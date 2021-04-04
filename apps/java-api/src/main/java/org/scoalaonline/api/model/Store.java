package org.scoalaonline.api.model;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "store")
public class Store {
  @Id
  private long storeID;

  private String name;

  private String address;

//  private Set<Customer> customer;
//  private List<Product> products;
}
