package kdohyeon.boilerplate.user;

public interface KakaoUserPort {
    UserPortResponse findUserFromKakao(String accessToken);
}
