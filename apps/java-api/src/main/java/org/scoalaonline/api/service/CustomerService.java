package org.scoalaonline.api.service;

import org.scoalaonline.api.model.Customer;
import org.scoalaonline.api.repo.CustomerRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CustomerService {

  @Autowired
  private CustomerRepo customerRepo;

  public List<Customer> getAllCustomers() {
    return customerRepo.findAll();
  }

  public Optional<Customer> getCustomerById(long id) {
    return customerRepo.findById(id);
  }

  public Customer addCustomer(Customer customer) {
    // Clear the ID supplied by the user
    customer.setCustomerID(0);

    // Save the new customer
    return customerRepo.save(customer);
  }

  public Customer updateCustomer(long id, Customer customer) {
    Optional<Customer> customerToUpdate = customerRepo.findById(id);

    // Check that the customer exists
    if (customerToUpdate.isEmpty()) {
      // If the customer with the given ID doesn't exist, return null
      return null;
    }

    // Copy the ID of the existing object into the updated object
    customer.setCustomerID(customerToUpdate.get().getCustomerID());

    // Save the updated customer with the right ID so that the old one gets overwritten
    return customerRepo.save(customer);
  }

  public void deleteCustomer(long id) {
    customerRepo.deleteById(id);
  }

  public boolean customerExists(long id) {
    return customerRepo.existsById(id);
  }
}
