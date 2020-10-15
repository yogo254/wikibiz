package com.wikibiz.ordersservice.controler;

import java.util.List;

import com.wikibiz.ordersservice.model.Order;
import com.wikibiz.ordersservice.repo.OrderRepo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.v3.oas.annotations.parameters.RequestBody;

@RestController
@RequestMapping("/api/order")
public class OrderControler {

    @Autowired
    private OrderRepo orderRepo;

    @GetMapping("/cleared/{cleared}")

    public List<Order> getCleareadOrders(@PathVariable("cleared") boolean cleared) {

        return orderRepo.findAllByCleared(cleared);

    }

    @GetMapping("/customer/{id}")
    public List<Order> getByCustomer(@PathVariable("id") String id) {
        return orderRepo.findAllByCustomerId(id);
    }

    @PostMapping

    public String addOrder(@RequestBody Order order) {
        orderRepo.save(order);

        return "order added succcessfully";
    }

}
