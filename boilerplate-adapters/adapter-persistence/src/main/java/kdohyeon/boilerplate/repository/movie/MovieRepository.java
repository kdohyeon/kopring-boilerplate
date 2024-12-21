package kdohyeon.boilerplate.repository.movie;

import kdohyeon.boilerplate.entity.movie.MovieEntity;
import kdohyeon.boilerplate.movie.NetplixMovie;
import kdohyeon.boilerplate.movie.PersistenceMoviePort;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Repository
@RequiredArgsConstructor
public class MovieRepository implements PersistenceMoviePort {

    private final MovieJpaRepository movieJpaRepository;

    @Override
    @Transactional
    public List<NetplixMovie> fetchBy(int page, int size) {
        return movieJpaRepository.search(PageRequest.of(page, size))
                .stream().map(MovieEntity::toDomain)
                .toList();
    }

    @Override
    @Transactional
    public NetplixMovie findBy(String movieName) {
        return movieJpaRepository.findByMovieName(movieName)
                .map(MovieEntity::toDomain)
                .orElseThrow();
    }

    @Override
    @Transactional
    public void insert(NetplixMovie netplixMovie) {
        Optional<MovieEntity> byMovieName = movieJpaRepository.findByMovieName(netplixMovie.getMovieName());

        if (byMovieName.isPresent()) {
            return;
        }

        MovieEntity movieEntity = MovieEntity.newEntity(
                netplixMovie.getMovieName(),
                netplixMovie.getIsAdult(),
                netplixMovie.getGenre(),
                netplixMovie.getOverview(),
                netplixMovie.getReleasedAt()
        );
        movieJpaRepository.save(movieEntity);
    }
}
