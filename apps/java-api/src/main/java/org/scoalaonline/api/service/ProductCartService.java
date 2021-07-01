package org.scoalaonline.api.service;

import org.scoalaonline.api.model.ProductCart;
import org.scoalaonline.api.model.ProductCartKey;
import org.springframework.beans.factory.annotation.Autowired;
import org.scoalaonline.api.repo.ProductCartRepo;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ProductCartService {
  @Autowired
  private ProductCartRepo productCartRepo;

  public List<ProductCart> getAllProductCarts() {
    return productCartRepo.findAll();
  }

  public Optional<ProductCart> getProductCartByID(ProductCartKey id) {
    return productCartRepo.findById(id);
  }

  public ProductCart addProductCart(ProductCart productcart) {
    // Clear the ID supplied by the user
    productcart.setId(null);

    // Save the new product cart
    return productCartRepo.save(productcart);
  }

  public ProductCart updateProductCart(ProductCartKey id, ProductCart productcart) {
    Optional<ProductCart> productCartToUpdate = productCartRepo.findById(id);

    // Check that the product cart exists
    if (productCartToUpdate.isEmpty()) {
      // If the product cart with the given ID doesn't exist, return null
      return null;
    }

    // Copy the ID of the existing object into the updated object
    productcart.setId(productCartToUpdate.get().getId());

    // Save the updated product cart with the right ID so that the old one gets overwritten
    return productCartRepo.save(productcart);
  }

  public void deleteProductCart(ProductCartKey id) {
    productCartRepo.deleteById(id);
  }

  public boolean productCartExists(ProductCartKey id) {
    return productCartRepo.existsById(id);
  }

}
