package com.excelr.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.excelr.Exception.ShippingException;
import com.excelr.model.Orders;
import com.excelr.model.Shipper;
import com.excelr.model.ShippingDetails;
import com.excelr.DTO.ShippingDTO;
import com.excelr.repo.OrderRepository;
import com.excelr.repo.ShipperRepository;
import com.excelr.repo.ShippingRepository;

@Service
public class ShippingServiceImpl implements ShippingService {

    private final ShippingRepository shippingRepository;
    private final OrderRepository orderRepository;
    private final ShipperRepository shipperRepository;

    @Autowired
    public ShippingServiceImpl(ShippingRepository shippingRepository, OrderRepository orderRepository, ShipperRepository shipperRepository) {
        this.shippingRepository = shippingRepository;
        this.orderRepository = orderRepository;
        this.shipperRepository = shipperRepository;
    }

    @Override
    @Transactional
    public ShippingDetails setShippingDetails(Integer orderId, Integer shipperId, ShippingDetails shippingDetails) throws ShippingException {
        Orders order = orderRepository.findById(orderId)
                .orElseThrow(() -> new ShippingException("Order with ID " + orderId + " not found"));

        Shipper shipper = shipperRepository.findById(shipperId)
                .orElseThrow(() -> new ShippingException("Shipper with ID " + shipperId + " not found"));

        shippingDetails.setOrder(order);
        shippingDetails.setShipper(shipper);

        return shippingRepository.save(shippingDetails);
    }

    @Override
    @Transactional
    public ShippingDetails updateShippingAddress(Integer shippingId, ShippingDTO shippingDTO) throws ShippingException {
        ShippingDetails existingShippingDetails = shippingRepository.findById(shippingId)
                .orElseThrow(() -> new ShippingException("Shipping details with ID " + shippingId + " not found"));

        existingShippingDetails.setAddress(shippingDTO.getAddress());
        existingShippingDetails.setCity(shippingDTO.getCity());
        existingShippingDetails.setState(shippingDTO.getState());
        existingShippingDetails.setPostalCode(shippingDTO.getPostalCode());

        return shippingRepository.save(existingShippingDetails);
    }

    @Override
    @Transactional
    public void deleteShippingDetails(Integer shippingId) throws ShippingException {
        ShippingDetails shippingDetails = shippingRepository.findById(shippingId)
                .orElseThrow(() -> new ShippingException("Shipping details with ID " + shippingId + " not found"));
        shippingRepository.delete(shippingDetails);
    }
}
