package kdohyeon.boilerplate.repository.movie;

public interface UserMovieDownloadCustomRepository {
    long countDownloadToday(String userId);
}
