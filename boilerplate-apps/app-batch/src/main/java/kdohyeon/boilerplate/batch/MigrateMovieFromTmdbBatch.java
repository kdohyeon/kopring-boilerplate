package kdohyeon.boilerplate.batch;

import kdohyeon.boilerplate.movie.FetchMovieUseCase;
import kdohyeon.boilerplate.movie.InsertMovieUseCase;
import kdohyeon.boilerplate.movie.restponse.MovieResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.job.builder.JobBuilder;
import org.springframework.batch.core.launch.support.RunIdIncrementer;
import org.springframework.batch.core.repository.JobRepository;
import org.springframework.batch.core.step.builder.StepBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.transaction.PlatformTransactionManager;

import java.util.List;

@Slf4j
@Configuration
@RequiredArgsConstructor
public class MigrateMovieFromTmdbBatch {

    private final FetchMovieUseCase fetchMovieUseCase;
    private final InsertMovieUseCase insertMovieUseCase;

    @Bean(name = "MigrateMoviesFromTmdbBatch")
    public Job job(JobRepository jobRepository, PlatformTransactionManager transactionManager) {
        return new JobBuilder("MigrateMoviesFromTmdbBatch", jobRepository)
                .preventRestart()
                .start(step(jobRepository, transactionManager))
                .incrementer(new RunIdIncrementer())
                .build();
    }

    @Bean(name = "MigrateMoviesFromTmdbBatchStep")
    public Step step(JobRepository jobRepository, PlatformTransactionManager transactionManager) {
        return new StepBuilder("MigrateMoviesFromTmdbBatchStep", jobRepository)
                .chunk(10, transactionManager)
                .reader(new HttpPageItemReader(1, fetchMovieUseCase))
                .writer(chunk -> {
                    List<MovieResponse> items = (List<MovieResponse>) chunk.getItems();
                    insertMovieUseCase.insert(items);
                })
                .build();
    }
}
