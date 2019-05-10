package com.trendyol.hermes.reactivepg;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.MediaType;
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
    
    @GetMapping(value = "/reactive-pg", produces = MediaType.APPLICATION_STREAM_JSON_VALUE)
    public Flux<Address> jdbc() {
        log.info("Reactivepg data fetch started thread:{} {}", Thread.currentThread().getName(),
            Thread.currentThread().getId());
        
        return repository.findAll();
    }
}
