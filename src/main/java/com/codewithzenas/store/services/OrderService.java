package com.codewithzenas.store.services;

import com.codewithzenas.store.dtos.OrderDto;
import com.codewithzenas.store.exceptions.OrderNotFoundException;
import com.codewithzenas.store.mappers.OrderMapper;
import com.codewithzenas.store.repositories.OrderRepository;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.stereotype.Service;

import java.util.List;

@AllArgsConstructor
@Service
public class OrderService {

    private final AuthService authService;
    private final OrderRepository orderRepository;
    private OrderMapper orderMapper;

    public List<OrderDto> getCurrentUserOrders() {
        var user = authService.getLoggedUser();
        var orders = orderRepository.getAllByCustomer(user);
        return orders.stream().map(orderMapper::toDto).toList();
    }

    public OrderDto getOrder(Long orderId) {
        var order = orderRepository.getOrderWithProduct(orderId)
                .orElseThrow(OrderNotFoundException::new);

        var user = authService.getLoggedUser();

        if (!order.isPlacedBy(user)) {
            throw new AccessDeniedException("Access denied");
        }

        return orderMapper.toDto(order);
    }
}
