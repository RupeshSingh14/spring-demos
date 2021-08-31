
package com.singh.rupesh.asyncdemo.service;

import com.singh.rupesh.asyncdemo.vo.User;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.concurrent.CompletableFuture;

//CommandLineRunner is an interface used to indicate that a bean should run when it is contained within a SpringApplication.
@Component
public class AppRunner implements CommandLineRunner {

    private static final Logger LOG = LoggerFactory.getLogger(AppRunner.class);

    private final GithubLookupService githubLookupService;

    public AppRunner(GithubLookupService githubLookupService) {
        this.githubLookupService = githubLookupService;
    }

    @Override
    public void run(String... args) throws Exception {
        //start the clock
        long start = System.currentTimeMillis();

        //Kick off multiple, asynchronous lookups
        CompletableFuture<User> page1 = githubLookupService.findUser("PivotalSoftware");
        CompletableFuture<User> page2 = githubLookupService.findUser("CloudFoundry");
        CompletableFuture<User> page3 = githubLookupService.findUser("Spring-Projects");
        CompletableFuture<User> page4 = githubLookupService.findUser("vmware");

        CompletableFuture.allOf(page1, page2, page3, page4).join();

        // Print results, including elapsed time
        LOG.info("Elapsed time: " + (System.currentTimeMillis() - start));
        LOG.info("--> " + page1.get());
        LOG.info("--> " + page2.get());
        LOG.info("--> " + page3.get());
        LOG.info("--> " + page4.get());
    }
}