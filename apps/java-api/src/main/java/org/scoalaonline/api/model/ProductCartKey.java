package org.scoalaonline.api.model;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import java.io.Serializable;
import java.util.Objects;

@Embeddable
public class ProductCartKey implements Serializable {
  @Column(name = "product_id")
  private long productID;

  @Column(name = "customer_id")
  private long customerID;

  public ProductCartKey(long productID, long customerID) {
    this.productID = productID;
    this.customerID = customerID;
  }

  public ProductCartKey() {

  }

  public long getProductID() {
    return productID;
  }

  public void setProductID(long productID) {
    this.productID = productID;
  }

  public long getCustomerID() {
    return customerID;
  }

  public void setCustomerID(long customerID) {
    this.customerID = customerID;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (o == null || getClass() != o.getClass()) return false;
    ProductCartKey that = (ProductCartKey) o;
    return productID == that.productID && customerID == that.customerID;
  }

  @Override
  public int hashCode() {
    return Objects.hash(productID, customerID);
  }
}
