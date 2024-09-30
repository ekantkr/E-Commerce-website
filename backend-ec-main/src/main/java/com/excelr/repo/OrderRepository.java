package com.excelr.repo;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.excelr.model.Orders;

@Repository
public interface OrderRepository extends JpaRepository<Orders, Integer> {

    // Ensure Orders entity has a User field with the correct name
    @Query("SELECT o FROM Orders o WHERE o.user.userId = :userId")
    List<Orders> findAllByUserId(@Param("userId") Integer userId);

    @Query("SELECT o FROM Orders o WHERE o.orderDate >= :date")
    List<Orders> findByOrderDateGreaterThanEqual(@Param("date") LocalDateTime date);

    @Query("SELECT o FROM Orders o WHERE o.orderId = :orderId AND o.user.userId = :userId")
    Orders findByOrderIdAndUserId(@Param("orderId") Integer orderId, @Param("userId") Integer userId);
}
