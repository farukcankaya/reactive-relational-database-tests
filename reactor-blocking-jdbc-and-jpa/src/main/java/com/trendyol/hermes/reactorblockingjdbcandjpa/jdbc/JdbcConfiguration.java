package com.trendyol.hermes.reactorblockingjdbcandjpa.jdbc;

import java.time.Duration;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Configuration
@ConfigurationProperties("jdbc")
public class JdbcConfiguration {
    
    private int timeoutMillis;
    
    public int getTimeoutMillis() {
        return timeoutMillis;
    }
    
    public Duration getTimeoutDuration() {
        return Duration.ofMillis(timeoutMillis);
    }
    
    public void setTimeoutMillis(int timeoutMillis) {
        this.timeoutMillis = timeoutMillis;
    }
}
