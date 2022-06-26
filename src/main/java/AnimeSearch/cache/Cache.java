package AnimeSearch.cache;


import AnimeSearch.models.FavoriteItem;
//import AnimeSearch.models.FavoriteItemBook;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;

public class Cache {

    private String keyword = "";
    private List<FavoriteItem> items = new ArrayList<>(); //list of favoriteItems = list of (items + userEmail)
    private FavoriteItem detailItem; // one favoriteItem
    private int offset;
    private boolean favMode;
    private String email = "anon@default.com"; //default email

    private static Cache cache;

    private Cache() {
    }

    public static Cache getInstance() {
        if (null == cache) {
            cache = new Cache();
        }
        return cache;
    }

    public String getKeyword() {
        return keyword;
    }

    public void setKeyword(String keyword) {
        this.keyword = keyword;

    }

    public Stream<FavoriteItem> streamItems() {
        return items.stream();
    } //favoriteItems are serializable

    public void addItems(List<FavoriteItem> items) {
        this.items.addAll(items);
    }

    public void clearItems() {
        this.items.clear();
    }

    public int itemsSize() {
        return items.size();
    }

    public FavoriteItem getDetailItem() {
        return detailItem;
    }

    public void setDetailItem(FavoriteItem detailItem) {
        this.detailItem = detailItem;
    }

    public boolean isFavMode() {
        return favMode;
    }

    public void setFavMode(boolean favMode) {
        this.favMode = favMode;
    }

    public int getOffset() {
        return offset;
    }

    public void setOffset(int offset) {
        this.offset = offset;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
