package com.trendyol.hermes.reactorblockingjdbcandjpa.jdbc;

import java.time.Duration;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import reactor.core.scheduler.Scheduler;

@Component
public class ReactorService implements ReactorExtension {
    
    private final Scheduler slowIOOperationsScheduler;
    private final JdbcConfiguration jdbcConfiguration;
    
    @Autowired
    public ReactorService(Scheduler slowIOOperationsScheduler,
        JdbcConfiguration jdbcConfiguration) {
        this.slowIOOperationsScheduler = slowIOOperationsScheduler;
        this.jdbcConfiguration = jdbcConfiguration;
    }
    
    @Override
    public Scheduler getScheduler() {
        return slowIOOperationsScheduler;
    }
    
    @Override
    public Duration getTimeout() {
        return jdbcConfiguration.getTimeoutDuration();
    }
    
}
