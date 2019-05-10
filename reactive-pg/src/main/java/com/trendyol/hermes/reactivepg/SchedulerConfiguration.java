package com.trendyol.hermes.reactivepg;

import java.util.concurrent.Executors;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import reactor.core.scheduler.Scheduler;
import reactor.core.scheduler.Schedulers;

@Configuration
public class SchedulerConfiguration {
    
    @Value("${api.scheduler.poolSize}")
    private int poolSize;
    
    @Bean
    public Scheduler scheduler() {
        return Schedulers.fromExecutorService(Executors.newFixedThreadPool(poolSize));
    }
}
