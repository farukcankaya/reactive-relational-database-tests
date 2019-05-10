package com.trendyol.hermes.reactorblockingjdbcandjpa;

import com.trendyol.hermes.reactorblockingjdbcandjpa.jdbc.JDBCDao;
import com.trendyol.hermes.reactorblockingjdbcandjpa.jdbc.ReactorExtension;
import com.trendyol.hermes.reactorblockingjdbcandjpa.jdbc.ReactorService;
import java.time.Duration;
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
    private final JDBCDao jdbcDao;
    private final ReactorExtension reactorService;
    
    public Controller(AddressRepository repository, JDBCDao jdbcDao,
        ReactorService reactorService) {
        this.repository = repository;
        this.jdbcDao = jdbcDao;
        this.reactorService = reactorService;
    }
    
    @GetMapping("/jpa")
    public Flux<Address> jpa() {
        log.info("jpa data fetch started thread:{} {}", Thread.currentThread().getName(),
            Thread.currentThread().getId());
        
        return Flux.fromIterable(repository.findLast());
    }
    
    @GetMapping("/jdbc")
    public Flux<Address> jdbc() {
        log.info("JDBC data fetch started thread:{} {}", Thread.currentThread().getName(),
            Thread.currentThread().getId());
        
        return reactorService.deferFlux(() -> Flux.fromIterable(jdbcDao.findLast()))
            .timeout(Duration.ofSeconds(30));
    }
}
