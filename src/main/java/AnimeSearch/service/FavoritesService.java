package AnimeSearch.service;

import AnimeSearch.models.FavoriteItemBook;
import AnimeSearch.repository.FavoritesRepository;
import com.vaadin.flow.component.UI;
import org.springframework.stereotype.Service;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.util.List;

@Service
public class FavoritesService extends ResponseEntityExceptionHandler {
    private FavoritesRepository favoritesRepository;



    public FavoritesService(FavoritesRepository favoritesRepository) {
        this.favoritesRepository = favoritesRepository;
    }


    public void getFavoritesPaged(ResponseCallback<List<FavoriteItemBook>> callback,
                                  int page) {
        favoritesRepository.getFavoritesPaged(callback, page);
    }



    public void addFavorite(UI ui, ResponseCallback<FavoriteItemBook> callback,
                            FavoriteItemBook favorite)  {
        favoritesRepository.addFavorite(ui, callback, favorite);
    }

    public void deleteFavoriteById(UI ui, ResponseCallback<FavoriteItemBook> callback,
                                   String id) {
        favoritesRepository.deleteFavoriteById(ui, callback, id);
    }



}
