package kdohyeon.boilerplate.repository.token;

import com.querydsl.jpa.impl.JPAQueryFactory;
import kdohyeon.boilerplate.entity.token.TokenEntity;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.Optional;

import static kdohyeon.boilerplate.entity.token.QTokenEntity.tokenEntity;

@Repository
@RequiredArgsConstructor
public class TokenCustomRepositoryImpl implements TokenCustomRepository {

    private final JPAQueryFactory jpaQueryFactory;

    @Override
    public Optional<TokenEntity> findByUserId(String userId) {
        return jpaQueryFactory.selectFrom(tokenEntity)
                .where(tokenEntity.userId.eq(userId))
                .fetch()
                .stream().findFirst();
    }
}
