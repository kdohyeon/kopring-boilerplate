package kdohyeon.boilerplate.controller.user;

import kdohyeon.boilerplate.controller.NetplixApiResponse;
import kdohyeon.boilerplate.controller.user.request.UserLoginRequest;
import kdohyeon.boilerplate.controller.user.request.UserRegisterRequest;
import kdohyeon.boilerplate.security.NetplixAuthUser;
import kdohyeon.boilerplate.token.FetchTokenUseCase;
import kdohyeon.boilerplate.token.UpdateTokenUseCase;
import kdohyeon.boilerplate.user.FetchUserUseCase;
import kdohyeon.boilerplate.user.RegisterUserUseCase;
import kdohyeon.boilerplate.user.command.UserRegistrationCommand;
import kdohyeon.boilerplate.user.response.UserRegistrationResponse;
import kdohyeon.boilerplate.user.response.UserResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.core.Authentication;
import org.springframework.util.ObjectUtils;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
@RequiredArgsConstructor
public class UserController {

    private final RegisterUserUseCase registerUserUseCase;
    private final AuthenticationManagerBuilder authenticationManagerBuilder;
    private final FetchTokenUseCase fetchTokenUseCase;
    private final FetchUserUseCase fetchUserUseCase;
    private final UpdateTokenUseCase updateTokenUseCase;

    @PostMapping("/api/v1/user/register")
    public NetplixApiResponse<UserRegistrationResponse> register(@RequestBody UserRegisterRequest request) {
        UserRegistrationResponse register = registerUserUseCase.register(
                UserRegistrationCommand.builder()
                        .email(request.getEmail())
                        .username(request.getUsername())
                        .encryptedPassword(request.getPassword())
                        .phone(request.getPhone())
                        .build()
        );

        return NetplixApiResponse.ok(register);
    }

    @PostMapping("/api/v1/user/login")
    public NetplixApiResponse<String> login(@RequestBody UserLoginRequest request) {
        String email = request.getEmail();
        String password = request.getPassword();

        UsernamePasswordAuthenticationToken token = new UsernamePasswordAuthenticationToken(email, password);
        Authentication authenticate = authenticationManagerBuilder.getObject().authenticate(token);

        NetplixAuthUser principal = (NetplixAuthUser) authenticate.getPrincipal();

        return NetplixApiResponse.ok("access-token");
    }

    @PostMapping("/api/v1/user/callback")
    public NetplixApiResponse<String> kakaoCallback(@RequestBody Map<String, String> request) {
        String code = request.get("code");

        String accessTokenFromKakao = fetchTokenUseCase.getTokenFromKakao(code);
        UserResponse kakaoUser = fetchUserUseCase.findKakaoUser(accessTokenFromKakao);

        UserResponse byProviderId = fetchUserUseCase.findByProviderId(kakaoUser.getProviderId());

        if (ObjectUtils.isEmpty(byProviderId)) {
            registerUserUseCase.registerSocialUser(
                    kakaoUser.getUsername(),
                    kakaoUser.getProvider(),
                    kakaoUser.getProviderId()
            );
        }

        return NetplixApiResponse.ok(updateTokenUseCase.upsertToken(kakaoUser.getProviderId()));
    }
}
