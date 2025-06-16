package com.ucc.orders.service;

import com.ucc.orders.model.entities.Order;
import com.ucc.orders.model.mappers.OrdersMapper;
import com.ucc.orders.repository.OrderRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class OrderService {

    private final OrderRepository orderRepository;
    private final OrdersMapper ordersMapper;

    public OrderService(OrderRepository orderRepository, OrdersMapper  ordersMapper) {
        this.orderRepository = orderRepository;
        this.ordersMapper = ordersMapper;
    }

    public List<Order> getOrders(){
        return orderRepository.findAll();
    }

    public ResponseEntity<Order> newOrder(Order order){
        orderRepository.save(order);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

}
