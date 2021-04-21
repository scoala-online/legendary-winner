package org.scoalaonline.api.controller;

import org.scoalaonline.api.exception.ResourceNotFoundException;
import org.scoalaonline.api.model.Product;
import org.scoalaonline.api.model.Store;
import org.scoalaonline.api.service.StoreService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@RestController
@RequestMapping("/stores")
public class StoreController {

  @Autowired
  private StoreService storeService;

  @GetMapping(value = {"", "/"})
  public ResponseEntity<List<Store>> getAllStores() {
    List<Store> storeList = storeService.getAllStores();
    return new ResponseEntity<>(storeList, HttpStatus.OK);
  }

  @GetMapping(value = "/{id}")
  public ResponseEntity<Store> getStoreById(@PathVariable("id") long id){
    Store store = storeService.getStoreById(id)
      .orElseThrow(() -> new ResponseStatusException(
        HttpStatus.NOT_FOUND, "No Store found with this ID", new ResourceNotFoundException()
      ));
    return new ResponseEntity<>(store, HttpStatus.OK);
  }

  @PostMapping(value = {"", "/"})
  public ResponseEntity<Store> addStore(@RequestBody Store store) {
    Store savedStore = storeService.addStore(store);
    return new ResponseEntity<>(savedStore, HttpStatus.CREATED);
  }

  @DeleteMapping(value = ("/{id}"))
  public ResponseEntity<HttpStatus> deleteStore(@PathVariable("id") long id) {
    if (storeService.storeExists(id)) {
      storeService.deleteStore(id);
      return new ResponseEntity<>(HttpStatus.ACCEPTED);
    } else {
      throw new ResponseStatusException(
        HttpStatus.NOT_FOUND, "Cannot delete non-existing Store", new ResourceNotFoundException()
      );
    }
  }
}
