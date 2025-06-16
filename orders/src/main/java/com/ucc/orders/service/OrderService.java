package com.ucc.orders.service;

import com.ucc.orders.model.entities.Order;
import com.ucc.orders.model.mappers.OrdersMapper;
import com.ucc.orders.repository.OrderRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.beans.factory.annotation.Value;

import java.util.List;

@Service
public class OrderService {

    private final OrderRepository orderRepository;
    private final OrdersMapper ordersMapper;
    private final RestTemplate restTemplate;

    @Value("${product.service.url:http://localhost:8080/api}")
    private String productServiceUrl;

    public OrderService(OrderRepository orderRepository, OrdersMapper  ordersMapper, RestTemplate restTemplate) {
        this.orderRepository = orderRepository;
        this.ordersMapper = ordersMapper;
        this.restTemplate = restTemplate;
    }

    public List<Order> getOrders(){
        return orderRepository.findAll();
    }

    public ResponseEntity<Order> newOrder(Order order){
        // Consultar el stock del producto
        String url = productServiceUrl + "/products/" + order.getProductId();
        ResponseEntity<ProductResponse> response = restTemplate.getForEntity(url, ProductResponse.class);
        if (!response.getStatusCode().is2xxSuccessful() || response.getBody() == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        ProductResponse product = response.getBody();
        if (product.getStock() == null || product.getStock() <= 0) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST); // Sin stock
        }
        orderRepository.save(order);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    // Clase interna para mapear la respuesta del producto
    private static class ProductResponse {
        private Integer stock;
        public Integer getStock() { return stock; }
        public void setStock(Integer stock) { this.stock = stock; }
    }
}
