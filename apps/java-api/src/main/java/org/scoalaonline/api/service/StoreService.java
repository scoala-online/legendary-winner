package org.scoalaonline.api.service;

import org.scoalaonline.api.model.Store;
import org.scoalaonline.api.repo.StoreRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class StoreService {

  @Autowired
  StoreRepo storeRepo;

  public List<Store> getAllStores () {
    return storeRepo.findAll();
  }

  public Optional<Store> getStoresById (long id) {
    return storeRepo.findById(id);
  }

  public Store addStores (Store store) {
    Store storeToSave = new Store();
    storeToSave.setName(store.getName());

    return storeRepo.save(store);
  }

  public Store updateStores (long id, Store store) {
    Store storeToUpdate = storeRepo.findById(id).get();

    if (store.getName() != null) {
      storeToUpdate.setName(store.getName());
    }

    return storeRepo.save(storeToUpdate);
  }

  public void deleteStores (long id) {
    storeRepo.deleteById(id);
  }

  public boolean storeExists (long id) {
    return storeRepo.existsById(id);
  }
}
