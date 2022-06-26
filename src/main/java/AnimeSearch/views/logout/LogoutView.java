package AnimeSearch.views.logout;

import AnimeSearch.views.main.MainView;
import com.vaadin.flow.component.UI;
import com.vaadin.flow.component.html.Div;
import com.vaadin.flow.router.AfterNavigationEvent;
import com.vaadin.flow.router.AfterNavigationObserver;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;

@Route(value = "logout", layout = MainView.class)
@PageTitle("Log Out")
public class LogoutView extends Div implements AfterNavigationObserver {

    @Override
    public void afterNavigation(AfterNavigationEvent afterNavigationEvent) {
    	UI.getCurrent().getPage().reload();
    }
}
