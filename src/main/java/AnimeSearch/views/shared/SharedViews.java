package AnimeSearch.views.shared;

import java.util.Random;
import AnimeSearch.models.FavoriteItem;
import com.vaadin.flow.component.dependency.CssImport;
import com.vaadin.flow.component.html.Div;
import com.vaadin.flow.component.html.Image;
import com.vaadin.flow.component.html.Span;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;

import java.util.Random;

@CssImport("./views/shared-views.css")
public class SharedViews {

    public static VerticalLayout getDetail(FavoriteItem favorite, boolean favMode) {
        VerticalLayout detail = new VerticalLayout();
        detail.setSpacing(false);
        detail.setPadding(false);

        detail.addClassName("detail");
        Div tab = new Div();
        tab.addClassName("tab");

        Image image = new Image();

        System.out.println("getDetail: " + favorite.getLink());///////////////////////////////////////////
        Random rand = new Random();
        int rand_int = rand.nextInt(399)+1;

        image.setSrc(null == favorite.getLink() ? "https://picsum.photos/id/"+rand_int+"/200/300" : favorite.getLink());
        VerticalLayout verticalLayout = new VerticalLayout();
        verticalLayout.addClassName("vertical-layout");


        Span title = getProperSpan(favorite.getTitle());
        title.addClassNames("text", "title");
        Span type = getProperSpan("Type: " + favorite.getType());
        type.addClassName("text");
        Span synopsis = getProperSpan(favorite.getSynopsis());
        synopsis.addClassName("text");
        Span email = getProperSpan(favorite.getUserEmail());
        email.addClassName("text");
        Span score = getProperSpan("Score: " + favorite.getScore());
        score.addClassName("text");

        if (favMode) {
            detail.addClassName("fav-mode");
            verticalLayout.add(title, type, score, email, synopsis);
        } else {
            verticalLayout.add(title, type, score, synopsis);
        }
        detail.add(tab, image, verticalLayout);

        return detail;
    }


    public static HorizontalLayout getCard(FavoriteItem favorite, boolean favMode) {
        HorizontalLayout card = new HorizontalLayout();
        card.addClassName("card");
        card.setSpacing(false);
        card.setPadding(false);

        Div tab = new Div();
        tab.addClassName("tab");
        Image image = new Image();

        System.out.println("getCard: " + favorite.getLink());////////////////////////////////////////
        image.setSrc(null == favorite.getLink() ? "https://picsum.photos/200/300" : favorite.getLink());
        VerticalLayout verticalLayout = new VerticalLayout();
        verticalLayout.addClassName("vertical-layout");
        verticalLayout.setSpacing(false);
        verticalLayout.setPadding(false);

        Span title = getProperSpan(favorite.getTitle());
        title.addClassNames("text", "title");
        Span type = getProperSpan("Type: " + favorite.getType());
        type.addClassName("text");
        Span synopsis = getProperSpan(favorite.getSynopsis());
        synopsis.addClassName("text");
        Span email = getProperSpan(favorite.getUserEmail());
        email.addClassName("text");
        Span score = getProperSpan("Score: " + favorite.getScore());
        score.addClassName("text");


        if (favMode) {
            verticalLayout.add(title, type, score, email);
            card.add(tab, image, verticalLayout);
        } else {
            verticalLayout.add(title, type, score);
            card.add(image, verticalLayout);
        }

        return card;
    }

    private static Span getProperSpan(String str){
        return new Span(null == str ? "" : str);
    }


}
