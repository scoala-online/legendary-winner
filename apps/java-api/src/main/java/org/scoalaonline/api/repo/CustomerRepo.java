package org.scoalaonline.api.repo;

import org.scoalaonline.api.model.Customer;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CustomerRepo extends JpaRepository<Customer, Long> {
}
