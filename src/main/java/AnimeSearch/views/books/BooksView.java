package AnimeSearch.views.books;

import AnimeSearch.cache.Cache;
import AnimeSearch.models.FavoriteItem;
import AnimeSearch.service.GoogleBooksService;
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


@Route(value = "book-search", layout = MainView.class)
@RouteAlias(value = "", layout = MainView.class)
@PageTitle("Book Search")
@CssImport("./views/generic-list.css")
@PreserveOnRefresh
public class BooksView extends Div implements AfterNavigationObserver {
    public static int MAX_RESULTS = 20;
    private GoogleBooksService googleBooksService;
    private Grid<FavoriteItem> grid = new Grid<>();
    private boolean isLoading = false;
    private TextField keyWord;
    private Notification loading = new Notification("Loading...", 1000, Notification.Position.BOTTOM_CENTER);

    private Notification doneLoading = new Notification("Done loading", 750, Notification.Position.BOTTOM_CENTER);

    public BooksView(GoogleBooksService googleBooksService) {
        this.googleBooksService = googleBooksService;

        keyWord = new TextField();
        keyWord.setLabel("Search Term");
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

                            Cache.getInstance().setDetailItem(event.getItem());
                            Cache.getInstance().setFavMode(false);
                            ui.navigate("detail-view");

                        }
                ));

        add(keyWord, withClientsideScrollListener(grid));
    }

    @Override
    public void afterNavigation(AfterNavigationEvent event) {

        if (Cache.getInstance().itemsSize() > 0)
            grid.setItems(Cache.getInstance().streamItems());

        keyWord.setValue(Cache.getInstance().getKeyword());

    }

    private void getPlaces(String searchTerm) {

        if (null == searchTerm || searchTerm.equals("")) return;

        isLoading = true;
        googleBooksService.getBooksPaged(volResp -> {
            getUI().get().access(() -> {
                //1. collect a list of favoriteItems and add to cache
                Cache.getInstance().addItems(volResp.getItems()
                        .stream()
                        .map( item -> FavoriteItem.fromItem(item, Cache.getInstance().getEmail()))
                        .collect(Collectors.toList())
                );
                // 2. add the list of favoriteItems to grid (from cache), and move the offset
                grid.setItems(Cache.getInstance().streamItems());
                Cache.getInstance().setOffset(Cache.getInstance().getOffset() + MAX_RESULTS);
                isLoading = false;
                // 3. push to client
                getUI().get().push();
                doneLoading.open();////////////
            });},
                searchTerm,
                MAX_RESULTS,
                Cache.getInstance().getOffset() //this was updated in the callback
        );
    }


    private Grid<FavoriteItem> withClientsideScrollListener(Grid<FavoriteItem> grid) {
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
