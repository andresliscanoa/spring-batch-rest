package com.lliscano.springbatch.batch;

import com.lliscano.springbatch.dto.UserDTO;
import com.lliscano.springbatch.entity.Users;
import com.lliscano.springbatch.repository.UsersRepository;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.job.builder.JobBuilder;
import org.springframework.batch.core.repository.JobRepository;
import org.springframework.batch.core.step.builder.StepBuilder;
import org.springframework.batch.item.data.RepositoryItemReader;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.task.SimpleAsyncTaskExecutor;
import org.springframework.core.task.TaskExecutor;
import org.springframework.data.domain.Sort;
import org.springframework.transaction.PlatformTransactionManager;

import java.util.HashMap;
import java.util.Map;

@Configuration
@AllArgsConstructor
@Slf4j
public class UsersBatchConfig {

    private final UsersRepository usersRepository;

    @Bean
    public RepositoryItemReader<Users> usersRepositoryItemReader() {
        RepositoryItemReader<Users> reader = new RepositoryItemReader<>();
        Map<String, Sort.Direction> sort = new HashMap<>();
        sort.put("id", Sort.Direction.ASC);
        reader.setRepository(usersRepository);
        reader.setMethodName("findAll");
        reader.setSort(sort);
        return reader;
    }
    @Bean
    public UsersProcessor processor() {
        return new UsersProcessor();
    }
    @Bean
    public RestItemWriter restItemWriter() {
        return new RestItemWriter();
    }

    @Bean
    public Step importUsers(JobRepository jobRepository, PlatformTransactionManager transactionManager) {
        return new StepBuilder("users-step",jobRepository)
                .<Users, UserDTO>chunk(1000,transactionManager)
                .reader(usersRepositoryItemReader())
                .processor(processor())
                .writer(restItemWriter())
                .taskExecutor(taskExecutor())
                .build();
    }

    @Bean
    public Job processJob(JobRepository jobRepository, PlatformTransactionManager transactionManager) {
        return new JobBuilder("import-users", jobRepository)
                .flow(importUsers(jobRepository,transactionManager)).end().build();
    }

    @Bean
    public TaskExecutor taskExecutor() {
        SimpleAsyncTaskExecutor asyncTaskExecutor = new SimpleAsyncTaskExecutor();
        asyncTaskExecutor.setConcurrencyLimit(1000);
        return asyncTaskExecutor;
    }



}
