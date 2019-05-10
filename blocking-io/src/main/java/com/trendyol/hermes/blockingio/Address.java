package com.trendyol.hermes.blockingio;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

@Entity
@Table(name = "address")
@SequenceGenerator(name = "seq_address", sequenceName = "seq_address")
public class Address implements Serializable {

  private static final long serialVersionUID = 5121486403684526871L;

  @Id
  @Column(name = "id")
  @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "seq_address")
  private Long id;

  @Column(name = "address_hash")
  private String addressHash;

  @Column(name = "address_text")
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
