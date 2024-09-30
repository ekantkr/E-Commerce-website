package com.excelr.service;

import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.excelr.DTO.OrdersDTO;
import com.excelr.Enum.OrderStatus;
import com.excelr.Exception.OrdersException;
import com.excelr.model.Orders;
import com.excelr.model.User;
import com.excelr.model.OrderRequest;
import com.excelr.repo.OrderRepository;
import com.excelr.repo.UserRepo;

@Service
public class OrdersServiceImpl implements OrdersService {

    @Autowired
    private OrderRepository orderRepository;

    @Autowired
    private UserRepo userRepo;

    @Override
    public OrdersDTO placeOrder(OrderRequest orderRequest) throws OrdersException {
        User user = userRepo.findById(orderRequest.getUserId())
                .orElseThrow(() -> new OrdersException("User with ID " + orderRequest.getUserId() + " not found"));

        Orders newOrder = new Orders();
        newOrder.setUser(user);
        newOrder.setOrderDate(orderRequest.getOrderDate() != null ? orderRequest.getOrderDate() : LocalDateTime.now());
        newOrder.setStatus(orderRequest.getStatus() != null ? orderRequest.getStatus() : OrderStatus.PENDING);
        newOrder.setTotalAmount(orderRequest.getTotalAmount() != null ? orderRequest.getTotalAmount() : 0.0);

        try {
            Orders savedOrder = orderRepository.save(newOrder);
            return convertToDTO(savedOrder);
        } catch (Exception e) {
            throw new OrdersException("Error placing order", e);
        }
    }

    @Override
    public Orders updateOrders(Integer orderId, OrdersDTO orderDTO) throws OrdersException {
        Orders existingOrder = orderRepository.findById(orderId)
                .orElseThrow(() -> new OrdersException("Order with ID " + orderId + " not found"));

        existingOrder.setStatus(orderDTO.getStatus());
        existingOrder.setOrderDate(orderDTO.getOrderDate());
        existingOrder.setTotalAmount(orderDTO.getTotalAmount());

        try {
            Orders updatedOrder = orderRepository.save(existingOrder);
            return updatedOrder;
        } catch (Exception e) {
            throw new OrdersException("Error updating order", e);
        }
    }

    @Override
    public Orders getOrdersDetails(Integer orderId) throws OrdersException {
        return orderRepository.findById(orderId)
                .orElseThrow(() -> new OrdersException("Order with ID " + orderId + " not found"));
    }

    @Override
    public List<Orders> getAllUserOrder(Integer userId) throws OrdersException {
        List<Orders> orders = orderRepository.findAllByUserId(userId);
        if (orders.isEmpty()) {
            throw new OrdersException("No orders found for user with ID " + userId);
        }
        return orders;
    }

    @Override
    public List<Orders> viewAllOrders() throws OrdersException {
        return orderRepository.findAll();
    }

    @Override
    public List<Orders> viewAllOrderByDate(Date date) throws OrdersException {
        LocalDateTime localDateTime = new java.sql.Timestamp(date.getTime()).toLocalDateTime();
        return orderRepository.findByOrderDateGreaterThanEqual(localDateTime);
    }

    @Override
    public void deleteOrders(Integer userId, Integer orderId) throws OrdersException {
        Orders order = orderRepository.findByOrderIdAndUserId(orderId, userId);
        if (order == null) {
            throw new OrdersException("Order with ID " + orderId + " not found for user with ID " + userId);
        }
        orderRepository.delete(order);
    }

    private OrdersDTO convertToDTO(Orders order) {
        OrdersDTO dto = new OrdersDTO();
        dto.setOrderId(order.getOrderId());
        dto.setStatus(order.getStatus());
        dto.setOrderDate(order.getOrderDate());
        dto.setTotalAmount(order.getTotalAmount());
        return dto;
    }
}
