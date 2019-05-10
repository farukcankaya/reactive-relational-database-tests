package com.trendyol.hermes.reactivepg;

import io.reactiverse.reactivex.pgclient.PgPool;
import io.reactiverse.reactivex.pgclient.Row;
import io.reactiverse.reactivex.pgclient.Tuple;
import io.reactivex.Flowable;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import reactor.adapter.rxjava.RxJava2Adapter;
import reactor.core.publisher.Flux;
import reactor.core.scheduler.Scheduler;

@Component
@Slf4j
public class AddressRepository {
    
    private final PgPool client;
    private final Scheduler scheduler;
    
    public AddressRepository(PgPool client, Scheduler scheduler) {
        this.client = client;
        this.scheduler = scheduler;
    }
    
    public Flux<Address> findAll() {
        return executeFluxQuery("SELECT * FROM address WHERE 1=1 ORDER BY id DESC LIMIT 300", Tuple.tuple())
            .map(row -> {
                Address address = new Address();
                address.setId(row.getLong("id"));
                address.setAddressText(row.getString("address_text"));
                address.setAddressHash(row.getString("address_hash"));
                return address;
            });
    }
    
    private Flux<Row> executeFluxQuery(String query, Tuple args) {
        log.debug("executing {} {}", query, args);
        
        Flowable<Row> flowable = client
            .rxBegin()
            .flatMapPublisher(tx ->
                tx.rxPrepare(query)
                    .flatMapPublisher(pq -> pq.createStream(1000, args).toFlowable())
                    .doFinally(tx::commit));
        
        return RxJava2Adapter.flowableToFlux(flowable).publishOn(scheduler);
    }
}
