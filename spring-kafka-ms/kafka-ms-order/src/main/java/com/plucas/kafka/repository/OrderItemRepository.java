package com.plucas.kafka.repository;

import com.plucas.kafka.entity.Order;
import com.plucas.kafka.entity.OrderItem;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OrderItemRepository extends CrudRepository<OrderItem, Integer> {

    
}
