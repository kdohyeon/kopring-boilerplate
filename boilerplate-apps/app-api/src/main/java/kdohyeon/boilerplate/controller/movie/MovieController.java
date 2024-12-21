package kdohyeon.boilerplate.controller.movie;

import kdohyeon.boilerplate.controller.NetplixApiResponse;
import kdohyeon.boilerplate.filter.JwtTokenProvider;
import kdohyeon.boilerplate.movie.DownloadMovieUseCase;
import kdohyeon.boilerplate.movie.FetchMovieUseCase;
import kdohyeon.boilerplate.movie.LikeMovieUseCase;
import kdohyeon.boilerplate.movie.restponse.PageableMoviesResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
public class MovieController {
    private final FetchMovieUseCase fetchMovieUseCase;
    private final DownloadMovieUseCase downloadMovieUseCase;
    private final LikeMovieUseCase likeMovieUseCase;
    private final JwtTokenProvider jwtTokenProvider;

    @GetMapping("/api/v1/movie/client/{page}")
    public NetplixApiResponse<PageableMoviesResponse> fetchMoviePageables(@PathVariable int page) {
        PageableMoviesResponse pageableMoviesResponse = fetchMovieUseCase.fetchFromClient(page);
        return NetplixApiResponse.ok(pageableMoviesResponse);
    }

    @PostMapping("/api/v1/movie/search")
    public NetplixApiResponse<PageableMoviesResponse> search(@RequestParam int page) {
        PageableMoviesResponse pageableMoviesResponse = fetchMovieUseCase.fetchFromDb(page);
        return NetplixApiResponse.ok(pageableMoviesResponse);
    }

    @PostMapping("/api/v1/movie/{movieId}/download")
    @PreAuthorize("hasAnyRole('ROLE_BRONZE', 'ROLE_SILVER', 'ROLE_GOLD')")
    public NetplixApiResponse<String> download(@PathVariable String movieId) {
        String download = downloadMovieUseCase.download(jwtTokenProvider.getUserId(), jwtTokenProvider.getRole(), movieId);
        return NetplixApiResponse.ok(download);
    }

    @PostMapping("/api/v1/movie/{movieId}/like")
    @PreAuthorize("hasAnyRole('ROLE_FREE', 'ROLE_BRONZE', 'ROLE_SILVER', 'ROLE_GOLD')")
    public NetplixApiResponse<String> like(@PathVariable String movieId) {
        String userId = jwtTokenProvider.getUserId();
        likeMovieUseCase.like(userId, movieId);
        return NetplixApiResponse.ok("");
    }
}
