package kdohyeon.boilerplate.user;

import kdohyeon.boilerplate.user.response.UserResponse;

public interface FetchUserUseCase {
    UserResponse findUserByEmail(String email);

    UserResponse findByProviderId(String providerId);

    UserResponse findKakaoUser(String accessToken);
}
