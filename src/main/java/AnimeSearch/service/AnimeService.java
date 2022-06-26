package AnimeSearch.service;


import AnimeSearch.models.AnimeResponse;
//import AnimeSearch.models.VolumesResponse;
import AnimeSearch.repository.AnimeRepository;
import org.springframework.stereotype.Service;


@Service
public class AnimeService {

    public static final int MAX_RESULTS = 20;
    private AnimeRepository animeRepository;

    public AnimeService(AnimeRepository animeRepository) {
        this.animeRepository = animeRepository;
    }

    public void getAnimePaged(ResponseCallback<AnimeResponse> callback, String search, int maxResults, int startPage) {

        System.out.println("fetching books in page " + startPage + "-> no. " + ((startPage - 1) * maxResults + 1)
                + " to " + (startPage * maxResults));

        animeRepository.getAnimePaged(callback, search, maxResults, startPage);


    }

}
