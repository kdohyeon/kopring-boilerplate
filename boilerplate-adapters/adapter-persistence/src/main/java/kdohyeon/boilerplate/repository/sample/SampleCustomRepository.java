package kdohyeon.boilerplate.repository.sample;

import kdohyeon.boilerplate.entity.sample.SampleEntity;

import java.util.List;

public interface SampleCustomRepository {
    List<SampleEntity> findAllByAbc();
}
