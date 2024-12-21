package kdohyeon.boilerplate.repository.movie;

import kdohyeon.boilerplate.entity.movie.UserMovieDownloadEntity;
import kdohyeon.boilerplate.movie.DownloadMoviePort;
import kdohyeon.boilerplate.movie.UserMovieDownload;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
@RequiredArgsConstructor
public class UserMovieDownloadRepository implements DownloadMoviePort {

    private final UserMovieDownloadJpaRepository userMovieDownloadJpaRepository;

    @Override
    @Transactional
    public void save(UserMovieDownload domain) {
        userMovieDownloadJpaRepository.save(UserMovieDownloadEntity.toEntity(domain));
    }

    @Override
    @Transactional
    public long downloadCntToday(String userId) {
        return userMovieDownloadJpaRepository.countDownloadToday(userId);
    }
}
