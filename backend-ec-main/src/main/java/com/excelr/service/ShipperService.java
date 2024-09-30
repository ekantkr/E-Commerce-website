package com.excelr.service;

import java.util.List;
import com.excelr.Exception.ShipperException;
import com.excelr.model.Shipper;

public interface ShipperService {

    void deleteShipperById(Integer id) throws ShipperException;

    Shipper saveShipper(Shipper shipper) throws ShipperException;

    Shipper getShipperById(Integer id) throws ShipperException;

    List<Shipper> getAllShippers() throws ShipperException;
}
