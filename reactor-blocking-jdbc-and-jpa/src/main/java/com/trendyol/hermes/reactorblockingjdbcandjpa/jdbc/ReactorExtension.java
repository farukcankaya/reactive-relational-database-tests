package com.trendyol.hermes.reactorblockingjdbcandjpa.jdbc;

import java.time.Duration;
import java.util.function.Supplier;
import reactor.core.publisher.Flux;
import reactor.core.scheduler.Scheduler;

public interface ReactorExtension {
    Scheduler getScheduler();
    
    Duration getTimeout();
    
    default <T> Flux<T> deferFlux(Supplier<Flux<T>> supplier) {
        return Flux.defer(supplier)
            .publishOn(getScheduler())
            .subscribeOn(getScheduler())
            .timeout(getTimeout());
    }
}
