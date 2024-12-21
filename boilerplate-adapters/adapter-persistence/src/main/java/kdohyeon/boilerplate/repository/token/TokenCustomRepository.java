package kdohyeon.boilerplate.repository.token;

import kdohyeon.boilerplate.entity.token.TokenEntity;

import java.util.Optional;

public interface TokenCustomRepository {
    Optional<TokenEntity> findByUserId(String userId);
}
