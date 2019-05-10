package com.trendyol.hermes.blockingio;

import java.util.List;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequestMapping("/api")
public class AddressController {
  private final AddressRepository repository;

  public AddressController(AddressRepository repository) {
    this.repository = repository;
  }

  @GetMapping("/jpa")
  public ResponseEntity<List<Address>> jpa() {
    log.info("jpa data fetch started thread:{} {}", Thread.currentThread().getName(), Thread.currentThread().getId());
    List<Address> all = repository.findLast();
    log.info("jpa data fetch started finished:{} {}", Thread.currentThread().getName(), Thread.currentThread().getId());
    return new ResponseEntity<>(all, HttpStatus.OK);
  }
}
