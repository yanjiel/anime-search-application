package AnimeSearch.repository;

import AnimeSearch.models.AnimeResponse;
import AnimeSearch.service.ResponseCallback;
//import net.minidev.json.JSONObject;
import org.springframework.stereotype.Repository;
import org.springframework.web.reactive.function.client.WebClient;

import java.util.Arrays;

@Repository
public class AnimeRepository {

    private final String BASE = "https://api.jikan.moe/v4/anime?q=%s&limit=%d&page=%d";
    //https://www.googleapis.com/books/v1/volumes?q=%s&maxResults=%d&startIndex=%d

    private AnimeResponse animeResponse;

    public void getAnimePaged(ResponseCallback<AnimeResponse> callback, String search, int maxResults,
                              int startPage) {


        String formatted = String.format(BASE, search, maxResults, startPage);
        WebClient.RequestHeadersSpec<?> spec = WebClient.create().get().uri(formatted);

        spec.retrieve().toEntity(AnimeResponse.class).subscribe(result -> {
                    this.animeResponse = result.getBody();
                    if (null == animeResponse || null == animeResponse.getData()) return;
//            if (null == animeResponse
//                    || null == animeResponse.getData()
//                    || animeResponse.getPagination().getStats().getCount() <= 0) return;
//                    callback.operationFinished(animeResponse);
                });

        spec.retrieve().bodyToMono(String.class).subscribe(result -> {
            String[] urls = result.split("\"jpg\":");
            String[] urls_2 = Arrays.copyOfRange(result.split("\"jpg\":"),1,urls.length+1 );

            for (String url: urls_2) {
                System.out.println(url);
            }
            callback.operationFinished(animeResponse);
        });

    }
}
