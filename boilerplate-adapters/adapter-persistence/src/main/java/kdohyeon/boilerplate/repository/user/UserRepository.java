package kdohyeon.boilerplate.repository.user;

import kdohyeon.boilerplate.entity.user.SocialUserEntity;
import kdohyeon.boilerplate.entity.user.UserEntity;
import kdohyeon.boilerplate.repository.subscription.UserSubscriptionRepository;
import kdohyeon.boilerplate.repository.user.social.SocialUserJpaRepository;
import kdohyeon.boilerplate.subscription.UserSubscription;
import kdohyeon.boilerplate.user.CreateUser;
import kdohyeon.boilerplate.user.FetchUserPort;
import kdohyeon.boilerplate.user.InsertUserPort;
import kdohyeon.boilerplate.user.UserPortResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Repository
@RequiredArgsConstructor
public class UserRepository implements FetchUserPort, InsertUserPort {

    private final UserJpaRepository userJpaRepository;
    private final SocialUserJpaRepository socialUserJpaRepository;
    private final UserSubscriptionRepository userSubscriptionRepository;

    @Override
    @Transactional
    public Optional<UserPortResponse> findByEmail(String email) {
        Optional<UserEntity> byEmail = userJpaRepository.findByEmail(email);
        return byEmail.map(userEntity -> UserPortResponse.builder()
                .userId(userEntity.getUserId())
                .password(userEntity.getPassword())
                .username(userEntity.getUsername())
                .email(userEntity.getEmail())
                .phone(userEntity.getPhone())
                .build());

    }

    @Override
    @Transactional
    public Optional<UserPortResponse> findByProviderId(String providerId) {
        Optional<SocialUserEntity> byProviderId = socialUserJpaRepository.findByProviderId(providerId);
        if (byProviderId.isEmpty()) {
            return Optional.empty();
        }

        SocialUserEntity socialUserEntity = byProviderId.get();

        Optional<UserSubscription> byUserId = userSubscriptionRepository.findByUserId(socialUserEntity.getSocialUserId());

        return Optional.of(UserPortResponse.builder()
                .userId(socialUserEntity.getSocialUserId())
                .provider(socialUserEntity.getProvider())
                .providerId(socialUserEntity.getProviderId())
                .username(socialUserEntity.getUsername())
                .role(byUserId.orElse(UserSubscription.newSubscription(socialUserEntity.getSocialUserId()))
                        .getSubscriptionType()
                        .toRole())
                .build());
    }

    @Override
    @Transactional
    public UserPortResponse create(CreateUser user) {
        UserEntity userEntity = new UserEntity(user.getUsername(), user.getEncryptedPassword(), user.getEmail(), user.getPhone());
        UserEntity save = userJpaRepository.save(userEntity);

        userSubscriptionRepository.create(userEntity.getUserId());

        return UserPortResponse.builder()
                .userId(save.getUserId())
                .username(save.getUsername())
                .password(save.getPassword())
                .email(save.getEmail())
                .phone(save.getPhone())
                .build();
    }

    @Override
    @Transactional
    public UserPortResponse createSocialUser(String username, String provider, String providerId) {
        SocialUserEntity socialUserEntity = new SocialUserEntity(username, provider, providerId);
        socialUserJpaRepository.save(socialUserEntity);

        userSubscriptionRepository.create(socialUserEntity.getSocialUserId());

        return UserPortResponse.builder()
                .provider(socialUserEntity.getProvider())
                .providerId(socialUserEntity.getProviderId())
                .username(socialUserEntity.getUsername())
                .build();
    }
}
