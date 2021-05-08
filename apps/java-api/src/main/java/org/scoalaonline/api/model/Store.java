package org.scoalaonline.api.model;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import org.scoalaonline.api.serializer.ProductSerializer;

import javax.persistence.*;
import java.util.List;
import java.util.Objects;

@Entity
@Table(name = "stores")
@JsonIdentityInfo(
  generator = ObjectIdGenerators.PropertyGenerator.class,
  property = "storeID")
public class Store {
  @Id
  @GeneratedValue
  @Column(name = "store_id", nullable = false)
  private long storeID;

  @Column(name = "name", nullable = false, length = 255)
  private String name;

  @Column(name = "address", nullable = false, length = 255)
  private String address;

  @OneToMany(mappedBy = "store")
  private List<Customer> customers;

  @OneToMany(mappedBy = "store")
  @JsonSerialize(using = ProductSerializer.class)
  private List<Product> products;

  public Store(long storeID, String name, String address, List<Customer> customers, List<Product> products) {
    this.storeID = storeID;
    this.name = name;
    this.address = address;
    this.customers = customers;
    this.products = products;
  }

  public Store() {

  }

  public long getStoreID() {
    return storeID;
  }

  public void setStoreID(long storeID) {
    this.storeID = storeID;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public String getAddress() {
    return address;
  }

  public void setAddress(String address) {
    this.address = address;
  }

  public List<Customer> getCustomers() {
    return customers;
  }

  public void setCustomers(List<Customer> customers) {
    this.customers = customers;
  }

  public List<Product> getProducts() {
    return products;
  }

  public void setProducts(List<Product> products) {
    this.products = products;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (o == null || getClass() != o.getClass()) return false;
    Store store = (Store) o;
    return storeID == store.storeID && name.equals(store.name) && address.equals(store.address) && customers.equals(store.customers) && products.equals(store.products);
  }

  @Override
  public int hashCode() {
    return Objects.hash(storeID, name, address, customers, products);
  }
}
