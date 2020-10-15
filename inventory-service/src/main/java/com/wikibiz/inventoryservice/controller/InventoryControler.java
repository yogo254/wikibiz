package com.wikibiz.inventoryservice.controller;

import java.util.List;

import com.wikibiz.inventoryservice.model.Inventory;
import com.wikibiz.inventoryservice.repo.InventoryRepo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/inventory")
public class InventoryControler {
    @Autowired
    private InventoryRepo inventoryRepo;

    @GetMapping("/all")

    public List<Inventory> findAll() {
        return inventoryRepo.findAll();

    }

    @GetMapping("/seller/{id}")

    public List<Inventory> findAllBySeller(@PathVariable("id") String id) {
        return inventoryRepo.findAllBySellerId(id);
    }

    @GetMapping("/product/{id}")
    public List<Inventory> findAllByProduct(@PathVariable("id") String id) {
        return inventoryRepo.findAllByProductId(id);
    }

    @PostMapping()
    public String addInventory(@RequestBody Inventory inventory) {
        inventoryRepo.save(inventory);

        return "inventory added successfully";
    }

}
