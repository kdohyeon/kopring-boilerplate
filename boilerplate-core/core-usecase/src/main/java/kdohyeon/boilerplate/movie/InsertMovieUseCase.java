package kdohyeon.boilerplate.movie;

import kdohyeon.boilerplate.movie.restponse.MovieResponse;

import java.util.List;

public interface InsertMovieUseCase {
    void insert(List<MovieResponse> items);
}
