package com.trendyol.hermes.r2dbc;

import org.springframework.data.r2dbc.repository.R2dbcRepository;
import org.springframework.data.r2dbc.repository.query.Query;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Flux;

@Repository
public interface AddressRepository extends R2dbcRepository<Address, Integer> {

  @Query("SELECT * FROM address WHERE 1=1 ORDER BY id DESC LIMIT 300")
  Flux<Address> findAll();
}
