package AnimeSearch.repository;

import AnimeSearch.models.VolumesResponse;
import AnimeSearch.service.ResponseCallback;
import org.springframework.stereotype.Repository;
import org.springframework.web.reactive.function.client.WebClient;

@Repository
public class GoogleBooksRepository {

    private final String BASE = "https://www.googleapis.com/books/v1/volumes?q=%s&maxResults=%d&startIndex=%d";

    public void getBooksPaged(ResponseCallback<VolumesResponse> callback, String search, int maxResults,
                              int startIndex) {


        String formatted = String.format(BASE, search, maxResults, startIndex);
        WebClient.RequestHeadersSpec<?> spec = WebClient.create().get().uri(formatted);

        spec
                .retrieve().toEntity(VolumesResponse.class).subscribe(result -> {

            final VolumesResponse volumesResponse = result.getBody();
            if (null == volumesResponse || null == volumesResponse.getItems()) return;
            callback.operationFinished(volumesResponse);

        });

    }
}
