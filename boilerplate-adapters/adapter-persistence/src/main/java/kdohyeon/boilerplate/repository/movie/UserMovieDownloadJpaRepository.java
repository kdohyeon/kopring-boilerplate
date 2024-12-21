package kdohyeon.boilerplate.repository.movie;

import kdohyeon.boilerplate.entity.movie.UserMovieDownloadEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserMovieDownloadJpaRepository extends JpaRepository<UserMovieDownloadEntity, String>, UserMovieDownloadCustomRepository {

}
