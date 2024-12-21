package kdohyeon.boilerplate.token;

public interface KakaoTokenPort {
    String getAccessTokenByCode(String code);
}
