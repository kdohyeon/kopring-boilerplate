package kdohyeon.boilerplate.user;

import java.util.Optional;

public interface FetchUserPort {
    Optional<UserPortResponse> findByEmail(String email);
    Optional<UserPortResponse> findByProviderId(String providerId);
}