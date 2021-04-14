package org.scoalaonline.api.controller;

import org.scoalaonline.api.exception.ResourceNotFoundException;
import org.scoalaonline.api.model.Store;
import org.scoalaonline.api.service.StoreService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
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
}
