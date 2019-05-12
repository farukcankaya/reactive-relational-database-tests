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
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/api")
@Slf4j
public class Controller {
    
    private final AddressRepository repository;
    private final CityRepository cityRepository;
    private final JDBCDao jdbcDao;
    private final ReactorExtension reactorService;
    
    public Controller(AddressRepository repository,
        CityRepository cityRepository, JDBCDao jdbcDao,
        ReactorService reactorService) {
        this.repository = repository;
        this.cityRepository = cityRepository;
        this.jdbcDao = jdbcDao;
        this.reactorService = reactorService;
    }
    
    @GetMapping("/jpa")
    public Flux<Address> jpa() {
        log.info("jpa data fetch started thread:{} {}", Thread.currentThread().getName(),
            Thread.currentThread().getId());
        
        return Flux.fromIterable(repository.findLast());
    }
    
    @GetMapping("/jpa-city")
    public Mono<City> jpaCity() {
        log.info("jpa data fetch started thread:{} {}", Thread.currentThread().getName(),
            Thread.currentThread().getId());
        
        return Mono.justOrEmpty(cityRepository.findById(1L));
    }
    
    @GetMapping("/jdbc")
    public Flux<Address> jdbc() {
        log.info("JDBC data fetch started thread:{} {}", Thread.currentThread().getName(),
            Thread.currentThread().getId());
        
        return reactorService.deferFlux(() -> Flux.fromIterable(jdbcDao.findLast()))
            .timeout(Duration.ofSeconds(30));
    }
}
