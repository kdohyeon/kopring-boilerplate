package kdohyeon.boilerplate.movie;

import kdohyeon.boilerplate.movie.restponse.MovieResponse;
import kdohyeon.boilerplate.movie.restponse.PageableMoviesResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Slf4j
@Service
@RequiredArgsConstructor
public class MovieService implements FetchMovieUseCase, InsertMovieUseCase, DownloadMovieUseCase, LikeMovieUseCase {
    private final TmdbMoviePort tmdbMoviePort;
    private final PersistenceMoviePort persistenceMoviePort;
    private final DownloadMoviePort downloadMoviePort;
    private final LikeMoviePort likeMoviePort;
    private final List<UserDownloadMovieRoleValidator> validators;

    @Override
    public PageableMoviesResponse fetchFromClient(int page) {
        TmdbPageableMovies tmdbPageableMovies = tmdbMoviePort.fetchPageable(page);
        return new PageableMoviesResponse(
                tmdbPageableMovies.getTmdbMovies().stream()
                        .map(movie -> new MovieResponse(
                                movie.getMovieName(),
                                movie.getIsAdult(),
                                movie.getGenre(),
                                movie.getOverview(),
                                movie.getReleaseAt()
                        )).collect(Collectors.toList()),
                tmdbPageableMovies.getPage(),
                tmdbPageableMovies.isHasNext()
        );
    }

    @Override
    public PageableMoviesResponse fetchFromDb(int page) {
        List<NetplixMovie> netplixMovies = persistenceMoviePort.fetchBy(page, 10);
        return new PageableMoviesResponse(
                netplixMovies.stream().map(it -> new MovieResponse(it.getMovieName(), it.getIsAdult(), List.of(), it.getOverview(), it.getReleasedAt())).toList(),
                page,
                true
        );
    }

    @Override
    public void insert(List<MovieResponse> items) {
        items.forEach(it -> {
                    NetplixMovie netplixMovie = NetplixMovie.builder()
                            .movieName(it.getMovieName())
                            .isAdult(it.getIsAdult())
                            .overview(it.getOverview())
                            .releasedAt(it.getReleaseAt())
                            .genre("genre")
                            .build();
                    persistenceMoviePort.insert(netplixMovie);
                }
        );
    }

    @Override
    public String download(String userId, String role, String movieId) {
        long cnt = downloadMoviePort.downloadCntToday(userId);
        boolean validate = validators.stream()
                .filter(validator -> validator.isTarget(role))
                .findAny()
                .orElseThrow()
                .validate(cnt);

        if (!validate) {
            throw new RuntimeException("더 이상 다운로드를 할 수 없습니다.");
        }

        NetplixMovie by = persistenceMoviePort.findBy(movieId);

        downloadMoviePort.save(UserMovieDownload.newDownload(userId, movieId));

        return by.getMovieName();
    }

    @Override
    public void like(String userId, String movieId) {
        Optional<UserMovieLike> byUserIdAndMovieId = likeMoviePort.findByUserIdAndMovieId(userId, movieId);
        if (byUserIdAndMovieId.isEmpty()) {
            likeMoviePort.save(UserMovieLike.newLike(userId, movieId));
        }

        UserMovieLike userMovieLike = byUserIdAndMovieId.get();
        userMovieLike.like();
        likeMoviePort.save(userMovieLike);
    }
}
