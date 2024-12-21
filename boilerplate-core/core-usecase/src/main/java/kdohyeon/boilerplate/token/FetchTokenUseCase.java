package kdohyeon.boilerplate.token;

import kdohyeon.boilerplate.user.response.UserResponse;

public interface FetchTokenUseCase {
    Boolean validateToken(String accessToken);

    String getTokenFromKakao(String code);

    UserResponse findUserByAccessToken(String accessToken);
}
