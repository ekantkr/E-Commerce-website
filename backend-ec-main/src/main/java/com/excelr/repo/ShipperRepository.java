package com.excelr.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.excelr.model.Shipper;
@Repository
public interface ShipperRepository extends JpaRepository<Shipper, Integer> {

}
