package org.scoalaonline.api.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "products")

public class Product {

    @Id
    @Column(name = "product_id")
    private long productID;
    @Column(name = "brand", nullable = false, length = 20)
    private String brand;
    @Column(name = "product_name", nullable = false, length = 20)
    private String productName;
    @Column(name = "quantity")
    private int quantity;

  public Product(long productID, String brand, String productName, int quantity) {
    this.productID = productID;
    this.brand = brand;
    this.productName = productName;
    this.quantity = quantity;
  }

  public long getProductID() {
    return productID;
  }

  public void setProductID(long productID) {
    this.productID = productID;
  }

  public String getBrand() {
    return brand;
  }

  public void setBrand(String brand) {
    this.brand = brand;
  }

  public String getProductName() {
    return productName;
  }

  public void setProductName(String productName) {
    this.productName = productName;
  }

  public int getQuantity() {
    return quantity;
  }

  public void setQuantity(int quantity) {
    this.quantity = quantity;
  }
}

