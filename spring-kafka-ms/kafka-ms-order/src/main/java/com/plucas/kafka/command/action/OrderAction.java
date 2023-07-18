package com.plucas.kafka.command.action;

import com.plucas.kafka.api.request.OrderItemRequest;
import com.plucas.kafka.api.request.OrderRequest;
import com.plucas.kafka.broker.message.OrderMessage;
import com.plucas.kafka.broker.producer.OrderProducer;
import com.plucas.kafka.entity.Order;
import com.plucas.kafka.entity.OrderItem;
import com.plucas.kafka.repository.OrderItemRepository;
import com.plucas.kafka.repository.OrderRepository;
import org.apache.commons.lang3.RandomStringUtils;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.stream.Collectors;

@Component
public class OrderAction {

    private OrderProducer orderProducer;
    private OrderRepository orderRepository;
    private OrderItemRepository orderItemRepository;

    public OrderAction(OrderProducer orderProducer, OrderRepository orderRepository, OrderItemRepository orderItemRepository) {
        this.orderProducer = orderProducer;
        this.orderRepository = orderRepository;
        this.orderItemRepository = orderItemRepository;
    }

    public Order convertToOrder(OrderRequest request) {
        var result = new Order();
        result.setCreditCardNumber(request.getCreditCardNumber());
        result.setOrderLocation(request.getOrderLocation());
        result.setOrderDateTime(LocalDateTime.now());
        result.setOrderNumber(RandomStringUtils.randomAlphanumeric(8).toUpperCase());

        var items = request.getItems().stream().map(this::convertToOrderItem).collect(Collectors.toList());
        items.forEach(item -> item.setOrder(result));

        result.setItems(items);
        return result;
    }

    private OrderItem convertToOrderItem(OrderItemRequest itemRequest) {
        var result = new OrderItem();

        result.setItemName(itemRequest.getItemName());
        result.setPrice(itemRequest.getPrice());
        result.setQuantity(itemRequest.getQuantity());
        return result;
    }

    public void saveToDatabase(Order order) {
        orderRepository.save(order);
        order.getItems().forEach(orderItemRepository::save);
    }


    public void publishToKafka(OrderItem orderItem) {
        var orderMessage = new OrderMessage();
        orderMessage.setItemName(orderItem.getItemName());
        orderMessage.setPrice(orderItem.getPrice());
        orderMessage.setQuantity(orderItem.getQuantity());

        var order = orderItem.getOrder();
        orderMessage.setOrderNumber(order.getOrderNumber());
        orderMessage.setOrderDateTime(order.getOrderDateTime());
        orderMessage.setOrderLocation(order.getOrderLocation());
        orderMessage.setCreditCardNumber(order.getCreditCardNumber());

        orderProducer.publish(orderMessage);
    }
}
