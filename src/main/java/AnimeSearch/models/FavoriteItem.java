package AnimeSearch.models;



import java.io.Serializable;
import java.util.List;

public class FavoriteItem implements Serializable {

    private String id;
    private String userEmail;
    private String title;
    private String year;
    private String description;
    private String authorName;
    private String link; //thumbnail

    //factory method
    public static FavoriteItem fromItem(Item item, String  userEmail){
        FavoriteItem favoriteItem = new FavoriteItem();
        favoriteItem.setUserEmail(userEmail);

        StringBuilder stringBuilder = new StringBuilder();
        List<String> authors = item.getVolumeInfo().getAuthors();
        if (null == authors || authors.size() < 1) {
            favoriteItem.setAuthorName("none");
        } else {
            for (int nC = 0; nC < authors.size() -1; nC++) {
                stringBuilder.append(authors.get(nC) + " & ");
            }
            stringBuilder.append(authors.get(authors.size() -1));
            favoriteItem.setAuthorName(stringBuilder.toString());
        }

        favoriteItem.setDescription(null == item.getVolumeInfo().getDescription() ? "": item.getVolumeInfo().getDescription());
        favoriteItem.setId(null == item.getId() ? "": item.getId());
        if( null != item.getVolumeInfo().getImageLinks() && null != item.getVolumeInfo().getImageLinks().getThumbnail()){
            favoriteItem.setLink(item.getVolumeInfo().getImageLinks().getThumbnail());
        } else {
            favoriteItem.setLink("https://picsum.photos/100/300");
        }

        favoriteItem.setTitle(null == item.getVolumeInfo().getTitle() ? "" : item.getVolumeInfo().getTitle());
        favoriteItem.setYear(null == item.getVolumeInfo().getPublishedDate() ? "" : item.getVolumeInfo().getPublishedDate());
        return favoriteItem;
    }



    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getUserEmail() {
        return userEmail;
    }

    public void setUserEmail(String userEmail) {
        this.userEmail = userEmail;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getYear() {
        return year;
    }

    public void setYear(String year) {
        this.year = year;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getAuthorName() {
        return authorName;
    }

    public void setAuthorName(String authorName) {
        this.authorName = authorName;
    }

    public String getLink() {
        return link;
    }

    public void setLink(String link) {
        this.link = link;
    }
}
