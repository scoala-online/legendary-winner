package org.scoalaonline.api.controller;

import org.scoalaonline.api.exception.ResourceNotFoundException;
import org.scoalaonline.api.model.ProductCart;
import org.scoalaonline.api.model.ProductCartKey;
import org.scoalaonline.api.model.Store;
import org.scoalaonline.api.service.ProductCartService;
import org.scoalaonline.api.service.StoreService;
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
  private ProductCartService productcartService;

  @GetMapping(value = {"", "/"})
  public ResponseEntity<List<ProductCart>> getAllProductCarts() {
    List<ProductCart> productcartList = productcartService.getAllProductCarts();
    return new ResponseEntity<>(productcartList, HttpStatus.OK);
  }

  @GetMapping(value = "/{id}")
  public ResponseEntity<ProductCart> getProductCartById(@PathVariable("id") ProductCartKey id){
    ProductCart productcart = productcartService.getProductCartByID(id)
      .orElseThrow(() -> new ResponseStatusException(
        HttpStatus.NOT_FOUND, "No Store found with this ID", new ResourceNotFoundException()
      ));
    return new ResponseEntity<>(productcart, HttpStatus.OK);
  }

  @PostMapping(value = {"", "/"})
  public ResponseEntity<ProductCart> addStore(@RequestBody ProductCart productcart) {
    ProductCart savedProductCart = productcartService.addProductCart(productcart);
    return new ResponseEntity<>(savedProductCart, HttpStatus.CREATED);
  }

  @PutMapping(value = ("/{id}"))
  public ResponseEntity<ProductCart> updateProductCart (@PathVariable("id") ProductCartKey id,
                                            @RequestBody ProductCart productcart) {
    if (productcartService.productcartExists(id)) {
      ProductCart updatedProductCart = productcartService.updateProductCart(id, productcart);
      return new ResponseEntity<>(updatedProductCart, HttpStatus.ACCEPTED);
    } else {
      throw new ResponseStatusException(
        HttpStatus.NOT_FOUND, "Cannot update non-existing Product Cart", new ResourceNotFoundException()
      );
    }
  }

  @DeleteMapping(value = ("/{id}"))
  public ResponseEntity<HttpStatus> deleteProductCart(@PathVariable("id") ProductCartKey id) {
    if (productcartService.productcartExists(id)) {
      productcartService.deleteProductCart(id);
      return new ResponseEntity<>(HttpStatus.ACCEPTED);
    } else {
      throw new ResponseStatusException(
        HttpStatus.NOT_FOUND, "Cannot delete non-existing Product Cart", new ResourceNotFoundException()
      );
    }
  }
}

