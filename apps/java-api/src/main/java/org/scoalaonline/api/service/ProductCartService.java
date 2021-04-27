package org.scoalaonline.api.service;

import org.scoalaonline.api.model.ProductCart;
import org.scoalaonline.api.model.ProductCartKey;
import org.scoalaonline.api.repo.CustomerRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.scoalaonline.api.repo.ProductCartRepo;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ProductCartService {
  @Autowired
  private ProductCartRepo productcartRepo;

  public List<ProductCart> getAllProductCarts() {
    return productcartRepo.findAll();
  }

  public Optional<ProductCart> getProductCartByID(ProductCartKey id) {
    return productcartRepo.findById(id);
  }

  public ProductCart addProductCart(ProductCart productcart) {
    // Clear the ID supplied by the user
    productcart.setId(null);

    // Save the new product cart
    return productcartRepo.save(productcart);
  }

  public ProductCart updateProductCart(ProductCartKey id, ProductCart productcart) {
    Optional<ProductCart> productcartToUpdate = productcartRepo.findById(id);

    // Check that the product cart exists
    if (productcartToUpdate.isEmpty()) {
      // If the product cart with the given ID doesn't exist, return null
      return null;
    }

    // Copy the ID of the existing object into the updated object
    productcart.setId(productcartToUpdate.get().getId());

    // Save the updated product cart with the right ID so that the old one gets overwritten
    return productcartRepo.save(productcart);
  }

  public void deleteProductCart(ProductCartKey id) {
    productcartRepo.deleteById(id);
  }

  public boolean productcartExists(ProductCartKey id) {
    return productcartRepo.existsById(id);
  }

}
