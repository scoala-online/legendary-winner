package org.scoalaonline.api.repo;

import org.scoalaonline.api.model.ProductCart;
import org.scoalaonline.api.model.ProductCartKey;
import org.scoalaonline.api.model.Store;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductCartRepo extends JpaRepository<ProductCart, ProductCartKey> {

}
