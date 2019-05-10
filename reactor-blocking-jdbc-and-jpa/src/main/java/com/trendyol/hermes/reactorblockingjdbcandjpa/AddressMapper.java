package com.trendyol.hermes.reactorblockingjdbcandjpa;

import java.sql.ResultSet;
import java.sql.SQLException;
import org.springframework.jdbc.core.RowMapper;

public class AddressMapper implements RowMapper<Address> {
    
    @Override
    public Address mapRow(ResultSet resultSet, int i) throws SQLException {
        Address address = new Address();
        address.setId(resultSet.getLong("id"));
        address.setAddressText(resultSet.getString("address_text"));
        address.setAddressHash(resultSet.getString("address_hash"));
        return address;
    }
}
