package kdohyeon.boilerplate.entity.token;

import kdohyeon.boilerplate.audit.MutableBaseEntity;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.time.LocalDateTime;
import java.util.UUID;

@Getter
@Entity
@Table(name = "tokens")
@RequiredArgsConstructor(access = AccessLevel.PROTECTED)
public class TokenEntity extends MutableBaseEntity {
    @Id
    @Column(name = "TOKEN_ID")
    private String tokenId;

    @Column(name = "USER_ID")
    private String userId;

    @Column(name = "ACCESS_TOKEN")
    private String accessToken;

    @Column(name = "REFRESH_TOKEN")
    private String refreshToken;

    @Column(name = "ACCESS_TOKEN_EXPIRES_AT")
    private LocalDateTime accessTokenExpiresAt;

    @Column(name = "REFRESH_TOKEN_EXPIRES_AT")
    private LocalDateTime refreshTokenExpiresAt;

    public TokenEntity(String userId, String accessToken, String refreshToken, LocalDateTime accessTokenExpireAt, LocalDateTime refreshTokenExpireAt) {
        this.tokenId = UUID.randomUUID().toString();
        this.userId = userId;
        this.accessToken = accessToken;
        this.refreshToken = refreshToken;
        this.accessTokenExpiresAt = accessTokenExpireAt;
        this.refreshTokenExpiresAt = refreshTokenExpireAt;
    }

    public void updateToken(String accessToken, String refreshToken) {
        LocalDateTime now = LocalDateTime.now();
        this.accessToken = accessToken;
        this.refreshToken = refreshToken;
        this.accessTokenExpiresAt = getAccessTokenExpireAt(now);
        this.refreshTokenExpiresAt = getRefreshTokenExpireAt(now);
    }

    public static TokenEntity newTokenEntity(String userId, String accessToken, String refreshToken) {
        LocalDateTime now = LocalDateTime.now();
        return new TokenEntity(
                userId, accessToken, refreshToken,
                getAccessTokenExpireAt(now),
                getRefreshTokenExpireAt(now)
        );
    }

    private static LocalDateTime getAccessTokenExpireAt(LocalDateTime now) {
        return now.plusHours(3);
    }

    private static LocalDateTime getRefreshTokenExpireAt(LocalDateTime now) {
        return now.plusHours(24);
    }
}
