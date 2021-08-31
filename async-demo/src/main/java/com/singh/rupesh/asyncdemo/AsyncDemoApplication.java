package com.singh.rupesh.asyncdemo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.Executor;

/*
The @EnableAsync annotation switches on Spring’s ability to run @Async methods in a background thread pool.
This class also customizes the Executor by defining a new bean. Refer more here on executors tuning
https://docs.spring.io/spring-framework/docs/current/reference/html/integration.html#scheduling-task-executor
*/
@SpringBootApplication
@EnableAsync
public class AsyncDemoApplication {
	// close the application context to shut down the custom ExecutorService
	public static void main(String[] args) {
		SpringApplication.run(AsyncDemoApplication.class, args).close();
	}


	@Bean
	public Executor taskExecutor() {
		ThreadPoolTaskExecutor executor = new ThreadPoolTaskExecutor();
		executor.setCorePoolSize(4);
		executor.setMaxPoolSize(4);
		executor.setQueueCapacity(500);
		executor.setThreadNamePrefix("GithubLookup-");
		executor.initialize();
		return executor;
	}
}
