package org.example.evaluations.evaluation.controllers;

import org.example.evaluations.evaluation.dtos.CreateOrderRequestDto;
import org.example.evaluations.evaluation.exceptions.ShortInventoryException;
import org.example.evaluations.evaluation.models.Order;
import org.example.evaluations.evaluation.services.IOrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/order")
public class OrderController {

    @Autowired
    private IOrderService orderService;

    @PostMapping
    public Order createOrder(@RequestBody CreateOrderRequestDto createOrderRequestDto) throws ShortInventoryException {
        try {
            return orderService.createOrder(createOrderRequestDto.getItemQuantityMap(), createOrderRequestDto.getCustomerId());
        }catch (ShortInventoryException exception) {
            throw exception;
        }
    }

    @ExceptionHandler(ShortInventoryException.class)
    public ResponseEntity<String> throwExceptions(Exception e) {
        return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
