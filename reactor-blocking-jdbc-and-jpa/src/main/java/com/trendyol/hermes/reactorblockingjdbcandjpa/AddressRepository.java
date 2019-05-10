package com.trendyol.hermes.reactorblockingjdbcandjpa;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface AddressRepository extends JpaRepository<Address, Long> {

  @Query(value = "SELECT * FROM address WHERE 1=1 ORDER BY id DESC LIMIT 300", nativeQuery = true)
  List<Address> findLast();
}
