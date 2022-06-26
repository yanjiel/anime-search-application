package AnimeSearch.service;


import AnimeSearch.models.VolumesResponse;
import AnimeSearch.repository.GoogleBooksRepository;
import org.springframework.stereotype.Service;


@Service
public class GoogleBooksService {

    public static final int MAX_RESULTS = 20;
    private GoogleBooksRepository googleBooksRepository;

    public GoogleBooksService(GoogleBooksRepository googleBooksRepository) {
        this.googleBooksRepository = googleBooksRepository;
    }


    public void getBooksPaged(ResponseCallback<VolumesResponse> callback, String search, int maxResults,
                              int startIndex) {

        System.out.println("fetching books -> " + startIndex + " to "
                + (startIndex + maxResults - 1));

        googleBooksRepository.getBooksPaged(callback, search, maxResults, startIndex);


    }

}
