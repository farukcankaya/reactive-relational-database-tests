package com.trendyol.hermes.r2dbc;

import java.io.Serializable;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Column;
import org.springframework.data.relational.core.mapping.Table;

@Table("address")
public class Address implements Serializable {
    
    private static final long serialVersionUID = 5121486403684526871L;
    
    @Id
    private Long id;
    
    @Column("address_hash")
    private String addressHash;
    
    @Column("address_text")
    private String addressText;
    
    public Long getId() {
        return id;
    }
    
    public void setId(Long id) {
        this.id = id;
    }
    
    public String getAddressHash() {
        return addressHash;
    }
    
    public void setAddressHash(String addressHash) {
        this.addressHash = addressHash;
    }
    
    public String getAddressText() {
        return addressText;
    }
    
    public void setAddressText(String addressText) {
        this.addressText = addressText;
    }
    
    @Override
    public String toString() {
        return new org.apache.commons.lang3.builder.ToStringBuilder(this)
            .append("id", id)
            .append("addressHash", addressHash)
            .append("addressText", addressText)
            .toString();
    }
}
