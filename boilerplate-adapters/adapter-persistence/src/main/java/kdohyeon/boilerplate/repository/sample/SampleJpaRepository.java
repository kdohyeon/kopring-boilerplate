package kdohyeon.boilerplate.repository.sample;

import kdohyeon.boilerplate.entity.sample.SampleEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SampleJpaRepository extends JpaRepository<SampleEntity, String>, SampleCustomRepository {
}
