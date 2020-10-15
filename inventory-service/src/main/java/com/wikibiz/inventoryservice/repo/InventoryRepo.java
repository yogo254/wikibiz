package com.wikibiz.inventoryservice.repo;

import java.util.List;

import com.wikibiz.inventoryservice.model.Inventory;

import org.springframework.data.mongodb.repository.MongoRepository;

public interface InventoryRepo extends MongoRepository<Inventory, String> {

    List<Inventory> findAllBySellerId(String sellerId);

    List<Inventory> findAllByProductId(String productId);

}
