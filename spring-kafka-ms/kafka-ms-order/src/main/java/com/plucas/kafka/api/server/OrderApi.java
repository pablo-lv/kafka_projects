package com.plucas.kafka.api.server;

import com.plucas.kafka.api.request.OrderRequest;
import com.plucas.kafka.api.response.OrderResponse;
import com.plucas.kafka.command.service.OrderService;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.print.attribute.standard.Media;

@RestController
@RequestMapping("/api/order")
public class OrderApi {

    private OrderService orderService;

    public OrderApi(OrderService orderService) {
        this.orderService = orderService;
    }

    @PostMapping(value = "", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<OrderResponse> createOrder(@RequestBody OrderRequest request) {
        var orderNumber = orderService.saveOrder(request);

        var orderResponse = new OrderResponse(orderNumber);

        return ResponseEntity.ok().body(orderResponse);
    }
}
