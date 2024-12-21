package kdohyeon.boilerplate.movie;

public interface TmdbMoviePort {
    TmdbPageableMovies fetchPageable(int page);
}
