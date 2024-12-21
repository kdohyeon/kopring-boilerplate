package kdohyeon.boilerplate.entity.movie;

import kdohyeon.boilerplate.audit.MutableBaseEntity;
import kdohyeon.boilerplate.movie.UserMovieDownload;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Entity
@Table(name = "user_movie_downloads")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
public class UserMovieDownloadEntity extends MutableBaseEntity {
    @Id
    @Column(name = "USER_MOVIE_DOWNLOAD_ID")
    private String userMovieDownloadId;

    @Column(name = "USER_ID")
    private String userId;

    @Column(name = "MOVIE_ID")
    private String movieId;

    public UserMovieDownload toDomain() {
        return UserMovieDownload.builder()
                .userMovieDownloadId(userMovieDownloadId)
                .movieId(movieId)
                .userId(userId)
                .build();
    }

    public static UserMovieDownloadEntity toEntity(UserMovieDownload domain) {
        return new UserMovieDownloadEntity(
                domain.getUserMovieDownloadId(), domain.getUserId(), domain.getMovieId()
        );
    }
}
