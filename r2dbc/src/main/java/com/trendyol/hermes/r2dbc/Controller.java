package com.trendyol.hermes.r2dbc;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Flux;

@RestController
@RequestMapping("/api")
@Slf4j
public class Controller {
    
    private final AddressRepository repository;
    
    public Controller(AddressRepository repository) {
        this.repository = repository;
    }
    
    @GetMapping("/r2dbc")
    public Flux<Address> r2dbc() {
        log.info("RD2BC data fetch started thread:{} {}", Thread.currentThread().getName(),
            Thread.currentThread().getId());
        
        return repository.findAll();
    }
}
