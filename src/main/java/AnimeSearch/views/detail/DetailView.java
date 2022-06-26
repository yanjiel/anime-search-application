package AnimeSearch.views.detail;

import AnimeSearch.cache.Cache;
import AnimeSearch.service.FavoritesService;
import AnimeSearch.views.main.MainView;
import AnimeSearch.views.shared.SharedViews;
import com.vaadin.flow.component.Component;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.dependency.CssImport;
import com.vaadin.flow.component.html.Div;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;

@Route(value = "detail-view", layout = MainView.class)
@PageTitle("Book Detail")
@CssImport("./views/detail.css")
public class DetailView extends Div {

    //    private Button favoriteAction = new Button();
    private Button goBack = new Button();
    private FavoritesService favoritesService;

//    private Notification noticeAdd = new Notification("Favorite ADDED", 1000, Notification.Position.BOTTOM_CENTER);
//    private Notification noticeDeleted = new Notification("Favorite DELETED", 1000,
//            Notification.Position.BOTTOM_CENTER);


    public DetailView(FavoritesService favoritesService) {
        this.favoritesService = favoritesService;

        addClassName("detail-wrap");
        add(SharedViews.getDetail(Cache.getInstance().getDetailItem(), Cache.getInstance().isFavMode()));
        add(createButtonLayout());

//        favoriteAction.setText(Cache.getInstance().isFavMode() ? "DELETE Favorite" : "ADD to Favorites");
//        favoriteAction.setClassName(Cache.getInstance().isFavMode() ? "red-button" : "green-button");
//        favoriteAction.addClickListener(e -> favoriteAction.getUI().ifPresent(ui -> {
//                    if (Cache.getInstance().isFavMode())
//                        deleteFavorite(Cache.getInstance().getDetailItem().getId());
//                    else
//                        addFavorite(Cache.getInstance().getDetailItem());
//                }
//        ));

//        goBack.setText(Cache.getInstance().isFavMode() ? "Return to Favorites" : "Return to Anime Search");
        goBack.setText("Return to Anime Search");
        goBack.addClickListener(
                e -> goBack.getUI().ifPresent(ui -> {
//                            if (Cache.getInstance().isFavMode())
//                                ui.navigate("favorites");
//                            else
//                                ui.navigate("anime-search");
                            ui.navigate("anime-search");
                        }
                ));
    }

//    public void addFavorite(FavoriteItem favorite) {
//        favoritesService.addFavorite(UI.getCurrent(), favoriteAdd -> {
//            getUI().get().access(() -> {
//                noticeAdd.open();
//                getUI().get().navigate("favorites");
//            });
//        }, favorite);
//
//    }

//    public void deleteFavorite(String id) {
//        favoritesService.deleteFavoriteById(UI.getCurrent(), favoriteDelete -> {
//            getUI().get().access(() -> {
//                noticeDeleted.open();
//                getUI().get().navigate("favorites");
//            });
//        }, id);
//
//    }


    private Component createButtonLayout() {
        HorizontalLayout buttonLayout = new HorizontalLayout();
        buttonLayout.addClassName("button-layout");
//        favoriteAction.addThemeVariants(ButtonVariant.LUMO_PRIMARY);
//        buttonLayout.add(favoriteAction);
        buttonLayout.add(goBack);
        return buttonLayout;
    }


}
