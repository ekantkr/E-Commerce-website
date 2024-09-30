import React, { useEffect, useState } from 'react';
import axios from 'axios';
import Navbar from '../Navbar/Navbar';
import './cart.css';
import { useNavigate } from 'react-router-dom';

const Cart = () => {
    const [cartItems, setCartItems] = useState([]);
    const [loading, setLoading] = useState(true);
    const userId = localStorage.getItem('userId');
    const navigate = useNavigate();

    useEffect(() => {
        fetchCart();
    }, []);

    const fetchCart = async () => {
        try {
            const token = localStorage.getItem('token');
            const response = await axios.get(`http://localhost:8080/api/cart/${userId}`, {
                headers: {
                    'Authorization': `Bearer ${token}`,
                },
            });
            setCartItems(response.data);
        } catch (error) {
            console.error('Error fetching cart:', error);
        } finally {
            setLoading(false);
        }
    };

    const handleRemoveItem = async (cartItemId) => {
        try {
            const token = localStorage.getItem('token');
            await axios.delete(`http://localhost:8080/api/cart/${userId}/remove/${cartItemId}`, {
                headers: {
                    'Authorization': `Bearer ${token}`,
                },
            });
            fetchCart();
        } catch (error) {
            console.error('Error removing item from cart:', error);
        }
    };

    const handleUpdateQuantity = async (cartItemId, newQuantity) => {
        try {
            const token = localStorage.getItem('token');
            await axios.put(`http://localhost:8080/api/cart/${userId}/update/${cartItemId}`, {
                shoesId: cartItemId,
                quantity: newQuantity,
            }, {
                headers: {
                    'Authorization': `Bearer ${token}`,
                },
            });
            fetchCart();
        } catch (error) {
            console.error('Error updating item quantity:', error);
        }
    };

    const handleCheckout = async () => {
        try {
            const token = localStorage.getItem('token');
            const orderRequest = {
                userId: userId,
                orderDate: new Date(),
                totalAmount: cartItems.reduce((total, item) => total + (item.cost - (item.cost * (item.discount / 100))) * item.quantity, 0),
                status: 'PENDING',
            };

            const response = await axios.post('http://localhost:8080/api/orders/place', orderRequest, {
                headers: {
                    'Authorization': `Bearer ${token}`,
                },
            });

            navigate('/checkout', { state: { orderId: response.data.orderId } });

        } catch (error) {
            console.error('Error during checkout process:', error);
        }
    };

    return (
        <>
            <Navbar />
            <div className="cart-container">
                <h2>Your Cart</h2>
                {loading ? (
                    <p>Loading...</p>
                ) : cartItems.length === 0 ? (
                    <p>Your cart is empty.</p>
                ) : (
                    <div className="cart-items">
                        {cartItems.map(item => (
                            <div key={item.id} className="cart-item">
                                <img src={item.image} alt={item.name} className="cart-item-image" />
                                <div className="cart-item-details">
                                    <h3 className="cart-item-name">{item.name}</h3>
                                    <p className="cart-item-cost">Price: ₹{(item.cost - (item.cost * (item.discount / 100))).toFixed(2)}</p>
                                    <div className="cart-item-quantity">
                                        <button onClick={() => handleUpdateQuantity(item.id, item.quantity - 1)} disabled={item.quantity <= 1}>-</button>
                                        <span>{item.quantity}</span>
                                        <button onClick={() => handleUpdateQuantity(item.id, item.quantity + 1)}>+</button>
                                    </div>
                                    <button className="remove-button" onClick={() => handleRemoveItem(item.id)}>Remove</button>
                                </div>
                            </div>
                        ))}
                    </div>
                )}
                <button className="checkout-button" onClick={handleCheckout}>Checkout</button>
            </div>
        </>
    );
};

export default Cart;
