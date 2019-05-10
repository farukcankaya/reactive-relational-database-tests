package com.trendyol.hermes.reactivepg;

import lombok.Getter;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;

@Configuration
@Getter
@Setter
public class PgSettings {
  @Value("${reactive.pg.host}")
  private String host;

  @Value("${reactive.pg.port}")
  private Integer port;

  @Value("${reactive.pg.database}")
  private String database;

  @Value("${reactive.pg.username}")
  private String username;

  @Value("${reactive.pg.password}")
  private String password;

  @Value("${reactive.pg.poolsize}")
  private Integer poolSize;
}
