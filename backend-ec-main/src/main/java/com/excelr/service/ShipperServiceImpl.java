package com.excelr.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.excelr.Exception.ShipperException;
import com.excelr.model.Shipper;
import com.excelr.repo.ShipperRepository;

import java.util.List;

@Service
public class ShipperServiceImpl implements ShipperService {

    private final ShipperRepository shipperRepository;

    @Autowired
    public ShipperServiceImpl(ShipperRepository shipperRepository) {
        this.shipperRepository = shipperRepository;
    }

    @Override
    @Transactional
    public void deleteShipperById(Integer id) throws ShipperException {
        if (!shipperRepository.existsById(id)) {
            throw new ShipperException("Shipper with ID " + id + " not found");
        }
        shipperRepository.deleteById(id);
    }

    @Override
    @Transactional
    public Shipper saveShipper(Shipper shipper) throws ShipperException {
        if (shipper == null) {
            throw new ShipperException("Shipper cannot be null");
        }
        return shipperRepository.save(shipper);
    }

    @Override
    @Transactional(readOnly = true)
    public Shipper getShipperById(Integer id) throws ShipperException {
        return shipperRepository.findById(id)
                .orElseThrow(() -> new ShipperException("Shipper with ID " + id + " not found"));
    }

    @Override
    @Transactional(readOnly = true)
    public List<Shipper> getAllShippers() throws ShipperException {
        List<Shipper> shippers = shipperRepository.findAll();
        if (shippers.isEmpty()) {
            throw new ShipperException("No shippers found");
        }
        return shippers;
    }
}
