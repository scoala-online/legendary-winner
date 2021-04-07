package org.scoalaonline.api.model;

import javax.persistence.*;
import java.util.Objects;
import java.util.Set;

@Entity
@Table(name = "stores")
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
  private Set<Customer> customers;

  @OneToMany(mappedBy = "store")
  private Set<Product> products;

  public Store(long storeID, String name, String address, Set<Customer> customers, Set<Product> products) {
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

  public Set<Customer> getCustomers() {
    return customers;
  }

  public void setCustomers(Set<Customer> customers) {
    this.customers = customers;
  }

  public Set<Product> getProducts() {
    return products;
  }

  public void setProducts(Set<Product> products) {
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
