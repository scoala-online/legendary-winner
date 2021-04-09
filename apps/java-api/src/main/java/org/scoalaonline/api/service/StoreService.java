package org.scoalaonline.api.service;

import org.scoalaonline.api.model.Store;
import org.scoalaonline.api.repo.StoreRepo;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;
import java.util.Optional;

public class StoreService {

  @Autowired
  private StoreRepo storeRepo;

  public List<Store> getAllStores() {
    return storeRepo.findAll();
  }

  public Optional<Store> getStoreById(long id) {
    return storeRepo.findById(id);
  }

  public Store addStore(Store store) {
    // Clear the ID supplied by the user
    store.setStoreID(0);

    // Save the new store
    return storeRepo.save(store);
  }

  public Store updateStore(long id, Store store) {
    Optional<Store> storeToUpdate = storeRepo.findById(id);

    // Check that the store exists
    if (storeToUpdate.isEmpty()) {
      // If the store with the given ID doesn't exist, return null
      return null;
    }

    // Copy the ID of the existing object into the updated object
    store.setStoreID(storeToUpdate.get().getStoreID());

    // Save the updated store with the right ID so that the old one gets overwritten
    return storeRepo.save(store);
  }

  public void deleteStore(long id) {
    storeRepo.deleteById(id);
  }

  public boolean storeExists(long id) {
    return storeRepo.existsById(id);
  }
}
