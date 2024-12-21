package kdohyeon.boilerplate.repository.user;

import kdohyeon.boilerplate.entity.user.UserHistoryEntity;
import org.springframework.data.jpa.repository.JpaRepository;


public interface UserHistoryJpaRepository extends JpaRepository<UserHistoryEntity, Long> {

}
