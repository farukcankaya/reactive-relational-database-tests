package com.trendyol.hermes.reactivepg;

import java.io.Serializable;

public class Address implements Serializable {

  private static final long serialVersionUID = 5121486403684526871L;

  private Long id;

  private String addressHash;

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
