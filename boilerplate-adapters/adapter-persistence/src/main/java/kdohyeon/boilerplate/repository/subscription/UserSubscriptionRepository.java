package kdohyeon.boilerplate.repository.subscription;

import kdohyeon.boilerplate.entity.subscription.UserSubscriptionEntity;
import kdohyeon.boilerplate.subscription.FetchUserSubscriptionPort;
import kdohyeon.boilerplate.subscription.InsertUserSubscriptionPort;
import kdohyeon.boilerplate.subscription.UpdateUserSubscriptionPort;
import kdohyeon.boilerplate.subscription.UserSubscription;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Repository
@RequiredArgsConstructor
public class UserSubscriptionRepository implements FetchUserSubscriptionPort, UpdateUserSubscriptionPort, InsertUserSubscriptionPort {

    private final UserSubscriptionJpaRepository userSubscriptionJpaRepository;

    @Override
    @Transactional
    public Optional<UserSubscription> findByUserId(String userId) {
        return userSubscriptionJpaRepository.findByUserId(userId)
                .map(UserSubscriptionEntity::toDomain);
    }

    @Override
    @Transactional
    public void create(String userId) {
        UserSubscription userSubscription = UserSubscription.newSubscription(userId);
        userSubscriptionJpaRepository.save(UserSubscriptionEntity.toEntity(userSubscription));
    }

    @Override
    @Transactional
    public void update(UserSubscription userSubscription) {
        userSubscriptionJpaRepository.save(UserSubscriptionEntity.toEntity(userSubscription));
    }
}
