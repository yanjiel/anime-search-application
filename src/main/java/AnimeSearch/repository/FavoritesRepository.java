package AnimeSearch.repository;

import AnimeSearch.cache.Cache;
import AnimeSearch.models.FavoriteItemBook;
import AnimeSearch.service.ResponseCallback;
import com.vaadin.flow.component.UI;
import com.vaadin.flow.component.notification.Notification;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.stereotype.Repository;
import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.web.reactive.function.client.WebClientResponseException;
import reactor.core.publisher.Mono;
import reactor.core.scheduler.Schedulers;

import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

@Repository
public class FavoritesRepository {
    private final ExecutorService executorService = Executors.newFixedThreadPool(3);

    //make sure to add your full container-url-connection-string to the IntelliJ confuguration
    //QUARKUS_BASE_URL :: https://container-service-22.9r4o895c5nvr8.us-east-2.cs.amazonlightsail.com/
    //If you are deploying to beanstalk, place in software || environmental properties.
    @Value("${QUARKUS_BASE_URL:#{'localhost:8080/'}}")
    private String baseUrl;

    public void getFavoritesPaged(ResponseCallback<List<FavoriteItemBook>> callback, int page) {

        String email = Cache.getInstance().getEmail();

        String raw = baseUrl + "/wish/paged/" + email + "/%d";
        String formatted = String.format(raw, page);
        WebClient.RequestHeadersSpec<?> spec = WebClient.create().get()
                .uri(formatted);

        spec.retrieve().bodyToMono(new ParameterizedTypeReference<List<FavoriteItemBook>>() {
        }).publishOn(Schedulers.fromExecutor(executorService)).subscribe(results -> callback.operationFinished(results));
    }


    public void deleteFavoriteById(UI ui, ResponseCallback<FavoriteItemBook> callback, String id) {

        String email = Cache.getInstance().getEmail();
        String raw = baseUrl + "/wish/" + email + "/%s";
        String formatted = String.format(raw, id);
        Mono<FavoriteItemBook> mono = WebClient.create().delete()
                .uri(formatted)
                .retrieve()
                .bodyToMono(FavoriteItemBook.class);

        mono
                .doOnError(throwable -> ui.access(() -> {
                    Notification.show("Unable to delete favorite: " + throwable.getMessage(), 2000,
                            Notification.Position.BOTTOM_CENTER);
                    ui.navigate("favorites");
                }))
                .publishOn(Schedulers.fromExecutor(executorService))
                .subscribe(results -> callback.operationFinished(results));

    }


    public void addFavorite(UI ui, ResponseCallback<FavoriteItemBook> callback, FavoriteItemBook favoriteAdd) {

        String formatted = baseUrl + "/wish";
        Mono<FavoriteItemBook> mono = WebClient.create().post()

                .uri(formatted)
                .body(Mono.just(favoriteAdd), FavoriteItemBook.class)
                .retrieve()
                .bodyToMono(FavoriteItemBook.class);

        mono
                .doOnError(throwable -> {
                    String message = "";
                    switch (((WebClientResponseException.UnsupportedMediaType) throwable).getStatusCode().value()){
                        case 415:
                            message = "This book is already in your favorites.";
                        break;
                        default:
                            message = "There was an error: " + throwable.getMessage();

                    }
                    final String finalMessage = message;
                    ui.access(() -> {
                        Notification.show(finalMessage , 2000,
                                Notification.Position.BOTTOM_CENTER);
                        ui.navigate("favorites");
                    });

                })
                .publishOn(Schedulers.fromExecutor(executorService))
                .subscribe(results -> callback.operationFinished(results));

    }



}
