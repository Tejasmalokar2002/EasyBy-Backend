package com.Shop.EasyBy.repositories;

import com.Shop.EasyBy.auth.entities.User;
import com.Shop.EasyBy.entities.Order;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface OrderRepository extends JpaRepository<Order, UUID> {

    List<Order> findByUser(User user);
}
