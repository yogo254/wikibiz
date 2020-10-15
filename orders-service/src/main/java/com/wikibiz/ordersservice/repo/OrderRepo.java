package com.wikibiz.ordersservice.repo;

import java.util.List;

import com.wikibiz.ordersservice.model.Order;

import org.springframework.data.mongodb.repository.MongoRepository;

public interface OrderRepo extends MongoRepository<Order, String> {

    List<Order> findAllByCustomerId(String customerId);

    List<Order> findAllByCleared(boolean cleared);

}
