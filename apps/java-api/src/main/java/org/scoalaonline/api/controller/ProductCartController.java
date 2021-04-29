package org.scoalaonline.api.controller;

import org.scoalaonline.api.exception.ResourceNotFoundException;
import org.scoalaonline.api.model.ProductCart;
import org.scoalaonline.api.model.ProductCartKey;
import org.scoalaonline.api.service.ProductCartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@RestController
@RequestMapping("/cart")

public class ProductCartController {

  @Autowired
  private ProductCartService productCartService;

  @GetMapping(value = {"", "/"})
  public ResponseEntity<List<ProductCart>> getAllProductCarts() {
    List<ProductCart> productCartList = productCartService.getAllProductCarts();
    return new ResponseEntity<>(productCartList, HttpStatus.OK);
  }

  @GetMapping(value = "/{productID},{customerID}")
  public ResponseEntity<ProductCart> getProductCartById(@PathVariable("productID") long productID, @PathVariable("customerID") long customerID){
    ProductCartKey key = new ProductCartKey(productID, customerID);
    ProductCart productcart = productCartService.getProductCartByID(key)
      .orElseThrow(() -> new ResponseStatusException(
        HttpStatus.NOT_FOUND, "No Store found with this ID", new ResourceNotFoundException()
      ));
    return new ResponseEntity<>(productcart, HttpStatus.OK);
  }

  @PostMapping(value = {"", "/"})
  public ResponseEntity<ProductCart> addStore(@RequestBody ProductCart productcart) {
    ProductCart savedProductCart = productCartService.addProductCart(productcart);
    return new ResponseEntity<>(savedProductCart, HttpStatus.CREATED);
  }

  @PutMapping(value = ("/{productID},{customerID}"))
  public ResponseEntity<ProductCart> updateProductCart (@PathVariable("productID") long productID, @PathVariable("customerID") long customerID,
                                            @RequestBody ProductCart productcart) {
    ProductCartKey key = new ProductCartKey(productID, customerID);
    if (productCartService.productCartExists(key)) {
      ProductCart updatedProductCart = productCartService.updateProductCart(key, productcart);
      return new ResponseEntity<>(updatedProductCart, HttpStatus.ACCEPTED);
    } else {
      throw new ResponseStatusException(
        HttpStatus.NOT_FOUND, "Cannot update non-existing Product Cart", new ResourceNotFoundException()
      );
    }
  }

  @DeleteMapping(value = ("/{productID},{customerID}"))
  public ResponseEntity<HttpStatus> deleteProductCart(@PathVariable("productID") long productID, @PathVariable("customerID") long customerID) {
    ProductCartKey key = new ProductCartKey(productID, customerID);
    if (productCartService.productCartExists(key)) {
      productCartService.deleteProductCart(key);
      return new ResponseEntity<>(HttpStatus.ACCEPTED);
    } else {
      throw new ResponseStatusException(
        HttpStatus.NOT_FOUND, "Cannot delete non-existing Product Cart", new ResourceNotFoundException()
      );
    }
  }
}

