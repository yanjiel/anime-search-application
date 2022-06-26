package AnimeSearch.repository;

import AnimeSearch.models.AnimeResponse;
import AnimeSearch.service.ResponseCallback;
import org.springframework.stereotype.Repository;
import org.springframework.web.reactive.function.client.WebClient;

@Repository
public class AnimeRepository {

    private final String BASE = "https://api.jikan.moe/v4/anime?q=%s&limit=%d&page=%d";
    //https://www.googleapis.com/books/v1/volumes?q=%s&maxResults=%d&startIndex=%d

    private AnimeResponse animeResponse;

    public void getAnimePaged(ResponseCallback<AnimeResponse> callback, String search, int maxResults,
                              int startPage) {


        String formatted = String.format(BASE, search, maxResults, startPage);
        WebClient.RequestHeadersSpec<?> spec = WebClient.create().get().uri(formatted);

        spec
                .retrieve().toEntity(AnimeResponse.class).subscribe(result -> {
                    this.animeResponse = result.getBody();
                    if (null == animeResponse || null == animeResponse.getData()) return;
//            if (null == animeResponse
//                    || null == animeResponse.getData()
//                    || animeResponse.getPagination().getStats().getCount() <= 0) return;
                    callback.operationFinished(animeResponse);

                });

//        spec.retrieve().bodyToMono(String.class).subscribe(result -> {
//                    String strStart = "\"images\":{\"jpg\":{\"image_url\":";
//                    String strEnd = "\"small_image_url\"";
////                    int startIndex = result.indexOf("\"images\":{\"jpg\":{\"image_url\":");
////                    int endIndex = result.indexOf("\"small_image_url\"");
//                    String url = result.substring(result.indexOf(strStart) + strStart.length(), result.indexOf(strEnd)-1)
//                            .replace("\"","").replace("\\","");
//                    System.out.println(url);
//                });

    }
}
