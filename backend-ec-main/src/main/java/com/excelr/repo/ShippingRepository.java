package com.excelr.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.excelr.model.ShippingDetails;
@Repository
public interface ShippingRepository extends JpaRepository<ShippingDetails, Integer> {

}
