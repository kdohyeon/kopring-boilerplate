package kdohyeon.boilerplate.entity.subscription;

import kdohyeon.boilerplate.audit.MutableBaseEntity;
import kdohyeon.boilerplate.subscription.SubscriptionType;
import kdohyeon.boilerplate.subscription.UserSubscription;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.UUID;

@Getter
@Entity
@Table(name = "user_subscriptions")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
public class UserSubscriptionEntity extends MutableBaseEntity {
    @Id
    @Column(name = "USER_SUBSCRIPTION_ID")
    private String userSubscriptionId;

    @Column(name = "USER_ID")
    private String userId;

    @Enumerated(value = EnumType.STRING)
    @Column(name = "SUBSCRIPTION_NAME")
    private SubscriptionType subscriptionType;

    @Column(name = "START_AT")
    private LocalDateTime subscriptionStartAt;

    @Column(name = "END_AT")
    private LocalDateTime subscriptionEndAt;

    @Column(name = "VALID_YN")
    private Boolean validYn;

    public UserSubscription toDomain() {
        return UserSubscription.builder()
                .userId(this.userId)
                .subscriptionType(this.subscriptionType)
                .startAt(this.subscriptionStartAt)
                .endAt(this.subscriptionEndAt)
                .validYn(this.validYn)
                .build();
    }

    public static UserSubscriptionEntity toEntity(UserSubscription userSubscription) {
        return new UserSubscriptionEntity(
                UUID.randomUUID().toString(),
                userSubscription.getUserId(),
                userSubscription.getSubscriptionType(),
                userSubscription.getStartAt(),
                userSubscription.getEndAt(),
                userSubscription.getValidYn()
        );
    }
}
