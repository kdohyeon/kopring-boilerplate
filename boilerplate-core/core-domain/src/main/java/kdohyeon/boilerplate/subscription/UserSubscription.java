package kdohyeon.boilerplate.subscription;

import lombok.Builder;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
@Builder
public class UserSubscription {
    private String userId;
    private SubscriptionType subscriptionType;
    private LocalDateTime startAt;
    private LocalDateTime endAt;
    private Boolean validYn;

    public UserSubscription(String userId, SubscriptionType subscriptionType, LocalDateTime startAt, LocalDateTime endAt, Boolean validYn) {
        this.userId = userId;
        this.subscriptionType = subscriptionType;
        this.startAt = startAt;
        this.endAt = endAt;
        this.validYn = validYn;
    }

    public void off() {
        this.validYn = false;
    }

    public void renew() {
        this.startAt = LocalDateTime.now();
        this.endAt = getEndAt(startAt);
        this.validYn = true;
    }

    public void change(SubscriptionType type) {
        this.subscriptionType = type;
    }

    public boolean ableToRenew() {
        LocalDateTime now = LocalDateTime.now();
        return now.isAfter(endAt);
    }

    public boolean ableToChange() {
        LocalDateTime now = LocalDateTime.now();
        return now.isBefore(endAt) && now.isAfter(startAt) && validYn;
    }

    public static UserSubscription newSubscription(String userId) {
        LocalDateTime now = LocalDateTime.now();

        return UserSubscription.builder()
                .userId(userId)
                .subscriptionType(SubscriptionType.FREE)
                .startAt(now)
                .endAt(getEndAt(now))
                .validYn(true)
                .build();
    }

    private static LocalDateTime getEndAt(LocalDateTime startAt) {
        return startAt.plusDays(30);
    }
}
