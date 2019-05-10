package com.trendyol.hermes.reactorblockingjdbcandjpa.jdbc;

import com.trendyol.hermes.reactorblockingjdbcandjpa.Address;
import com.trendyol.hermes.reactorblockingjdbcandjpa.AddressMapper;
import java.util.List;
import lombok.extern.slf4j.Slf4j;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

@Component
@Slf4j
public class JDBCDao {
    
    private JdbcTemplate jdbcTemplate;
    
    private static final String GET_ADDRESS_SQL = "SELECT * FROM address WHERE 1=1 ORDER BY id DESC LIMIT 300;";
    private static final AddressMapper ADDRESS_MAPPER = new AddressMapper();
    
    public JDBCDao(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }
    
    public List<Address> findLast() {
        log.info("JDBCDao started thread {} {}", Thread.currentThread().getName(), Thread.currentThread().getId());
        
        List<Address> addresses = jdbcTemplate.query(GET_ADDRESS_SQL, ADDRESS_MAPPER);
        
        log.info("JDBCDao finished thread {} {}", Thread.currentThread().getName(), Thread.currentThread().getId());
        
        return addresses;
    }
}
