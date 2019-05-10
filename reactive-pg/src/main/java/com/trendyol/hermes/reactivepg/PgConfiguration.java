package com.trendyol.hermes.reactivepg;

import io.reactiverse.pgclient.PgPoolOptions;
import io.reactiverse.reactivex.pgclient.PgClient;
import io.reactiverse.reactivex.pgclient.PgPool;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class PgConfiguration {
  private static final int CONNECT_TIMEOUT_IN_MILLIS = 3000;
  private static final int RECONNECT_ATTEMPTS = 3;
  private static final int IDLE_CONNECTION_TIMEOUT_IN_SECONDS = 45;

  @Bean
  public PgPool client(PgSettings pgSettings) {
    PgPoolOptions pgPoolOptions =
        new PgPoolOptions()
            .setHost(pgSettings.getHost())
            .setPort(pgSettings.getPort())
            .setDatabase(pgSettings.getDatabase())
            .setUser(pgSettings.getUsername())
            .setPassword(pgSettings.getPassword())
            .setConnectTimeout(CONNECT_TIMEOUT_IN_MILLIS)
            .setCachePreparedStatements(Boolean.TRUE)
            .setReconnectAttempts(RECONNECT_ATTEMPTS)
            .setIdleTimeout(IDLE_CONNECTION_TIMEOUT_IN_SECONDS)
            .setMaxSize(pgSettings.getPoolSize());

    return PgClient.pool(pgPoolOptions);
  }
}
