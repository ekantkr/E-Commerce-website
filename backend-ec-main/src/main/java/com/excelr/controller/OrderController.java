package com.excelr.controller;

import java.util.Date;
import java.util.List;

import com.excelr.DTO.OrdersDTO;
import com.excelr.model.OrderRequest;
import com.excelr.model.Orders;
import com.excelr.service.OrdersService;

import lombok.RequiredArgsConstructor;

import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin("*")
@RequiredArgsConstructor
@RequestMapping("/api/orders")
public class OrderController {

    private final OrdersService ordersService;

    @PostMapping("/place")
    public ResponseEntity<OrdersDTO> placeOrder(@RequestBody OrderRequest orderRequest) {
        OrdersDTO placeOrder = ordersService.placeOrder(orderRequest);
        return ResponseEntity.ok(placeOrder);
    }

    @GetMapping("/{orderId}")
    public ResponseEntity<Orders> getOrdersDetails(@PathVariable("orderId") Integer orderId) {
        Orders order = ordersService.getOrdersDetails(orderId);
        return new ResponseEntity<>(order, HttpStatus.OK);
    }

    @GetMapping("/user/{userId}")
    public ResponseEntity<List<Orders>> getAllUserOrder(@PathVariable("userId") Integer userId) {
        List<Orders> orders = ordersService.getAllUserOrder(userId);
        return new ResponseEntity<>(orders, HttpStatus.OK);
    }

    @GetMapping("/all")
    public ResponseEntity<List<Orders>> viewAllOrders() {
        List<Orders> orders = ordersService.viewAllOrders();
        return new ResponseEntity<>(orders, HttpStatus.OK);
    }

    @GetMapping("/date/{date}")
    public ResponseEntity<List<Orders>> viewAllOrderByDate(
            @PathVariable @DateTimeFormat(pattern = "yyyy-MM-dd") Date date) {
        List<Orders> orders = ordersService.viewAllOrderByDate(date);
        return new ResponseEntity<>(orders, HttpStatus.OK);
    }

    @DeleteMapping("/user/{userId}/{orderId}")
    public ResponseEntity<String> deleteOrders(@PathVariable("userId") Integer userId, @PathVariable("orderId") Integer orderId) {
        ordersService.deleteOrders(userId, orderId);
        return new ResponseEntity<>("Order successfully deleted.", HttpStatus.OK);
    }
}
