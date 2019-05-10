package com.trendyol.hermes.reactorblockingjdbcandjpa.jdbc;

import java.util.concurrent.Executors;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import reactor.core.scheduler.Scheduler;
import reactor.core.scheduler.Schedulers;

@Configuration
public class SlowIOSchedulerConfiguration {
    
    @Bean
    public Scheduler slowIOOperationsScheduler() {
        return Schedulers.fromExecutorService(Executors.newFixedThreadPool(200));
    }
}