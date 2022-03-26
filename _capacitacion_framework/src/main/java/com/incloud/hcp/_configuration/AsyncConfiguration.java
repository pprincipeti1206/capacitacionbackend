package com.incloud.hcp._configuration;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;

import java.util.concurrent.Executor;

//@Configuration
//@EnableAsync
//public class AsyncConfiguration implements AsyncConfigurer {
//
//    @Override
//    public Executor getAsyncExecutor() {
//        return new ThreadPoolTaskExecutor();
//    }
//
//    @Override
//    public AsyncUncaughtExceptionHandler getAsyncUncaughtExceptionHandler() {
//        return new CustomAsyncExceptionHandler();
//    }
//
//    @Bean
//    public Executor taskExecutor() {
//        ThreadPoolTaskExecutor executor = new ThreadPoolTaskExecutor();
//        executor.setCorePoolSize(5);
//        executor.setMaxPoolSize(10);
//        executor.setQueueCapacity(100);
//        executor.setWaitForTasksToCompleteOnShutdown(true);
//        executor.initialize();
//        return executor;
//    }
//
//}

@Configuration
@EnableAsync
public class AsyncConfiguration {
    private static final Logger LOGGER = LoggerFactory.getLogger(AsyncConfiguration.class);
    @Bean (name = "taskExecutor")
    public Executor taskExecutor() {
        LOGGER.debug("Creating Async Task Executor");
        final ThreadPoolTaskExecutor executor = new ThreadPoolTaskExecutor();
        executor.setCorePoolSize(4);
        executor.setMaxPoolSize(10);
        executor.setQueueCapacity(100);
        executor.setThreadNamePrefix("CarThread-");
        executor.initialize();
        return executor;
    }
}