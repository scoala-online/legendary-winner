package org.scoalaonline.api.model;
import javax.persistence.*;
import java.util.List;
import java.util.Objects;



  @Entity
  @Table(name = "customers")
  public class Customer
  {
    //region Fields
    @Id
    @GeneratedValue
    @Column(name = "customer_id")
    private long customerID;

    @Column(name = "first_name", nullable = false, length = 20)
    private String firstName;

    @Column(name = "last_name", nullable = false, length = 20)
    private String lastName;

    @OneToMany
    private List<Product> products;

    public Customer(long id, String firstName, String lastName ) {
      this.customerID = id;
      this.firstName=firstName;
      this.lastName=lastName;
    }

    public Customer(long id, String firstName, String lastName,List<Product> products ) {
      this.customerID = id;
      this.firstName=firstName;
      this.lastName=lastName;
      this.products=products;
    }

    public Customer() {
      
    }

    //region Getters
    public long getCustomerID() {
      return customerID;
    }

    public String getFirstName()
    {
      return firstName;
    }

    public String getLastName()
    {
      return lastName;
    }

    public List<Product> getProducts() {
      return products;
    }

    //endregion

    //region Setters
    public void setCustomerID(long customerID) {
      this.customerID = customerID;
    }

    public void setFirstName(String firstName)
    {
      this.firstName = firstName;
    }

    public void setLastName(String lastName)
    {
      this.lastName = lastName;
    }
    public void setProducts(List<Product> products) {
      this.products = products;
    }

    public boolean equals(Object object) {
      if (this == object) return true;
      if (!(object instanceof Customer)) return false;
      if (!super.equals(object)) return false;
      Customer customer = (Customer) object;
      return customerID == customer.customerID &&
        java.util.Objects.equals(firstName, customer.firstName) &&
        java.util.Objects.equals(lastName, customer.lastName) &&
        java.util.Objects.equals(products, customer.products);
    }

    public int hashCode() {
      return java.util.Objects.hash(super.hashCode(), customerID, firstName, lastName, products);
    }

//endregion

}
