package kdohyeon.boilerplate.user;

import kdohyeon.boilerplate.user.command.UserRegistrationCommand;
import kdohyeon.boilerplate.user.response.UserRegistrationResponse;

public interface RegisterUserUseCase {
    UserRegistrationResponse register(UserRegistrationCommand command);
    UserRegistrationResponse registerSocialUser(String username, String provider, String providerId);
}
