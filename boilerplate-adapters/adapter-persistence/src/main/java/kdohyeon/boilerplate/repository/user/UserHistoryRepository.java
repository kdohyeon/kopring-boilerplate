package kdohyeon.boilerplate.repository.user;

import kdohyeon.boilerplate.entity.user.UserHistoryEntity;
import kdohyeon.boilerplate.user.UserHistoryPort;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
@RequiredArgsConstructor
public class UserHistoryRepository implements UserHistoryPort {

    private final UserHistoryJpaRepository userHistoryJpaRepository;

    @Override
    @Transactional
    public void create(String userId, String userRole, String clientIp, String reqMethod, String reqUrl, String reqHeader, String reqPayload) {
        userHistoryJpaRepository.save(
                new UserHistoryEntity(
                        userId, userRole, clientIp, reqMethod, reqUrl, reqHeader, reqPayload)
        );
    }
}
