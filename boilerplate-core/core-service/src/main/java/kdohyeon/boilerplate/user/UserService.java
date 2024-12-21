package kdohyeon.boilerplate.user;

import kdohyeon.boilerplate.exception.UserException;
import kdohyeon.boilerplate.user.command.UserRegistrationCommand;
import kdohyeon.boilerplate.user.response.UserRegistrationResponse;
import kdohyeon.boilerplate.user.response.UserResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserService implements FetchUserUseCase, RegisterUserUseCase {

    private final FetchUserPort fetchUserPort;
    private final InsertUserPort insertUserPort;
    private final KakaoUserPort kakaoUserPort;

    @Override
    public UserResponse findUserByEmail(String email) {
        Optional<UserPortResponse> byEmail = fetchUserPort.findByEmail(email);
        if (byEmail.isEmpty()) {
            throw new UserException.UserDoesNotExistException();
        }

        UserPortResponse userPortResponse = byEmail.get();

        return UserResponse.builder()
                .userId(userPortResponse.getUserId())
                .email(userPortResponse.getEmail())
                .password(userPortResponse.getPassword())
                .phone(userPortResponse.getPhone())
                .role(userPortResponse.getRole())
                .username(userPortResponse.getUsername())
                .build();
    }

    @Override
    public UserResponse findByProviderId(String providerId) {
        return fetchUserPort.findByProviderId(providerId)
                .map(it -> UserResponse.builder()
                        .userId(it.getUserId())
                        .providerId(it.getProviderId())
                        .provider(it.getProvider())
                        .username(it.getUsername())
                        .role(it.getRole())
                        .build())
                .orElse(null);
    }

    @Override
    public UserResponse findKakaoUser(String accessToken) {
        UserPortResponse userFromKakao = kakaoUserPort.findUserFromKakao(accessToken);
        return UserResponse.builder()
                .provider(userFromKakao.getProvider())
                .providerId(userFromKakao.getProviderId())
                .username(userFromKakao.getUsername())
                .build();
    }

    @Override
    public UserRegistrationResponse register(UserRegistrationCommand command) {
        String email = command.getEmail();
        Optional<UserPortResponse> byEmail = fetchUserPort.findByEmail(email);

        if (byEmail.isPresent()) {
            throw new UserException.UserAlreadyExistException();
        }

        UserPortResponse response = insertUserPort.create(CreateUser.builder()
                .username(command.getUsername())
                .encryptedPassword(command.getEncryptedPassword())
                .email(command.getEmail())
                .phone(command.getPhone())
                .build()
        );

        return new UserRegistrationResponse(response.getUsername(), response.getEmail(), response.getPhone());
    }

    @Override
    public UserRegistrationResponse registerSocialUser(String username, String provider, String providerId) {
        Optional<UserPortResponse> byProviderId = fetchUserPort.findByProviderId(providerId);
        if (byProviderId.isPresent()) {
            return null;
        }

        UserPortResponse socialUser = insertUserPort.createSocialUser(username, provider, providerId);
        return new UserRegistrationResponse(socialUser.getUsername(), null, null);
    }
}
