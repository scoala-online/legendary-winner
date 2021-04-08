package org.scoalaonline.api.model;

import javax.persistence.*;
import java.util.Objects;

@Entity
public class ProductCart {
  @EmbeddedId
  private ProductCartKey id;

  @ManyToOne
  @MapsId("customerID")
  @JoinColumn(name = "customer_id")
  private Customer customer;

  @ManyToOne
  @MapsId("productID")
  @JoinColumn(name = "product_id")
  private Product product;

  private int amount;

  public ProductCart(ProductCartKey id, Customer customer, Product product, int amount) {
    this.id = id;
    this.customer = customer;
    this.product = product;
    this.amount = amount;
  }

  public ProductCart()
  {
  }

  public ProductCartKey getId() {
    return id;
  }

  public void setId(ProductCartKey id) {
    this.id = id;
  }

  public Customer getCustomer() {
    return customer;
  }

  public void setCustomer(Customer customer) {
    this.customer = customer;
  }

  public Product getProduct() {
    return product;
  }

  public void setProduct(Product product) {
    this.product = product;
  }

  public int getAmount() {
    return amount;
  }

  public void setAmount(int amount) {
    this.amount = amount;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (o == null || getClass() != o.getClass()) return false;
    ProductCart that = (ProductCart) o;
    return amount == that.amount && id.equals(that.id) && customer.equals(that.customer) && product.equals(that.product);
  }

  @Override
  public int hashCode() {
    return Objects.hash(id, customer, product, amount);
  }
}
