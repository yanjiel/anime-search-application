package AnimeSearch.views.books;

import AnimeSearch.cache.Cache;
import AnimeSearch.models.FavoriteItem;
import AnimeSearch.models.FavoriteItemBook;
import AnimeSearch.service.AnimeService;
import AnimeSearch.views.main.MainView;
import AnimeSearch.views.shared.SharedViews;
import AnimeSearch.utils.Utils;
import com.vaadin.flow.component.ClientCallable;
import com.vaadin.flow.component.dependency.CssImport;
import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.grid.GridVariant;
import com.vaadin.flow.component.html.Div;
import com.vaadin.flow.component.notification.Notification;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.router.*;

import java.util.stream.Collectors;


@Route(value = "anime-search", layout = MainView.class)
@RouteAlias(value = "", layout = MainView.class)
@PageTitle("Anime Search")
@CssImport("./views/generic-list.css")
@PreserveOnRefresh
public class AnimeView extends Div implements AfterNavigationObserver {
    public static int MAX_RESULTS = 20;
    private AnimeService animeService;
    private Grid<FavoriteItem> grid = new Grid<>();
    private boolean isLoading = false;
    private TextField keyWord;
    private Notification loading = new Notification("Loading...", 1000, Notification.Position.BOTTOM_CENTER);
    private Notification doneLoading = new Notification("Done loading", 750, Notification.Position.BOTTOM_CENTER);

    public AnimeView(AnimeService animeService) {
        this.animeService = animeService;

        keyWord = new TextField();
        keyWord.setLabel("Search Title");
        keyWord.setPlaceholder("type search-term, then press [ENTER]");
        keyWord.setAutofocus(true);
        keyWord.addKeyDownListener(keyDownEvent -> {
                    String keyStroke = keyDownEvent.getKey().getKeys().toString();
                    if (keyStroke.equals("[Enter]") && !isLoading && !keyWord.getValue().equals("")) {
                        Cache.getInstance().setKeyword(keyWord.getValue());
                        executeSearch(Cache.getInstance().getKeyword());
                    }
                }
        );

        addClassName("generic-list");
        grid.addThemeVariants(GridVariant.LUMO_NO_BORDER, GridVariant.LUMO_NO_ROW_BORDERS);
        grid.addComponentColumn(favoriteItem -> SharedViews.getCard(favoriteItem, false));
        grid.addItemClickListener(
                event -> grid.getUI().ifPresent(ui -> {
                            // if user clicks on a favoriteItem in the grid, then opens up detail-view to show details
                            Cache.getInstance().setDetailItem(event.getItem());
                            Cache.getInstance().setFavMode(false);
                            ui.navigate("detail-view");
                        }
                ));

        add(keyWord, withClientsideScrollListener(grid)); //add the TextField and Grid (w ClickListener and ScrollListener)
    }

    @Override
    public void afterNavigation(AfterNavigationEvent event) {

        if (Cache.getInstance().itemsSize() > 0)
            grid.setItems(Cache.getInstance().streamItems()); //cache.streamItems returns a Stream<FavoriteItem>

        keyWord.setValue(Cache.getInstance().getKeyword()); //set the value of the TextField
        System.out.println("search keyword is: " + keyWord.getValue());

    }

    private void getPlaces(String searchTerm) {

        if (null == searchTerm || searchTerm.equals("")) return;

        isLoading = true;
        animeService.getAnimePaged (
                //call back
                aniResp -> {
                    getUI().get().access(() -> {
                        //1. collect a list of favoriteItems and add to cache
                        Cache.getInstance().addItems(
                                aniResp.getData()
                                        .stream()
                                        .map( item -> FavoriteItem.fromItem(item, Cache.getInstance().getEmail()))
                                        .collect(Collectors.toList()));
                        // 2. add the list of favoriteItems to grid (from cache), and move the offset
                        grid.setItems(Cache.getInstance().streamItems());
                        //Cache.getInstance().setOffset(Cache.getInstance().getOffset() + MAX_RESULTS);
                        Cache.getInstance().setOffset(Cache.getInstance().getOffset() + 1); // the offset is for page numbers
                        isLoading = false;
                        // 3. push to client
                        getUI().get().push();
                        doneLoading.open();////////////
                    });},
                searchTerm,
                MAX_RESULTS,
                Cache.getInstance().getOffset() //this is the startPage which would have been updated in the callback
        );
    }


    private Grid<FavoriteItem> withClientsideScrollListener(Grid<FavoriteItem> grid) {
        //dom.element.executeJs to execute a javascript, second parameter is passed in (as element) to the executed script
        // scrollFunction.js javascript is in the resources sub-folder
        grid.getElement().executeJs(
                Utils.getFileFromResourceAsString(this.getClass(), "scrollFunction.js"),
                getElement());
        return grid;
    }

    @ClientCallable
    public void onGridEnd() {

        if (!isLoading) {
            System.out.println("Paging...");
            loading.open();
            getPlaces(Cache.getInstance().getKeyword());
        }

    }

    public void executeSearch(String searchFor) {

        Cache.getInstance().setKeyword(searchFor);
        Cache.getInstance().setOffset(0);
        Cache.getInstance().clearItems();
        getPlaces(searchFor);
    }





}
