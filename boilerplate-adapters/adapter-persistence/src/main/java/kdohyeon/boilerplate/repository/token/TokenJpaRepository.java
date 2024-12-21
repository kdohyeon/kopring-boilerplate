package kdohyeon.boilerplate.repository.token;

import kdohyeon.boilerplate.entity.token.TokenEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TokenJpaRepository extends JpaRepository<TokenEntity, String>, TokenCustomRepository {
}
