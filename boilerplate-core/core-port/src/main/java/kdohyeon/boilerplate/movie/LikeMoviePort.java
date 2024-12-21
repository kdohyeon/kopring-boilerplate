package kdohyeon.boilerplate.movie;

import java.util.Optional;

public interface LikeMoviePort {
    void save(UserMovieLike domain);

    Optional<UserMovieLike> findByUserIdAndMovieId(String userId, String movieId);
}
