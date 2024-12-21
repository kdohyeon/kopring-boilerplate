package kdohyeon.boilerplate.movie.restponse;

import lombok.Getter;

import java.util.List;

@Getter
public class MovieResponse {
    private final String movieName;
    private final Boolean isAdult;
    private final List<String> genre;
    private final String overview;
    private final String releaseAt;

    public MovieResponse(String movieName, Boolean isAdult, List<String> genre, String overview, String releaseAt) {
        this.movieName = movieName;
        this.isAdult = isAdult;
        this.genre = genre;
        this.overview = overview;
        this.releaseAt = releaseAt;
    }
}
