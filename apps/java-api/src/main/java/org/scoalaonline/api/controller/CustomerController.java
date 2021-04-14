package org.scoalaonline.api.controller;

import org.scoalaonline.api.exception.ResourceNotFoundException;
import org.scoalaonline.api.model.Customer;
import org.scoalaonline.api.model.Store;
import org.scoalaonline.api.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@RestController
@RequestMapping("/customers")
public class CustomerController {

  @Autowired
  private CustomerService customerService;

  @GetMapping(value = {"", "/"})
  public ResponseEntity<List<Customer>> getAllCustomer() {
    List<Customer> customerList = customerService.getAllCustomers();
    return new ResponseEntity<>(customerList, HttpStatus.OK);
  }

  @GetMapping(value = "/{id}")
  public ResponseEntity<Customer> getCustomerById(@PathVariable("id") long id){
    Customer customer = customerService.getCustomerById(id)
      .orElseThrow(() -> new ResponseStatusException(
        HttpStatus.NOT_FOUND, "No Customer found with this ID", new ResourceNotFoundException()
      ));
    return new ResponseEntity<>(customer, HttpStatus.OK);
  }

  @PostMapping(value = {"", "/"})
  public ResponseEntity<Customer> addCustomer(@RequestBody Customer customer) {
    Customer savedCustomer = customerService.addCustomer(customer);
    return new ResponseEntity<>(savedCustomer, HttpStatus.CREATED);
  }
}
