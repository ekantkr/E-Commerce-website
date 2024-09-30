import React, { useState } from 'react';
import axios from 'axios';
import Navbar from '../Navbar/Navbar';
import './checkout.css';
import { useLocation, useNavigate } from 'react-router-dom';

const Checkout = () => {
    const [shippingDetails, setShippingDetails] = useState({
        address: '',
        city: '',
        state: '',
        postalCode: '', // Corrected field name
        country: '',
        phoneNumber: '',
    });
    const [orderSummary, setOrderSummary] = useState(null);
    const [paymentSuccess, setPaymentSuccess] = useState(false);
    const location = useLocation();
    const navigate = useNavigate();
    const userId = localStorage.getItem('userId');
    const token = localStorage.getItem('token');
    const { orderId } = location.state || {};

    const handleShippingChange = (e) => {
        setShippingDetails({
            ...shippingDetails,
            [e.target.name]: e.target.value,
        });
    };

    const handleShippingSubmit = async (e) => {
        e.preventDefault();
        try {
            const shipperId = 1; // Assuming shipperId is constant
            const shippingResponse = await axios.post(
                `http://localhost:8080/api/order-shipping/${orderId}/${shipperId}`,
                shippingDetails,
                {
                    headers: {
                        'Authorization': `Bearer ${token}`,
                    },
                }
            );

            // Assuming the backend returns an order summary after shipping details are submitted
            setOrderSummary({
                orderId,
                totalPrice: shippingResponse.data.totalAmount, // Replace with the correct field if different
            });
        } catch (error) {
            console.error('Error during checkout process:', error);

            // Handle 401 Unauthorized errors
            if (error.response && error.response.status === 401) {
                console.error('Unauthorized access. Please login again.');
                navigate('/cart');
            }

            // Handle validation errors related to postalCode
            if (error.response && error.response.data && error.response.data.errors) {
                const validationErrors = error.response.data.errors;
                validationErrors.forEach((err) => {
                    if (err.field === 'postalCode') {
                        console.error('Postal code is mandatory and cannot be null.');
                    }
                });
            }
        }
    };

    const handlePayment = async () => {
        try {
            const paymentResponse = await axios.post(
                `http://localhost:8080/api/order-payments/makePayment/${orderSummary.orderId}/${userId}/CREDIT_CARD`,
                {},
                {
                    headers: {
                        'Authorization': `Bearer ${token}`,
                    },
                }
            );

            setPaymentSuccess(true);
        } catch (error) {
            console.error('Error processing payment:', error);
        }
    };

    return (
        <>
            <Navbar />
            <div className="checkout-container">
                {!orderSummary && !paymentSuccess && (
                    <div className="shipping-details">
                        <h2>Shipping Details</h2>
                        <form onSubmit={handleShippingSubmit}>
                            <input
                                type="text"
                                name="address"
                                placeholder="Address"
                                value={shippingDetails.address}
                                onChange={handleShippingChange}
                                required
                            />
                            <input
                                type="text"
                                name="city"
                                placeholder="City"
                                value={shippingDetails.city}
                                onChange={handleShippingChange}
                                required
                            />
                            <input
                                type="text"
                                name="state"
                                placeholder="State"
                                value={shippingDetails.state}
                                onChange={handleShippingChange}
                                required
                            />
                            <input
                                type="text"
                                name="postalCode" // Corrected field name
                                placeholder="Postal Code"
                                value={shippingDetails.postalCode}
                                onChange={handleShippingChange}
                                required
                            />
                            <input
                                type="text"
                                name="country"
                                placeholder="Country"
                                value={shippingDetails.country}
                                onChange={handleShippingChange}
                                required
                            />
                            <input
                                type="text"
                                name="phoneNumber"
                                placeholder="Phone Number"
                                value={shippingDetails.phoneNumber}
                                onChange={handleShippingChange}
                                required
                            />
                            <button type="submit" className="shipping-button">Proceed to Order Summary</button>
                        </form>
                    </div>
                )}

                {orderSummary && !paymentSuccess && (
                    <div className="order-summary">
                        <h2>Order Summary</h2>
                        <p><strong>Address:</strong> {shippingDetails.address}, {shippingDetails.city}, {shippingDetails.state}, {shippingDetails.postalCode}, {shippingDetails.country}</p>
                        <p><strong>Phone Number:</strong> {shippingDetails.phoneNumber}</p>
                        <p><strong>Total Price:</strong> ₹{orderSummary.totalAmount}</p>
                        <button onClick={handlePayment} className="payment-button">Proceed to Payment</button>
                    </div>
                )}

                {paymentSuccess && (
                    <div className="payment-success">
                        <h2>Payment Successful!</h2>
                        <p>Your order has been placed successfully. Thank you for shopping with us!</p>
                    </div>
                )}
            </div>
        </>
    );
};

export default Checkout;
