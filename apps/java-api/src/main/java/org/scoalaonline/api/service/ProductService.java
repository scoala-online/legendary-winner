package org.scoalaonline.api.service;

import org.scoalaonline.api.model.Product;
import org.scoalaonline.api.model.Store;
import org.scoalaonline.api.repo.ProductRepo;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;
import java.util.Optional;

public class ProductService {

  @Autowired
  private ProductRepo productRepo;

  public List<Product> getAllProducts() {
    return productRepo.findAll();
  }

  public Optional<Product> getProductById(long id) {
    return productRepo.findById(id);
  }

  public Product addProduct(Product product) {
    // Clear the ID supplied by the user
    product.setProductID(0);

    // Save the new product
    return productRepo.save(product);
  }

  public Product updateProduct(long id, Product product) {
    Optional<Product> productToUpdate = productRepo.findById(id);

    // Check that the product exists
    if (productToUpdate.isEmpty()) {
      // If the product with the given ID doesn't exist, return null
      return null;
    }

    // Copy the ID of the existing object into the updated object
    product.setProductID(productToUpdate.get().getProductID());

    // Save the updated product with the right ID so that the old one gets overwritten
    return productRepo.save(product);
  }

  public void deleteProduct(long id) {
    productRepo.deleteById(id);
  }

  public boolean productExists(long id) {
    return productRepo.existsById(id);
  }
}
