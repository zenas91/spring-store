package com.codewithzenas.store.repositories;

import com.codewithzenas.store.entities.Order;
import com.codewithzenas.store.entities.User;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface OrderRepository extends JpaRepository<Order, Long> {

    @EntityGraph(attributePaths = "orderItems.product")
    @Query("select o from Order o where o.customer = :customer")
    List<Order> getAllByCustomer(@Param("customer") User customer);

    @EntityGraph(attributePaths = "orderItems.product")
    @Query("select o from Order o where o.id = :orderId")
    Optional<Order> getOrderWithProduct(@Param("orderId") Long orderId);
}
