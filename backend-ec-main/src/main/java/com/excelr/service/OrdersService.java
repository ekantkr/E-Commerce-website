package com.excelr.service;

import java.util.Date;
import java.util.List;

import com.excelr.Exception.OrdersException;
import com.excelr.model.Orders;
import com.excelr.DTO.OrdersDTO;
import com.excelr.model.OrderRequest;

public interface OrdersService {
    
    OrdersDTO placeOrder(OrderRequest orderRequest) throws OrdersException;
    
    Orders updateOrders(Integer orderId, OrdersDTO orderDTO) throws OrdersException;
    
    Orders getOrdersDetails(Integer orderId) throws OrdersException;
    
    List<Orders> getAllUserOrder(Integer userId) throws OrdersException;
    
    List<Orders> viewAllOrders() throws OrdersException;
    
    List<Orders> viewAllOrderByDate(Date date) throws OrdersException;
    
    void deleteOrders(Integer userId, Integer orderId) throws OrdersException;
}
