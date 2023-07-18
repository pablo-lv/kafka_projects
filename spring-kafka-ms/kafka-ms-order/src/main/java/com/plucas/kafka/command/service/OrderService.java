package com.plucas.kafka.command.service;

import com.plucas.kafka.api.request.OrderRequest;
import com.plucas.kafka.command.action.OrderAction;
import org.springframework.stereotype.Service;

@Service
public class OrderService {

    private OrderAction orderAction;

    public OrderService(OrderAction orderAction) {
        this.orderAction = orderAction;
    }

    public String saveOrder(OrderRequest request) {
        var order = orderAction.convertToOrder(request);
        orderAction.saveToDatabase(order);

        order.getItems().forEach(orderAction::publishToKafka);

        return order.getOrderNumber();
    }
}
