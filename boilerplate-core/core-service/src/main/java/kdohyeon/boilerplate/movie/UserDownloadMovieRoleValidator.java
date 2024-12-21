package kdohyeon.boilerplate.movie;

public interface UserDownloadMovieRoleValidator {
    boolean validate(long count);
    boolean isTarget(String role);
}
