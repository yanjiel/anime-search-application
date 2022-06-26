package AnimeSearch.views.main;

import AnimeSearch.security.AvatarHelper;
import AnimeSearch.views.about.AboutView;
//import AnimeSearch.views.logout.LogoutView;
import AnimeSearch.cache.Cache;
import AnimeSearch.views.books.BooksView;
//import AnimeSearch.views.favorites.FavoritesView;
import AnimeSearch.views.favorites.FavoritesView;
import com.vaadin.flow.component.Component;
import com.vaadin.flow.component.ComponentUtil;
import com.vaadin.flow.component.applayout.AppLayout;
import com.vaadin.flow.component.applayout.DrawerToggle;
import com.vaadin.flow.component.avatar.Avatar;
import com.vaadin.flow.component.dependency.CssImport;
import com.vaadin.flow.component.dependency.JsModule;
import com.vaadin.flow.component.html.H1;
import com.vaadin.flow.component.html.Image;
import com.vaadin.flow.component.html.Span;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.page.Push;
import com.vaadin.flow.component.tabs.Tab;
import com.vaadin.flow.component.tabs.Tabs;
import com.vaadin.flow.component.tabs.TabsVariant;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.RouterLink;
import com.vaadin.flow.server.PWA;
import com.vaadin.flow.theme.Theme;
import com.vaadin.flow.theme.lumo.Lumo;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;

import java.util.Optional;

/**
 * The main view is a top-level placeholder for other views.
 */
@Push
@PWA(name = "GoogleBooksDatabase", shortName = "GoogleBooksDb", enableInstallPrompt = false)
@JsModule("./styles/shared-styles.js")
@Theme(value = Lumo.class, variant = Lumo.DARK)
@CssImport("./views/main.css")
public class MainView extends AppLayout  {

    private static final Logger LOGGER = LoggerFactory.getLogger(AppLayout.class);

    private final Tabs menu;
    private H1 viewTitle;

    public MainView(@Value("${authentication}") boolean authenticationEnabled) {
        setPrimarySection(Section.DRAWER);
        addToNavbar(true, createHeaderContent());
        menu = createMenu(authenticationEnabled);
        addToDrawer(createDrawerContent(menu));
    }


    private Component createHeaderContent()  {
        HorizontalLayout layout = new HorizontalLayout();
        layout.setId("header");
        layout.getThemeList().set("dark", true);
        layout.setSpacing(false);
        layout.add(new DrawerToggle());
        viewTitle = new H1();
        layout.add(viewTitle);
        Span user = new Span("Hello, " + Cache.getInstance().getEmail());
        user.setClassName("user");
        layout.add(user);

        try {
            layout.add(new Avatar(StringUtils.EMPTY, String.format("%s %s", AvatarHelper.BASE64_PREFIX,
                    AvatarHelper.createBase64Avatar(Math.abs(Cache.getInstance().getEmail().hashCode())))));
        } catch (Exception e) {
            LOGGER.warn("Unable to create avatar for user '{}':", Cache.getInstance().getEmail(), e);
        }

        return layout;
    }

    private Component createDrawerContent(Tabs menu) {
        VerticalLayout layout = new VerticalLayout();
        layout.addClassName("drawer-content");
        layout.setPadding(false);
        layout.setSpacing(false);
        layout.getThemeList().set("spacing-s", true);
        HorizontalLayout logoLayout = new HorizontalLayout();
        logoLayout.setId("logo");
        logoLayout.add(new Image("images/logo.png", "vaadinApp logo"));
        logoLayout.add(new H1("Book Search App"));
        layout.add(logoLayout, menu);
        return layout;
    }

    private Tabs createMenu(boolean authenticationEnabled) {
        final Tabs tabs = new Tabs();
        tabs.setOrientation(Tabs.Orientation.VERTICAL);
        tabs.addThemeVariants(TabsVariant.LUMO_MINIMAL);
        tabs.setId("tabs");
        tabs.add(createMenuItems());

//        if (authenticationEnabled) {
//            tabs.add(createTab("Log Out", LogoutView.class));
//        }

        return tabs;
    }

    private Component[] createMenuItems() {
//        return new Tab[]{createTab("Book Search", BooksView.class), createTab("Favorites", FavoritesView.class),
//                createTab("About Us", AboutView.class)};
        return new Tab[]{createTab("Book Search", BooksView.class), createTab("About Us", AboutView.class)};
    }

    private static Tab createTab(String text, Class<? extends Component> navigationTarget) {
        final Tab tab = new Tab();
        tab.add(new RouterLink(text, navigationTarget));
        ComponentUtil.setData(tab, Class.class, navigationTarget);
        return tab;
    }

    @Override
    protected void afterNavigation() {
        super.afterNavigation();
        getTabForComponent(getContent()).ifPresent(selectedTab -> menu.setSelectedTab(selectedTab));
        viewTitle.setText(getCurrentPageTitle());
    }

    private Optional<Tab> getTabForComponent(Component component) {
        return menu.getChildren().filter(tab -> ComponentUtil.getData(tab, Class.class).equals(component.getClass()))
                .findFirst().map(obj -> Tab.class.cast(obj));
    }

    private String getCurrentPageTitle() {
        PageTitle title = getContent().getClass().getAnnotation(PageTitle.class);
        return title == null ? "" : title.value();
    }

}
