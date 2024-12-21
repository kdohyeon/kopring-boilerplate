package kdohyeon.boilerplate.token;

import kdohyeon.boilerplate.token.response.TokenResponse;

public interface CreateTokenUseCase {
    TokenResponse createNewToken(String userId);
}
