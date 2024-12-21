package kdohyeon.boilerplate.movie;

import kdohyeon.boilerplate.movie.restponse.PageableMoviesResponse;

public interface FetchMovieUseCase {
    PageableMoviesResponse fetchFromClient(int page);
    PageableMoviesResponse fetchFromDb(int page);
}
