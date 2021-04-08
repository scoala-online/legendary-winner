package org.scoalaonline.api.repo;

import org.scoalaonline.api.model.Store;
import org.springframework.data.jpa.repository.JpaRepository;

public interface StoreRepo extends JpaRepository<Store, Long> {

}
