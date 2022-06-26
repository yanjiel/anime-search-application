package AnimeSearch.models;



import java.io.Serializable;
import java.util.Random;
public class FavoriteItem implements Serializable {

    private String malId;
    private String userEmail;
    private String title;
    private String type;
    private String synopsis;
    private String score;
    private String link; //thumbnail

    //factory method
    public static FavoriteItem fromItem(Item item, String  userEmail){
        //assign the book id, title, year, description, authorName, link (thumbnail) to favoriteItem, also assign the userEmail to favoriteItem
        FavoriteItem favoriteItem = new FavoriteItem();
        favoriteItem.setMalId(null == item.getMalId() ? "": item.getMalId());
        favoriteItem.setUserEmail(userEmail);

//        StringBuilder stringBuilder = new StringBuilder();
//        List<String> authors = item.getVolumeInfo().getAuthors();
//        if (null == authors || authors.size() < 1) {
//            favoriteItem.setAuthorName("none");
//        } else {
//            for (int nC = 0; nC < authors.size() -1; nC++) {
//                stringBuilder.append(authors.get(nC) + " & ");
//            }
//            stringBuilder.append(authors.get(authors.size() -1));
//            favoriteItem.setAuthorName(stringBuilder.toString());
//        }

        favoriteItem.setTitle(null == item.getTitle() ? "" : item.getTitle());
        favoriteItem.setType(null == item.getType() ? "" : item.getType());
        favoriteItem.setScore((null == item.getScore()|| item.getScore() <= 0 ) ? "" : String.valueOf(item.getScore()));

        favoriteItem.setSynopsis(null == item.getSynopsis() ? "": item.getSynopsis());
        // set the thumbnail/link

//        System.out.println(item.getImages().getJpg().getImageUrl());
//        System.out.println(item.getImages().getJpg().getSmallImageUrl());
//        System.out.println(item.getImages().getJpg().getLargeImageUrl());
//        System.out.println(item.getImages().getWebp().getImageUrl());
//        System.out.println(item.getImages().getWebp().getSmallImageUrl());
//        System.out.println(item.getImages().getWebp().getLargeImageUrl());
//        System.out.println(item.getTrailer().getImages().getImageUrl());
//        System.out.println(item.getTrailer().getImages().getSmallImageUrl());
//        System.out.println(item.getTrailer().getImages().getMediumImageUrl());
//        System.out.println(item.getTrailer().getImages().getLargeImageUrl());
//        System.out.println(item.getTrailer().getImages().getMaximumImageUrl());

        if( null != item.getTitle() && null != item.getImages().getJpg().getSmallImageUrl()){
            favoriteItem.setLink(item.getImages().getJpg().getSmallImageUrl());
        } else {
            Random rand = new Random();
            int rand_int = rand.nextInt(399) + 1;
            favoriteItem.setLink("https://picsum.photos/id/" + rand_int + "/200/300");
        }

        return favoriteItem;
    }



    public String getMalId() {
        return malId;
    }

    public void setMalId(String malId) {
        this.malId = malId;
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

    public String getType() {
        return type;
    }

    public void setType(String type) {this.type = type;}

    public String getSynopsis() {
        return synopsis;
    }

    public void setSynopsis(String synopsis) {this.synopsis = synopsis;}

    public String getScore() {
        return score;
    }

    public void setScore(String score) {
        this.score = score;
    }

    public String getLink() {
        return link;
    }

    public void setLink(String link) {
        this.link = link;
    }
}
