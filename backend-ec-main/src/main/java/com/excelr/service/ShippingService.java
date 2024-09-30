package com.excelr.service;

import com.excelr.Exception.ShippingException;
import com.excelr.model.ShippingDetails;
import com.excelr.DTO.ShippingDTO;

public interface ShippingService {
    ShippingDetails setShippingDetails(Integer orderId, Integer shipperId, ShippingDetails shippingDetails) throws ShippingException;
    ShippingDetails updateShippingAddress(Integer shippingId, ShippingDTO shippingDTO) throws ShippingException;
    void deleteShippingDetails(Integer shippingId) throws ShippingException;
}
