package org.scoalaonline.api.model;

import javax.persistence.*;
import java.util.List;
import java.util.Objects;

@Entity
@Table(name = "products")
public class Product {
  @Id
  @GeneratedValue
  @Column(name = "product_id", nullable = false)
  private long productID;

  @Column(name = "brand", nullable = false, length = 255)
  private String brand;

  @Column(name = "product_name", nullable = false, length = 255)
  private String productName;

  @Column(columnDefinition = "integer default 0")
  private int quantity;

  @ManyToOne
  @JoinColumn(name = "store_id")
  private Store store;

  @OneToMany(mappedBy = "product")
  private List<ProductCart> customers;

  public Product(long productID, String brand, String productName, int quantity) {
    this.productID = productID;
    this.brand = brand;
    this.productName = productName;
    this.quantity = quantity;
  }

  public Product() {

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

  public Store getStore() {
    return store;
  }

  public void setStore(Store store) {
    this.store = store;
  }

  public List<ProductCart> getCustomers() {
    return customers;
  }

  public void setCustomers(List<ProductCart> customers) {
    this.customers = customers;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (o == null || getClass() != o.getClass()) return false;
    Product product = (Product) o;
    return productID == product.productID && quantity == product.quantity && brand.equals(product.brand) && productName.equals(product.productName) && store.equals(product.store) && customers.equals(product.customers);
  }

  @Override
  public int hashCode() {
    return Objects.hash(productID, brand, productName, quantity, store, customers);
  }
}

