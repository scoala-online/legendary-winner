package org.scoalaonline.api.repo;

import org.scoalaonline.api.model.Product;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductRepo extends JpaRepository<Product, Long> { // data base access
}
