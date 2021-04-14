package org.scoalaonline.api.controller;

import org.scoalaonline.api.exception.ResourceNotFoundException;
import org.scoalaonline.api.model.Customer;
import org.scoalaonline.api.model.Product;
import org.scoalaonline.api.model.Store;
import org.scoalaonline.api.service.ProductService;
import org.scoalaonline.api.service.StoreService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@RestController
@RequestMapping("/products")
public class ProductController {

  @Autowired
  private ProductService productService;

  @GetMapping(value = {"", "/"})
  public ResponseEntity<List<Product>> getAllProducts() {
    List<Product> productList = productService.getAllProducts();
    return new ResponseEntity<>(productList, HttpStatus.OK);
  }

  @GetMapping(value = "/{id}")
  public ResponseEntity<Product> getProductById(@PathVariable("id") long id){
    Product product = productService.getProductById(id)
      .orElseThrow(() -> new ResponseStatusException(
        HttpStatus.NOT_FOUND, "No Product found with this ID", new ResourceNotFoundException()
      ));
    return new ResponseEntity<>(product, HttpStatus.OK);
  }

  @PostMapping(value = {"", "/"})
  public ResponseEntity<Product> addProduct(@RequestBody Product product) {
    Product savedProduct = productService.addProduct(product);
    return new ResponseEntity<>(savedProduct, HttpStatus.CREATED);
  }
}
