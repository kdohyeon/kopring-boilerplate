package kdohyeon.boilerplate.repository.movie;

import kdohyeon.boilerplate.entity.movie.MovieEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MovieJpaRepository extends JpaRepository<MovieEntity, String>, MovieCustomRepository {
}
