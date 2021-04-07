package org.scoalaonline.api.model;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Set;

@Entity
@Table(name = "customers")
public class Customer {
  @Id
  @GeneratedValue
  @Column(name = "customer_id", nullable = false)
  private long customerID;

  @Column(name = "first_name", nullable = false, length = 255)
  private String firstName;

  @Column(name = "last_name", nullable = false, length = 255)
  private String lastName;

  @ManyToMany
  @JoinTable(
    name = "product_bought",
    joinColumns = @JoinColumn(name = "customer_id"),
    inverseJoinColumns = @JoinColumn(name = "product_id"))
  private List<Product> products;

  @ManyToOne
  @JoinColumn(name="store_id")
  private Store store;

  public Customer(long id, String firstName, String lastName) {
    this.customerID = id;
    this.firstName = firstName;
    this.lastName = lastName;
  }

  public Customer(long id, String firstName, String lastName, List<Product> products) {
    this.customerID = id;
    this.firstName = firstName;
    this.lastName = lastName;
    this.products = products;
  }

  public Customer() {

  }

  public long getCustomerID() {
    return customerID;
  }

  public void setCustomerID(long customerID) {
    this.customerID = customerID;
  }

  public String getFirstName() {
    return firstName;
  }

  public void setFirstName(String firstName) {
    this.firstName = firstName;
  }

  public String getLastName() {
    return lastName;
  }

  public void setLastName(String lastName) {
    this.lastName = lastName;
  }

  public List<Product> getProducts() {
    return products;
  }

  public void setProducts(List<Product> products) {
    this.products = products;
  }

  public Store getStore() {
    return store;
  }

  public void setStore(Store store) {
    this.store = store;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (o == null || getClass() != o.getClass()) return false;
    Customer customer = (Customer) o;
    return customerID == customer.customerID && firstName.equals(customer.firstName) && lastName.equals(customer.lastName) && products.equals(customer.products) && store.equals(customer.store);
  }

  @Override
  public int hashCode() {
    return Objects.hash(customerID, firstName, lastName, products, store);
  }
}
