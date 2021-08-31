package com.singh.rupesh.asyncdemo.service;

import com.singh.rupesh.asyncdemo.vo.User;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.concurrent.CompletableFuture;

@Service
public class GithubLookupService {

    private static final Logger LOG = LoggerFactory.getLogger(GithubLookupService.class);
    private final RestTemplate restTemplate;

    public GithubLookupService(RestTemplateBuilder restTemplateBuilder) {
        this.restTemplate = restTemplateBuilder.build();
    }

    /*
    The findUser method is flagged with Spring’s @Async annotation, indicating that it should run on a separate thread.
    The method’s return type is CompletableFuture<User> instead of User, a requirement for any asynchronous service.
    */
    @Async
    public CompletableFuture<User> findUser(String user) throws InterruptedException {
        LOG.info("Looking up " + user);
        String url = String.format("https://api.github.com/users/%s", user);
        User results = restTemplate.getForObject(url, User.class);
        Thread.sleep(1000L);
        return CompletableFuture.completedFuture(results);
    }
}
