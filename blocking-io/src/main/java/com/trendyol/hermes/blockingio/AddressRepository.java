package com.trendyol.hermes.blockingio;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface AddressRepository extends JpaRepository<Address, Long> {
  @Query(value = "SELECT * FROM address WHERE 1=1 ORDER BY id DESC LIMIT 300", nativeQuery = true)
  List<Address> findLast();
}
