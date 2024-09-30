import React, { useEffect, useState } from 'react';
import { MdOutlineDelete } from 'react-icons/md';
import Navbar from '../Navbar/Navbar';
import Footers from '../Footer/Footers';
import { Link, useNavigate } from 'react-router-dom';
import './wishlist.css';
import axios from 'axios';

const Wishlist = () => {
  const [wishlistItems, setWishlistItems] = useState([]);
  const [error, setError] = useState(null);
  const navigate = useNavigate();

  useEffect(() => {
    const userId = localStorage.getItem('userId');
    const token = localStorage.getItem('token');

    if (userId && token) {
      fetchWishlists(userId, token);
    } else {
      setError('Please log in to view your wishlist.');
    }
  }, []);

  const fetchWishlists = async (userId, token) => {
    try {
      const response = await axios.get(`http://localhost:8080/api/wishlists/user/${userId}`, {
        headers: { Authorization: `Bearer ${token}` },
      });

      setWishlistItems(response.data);
    } catch (error) {
      setError('Failed to fetch wishlist items. Please try again later.');
    }
  };

  const handleRemoveItem = async (wishlistId, shoeId) => {
    const token = localStorage.getItem('token');

    try {
      await axios.delete(`http://localhost:8080/api/wishlists/${wishlistId}/remove/${shoeId}`, {
        headers: { Authorization: `Bearer ${token}` },
      });
      setWishlistItems(wishlistItems.filter(item => item.shoes.every(shoe => shoe.id !== shoeId)));
    } catch (error) {
      setError('Failed to remove item from wishlist. Please try again.');
    }
  };

  const addToCart = async (shoe) => {
    const userId = localStorage.getItem('userId');
    const token = localStorage.getItem('token');

    if (!userId || !token) {
      alert('Please log in to add items to your cart.');
      return;
    }

    try {
      const response = await axios.post(
        `http://localhost:8080/api/cart/${userId}/add`,
        {
          shoesId: shoe.id,
          quantity: shoe.quantity,
        },
        {
          headers: { Authorization: `Bearer ${token}` },
        }
      );

      if (response.status === 200) {
        alert('Shoe added to cart successfully!');
        navigate('/cart'); // Navigate to Cart page
      } else {
        alert('Failed to add shoe to cart. Please try again.');
      }
    } catch (error) {
      setError('Failed to add item to cart. Please try again.');
    }
  };

  return (
    <>
      <Navbar />
      <div className="wishlist-container">
        {error && <p className="error-message">{error}</p>}
        {wishlistItems.length === 0 ? (
          <div className="wishlist-empty">
            <h2 className="wishlist-empty-title">Your Wishlist is Empty</h2>
            <p className="wishlist-empty-message">It looks like you haven't added anything to your wishlist yet.</p>
            <p className="wishlist-empty-message">Explore our collections and start adding your favorite items!</p>
            <Link to="/" className="wishlist-explore-button">Start Shopping</Link>
          </div>
        ) : (
          wishlistItems.map((item) => (
            <div className="wishlist-item" key={item.id}>
              {item.shoes && item.shoes.length > 0 ? (
                item.shoes.map((shoe) => (
                  <div key={shoe.id} className="wishlist-item-details">
                    <img src={shoe.image} alt={shoe.name} className="wishlist-item-image" />
                    <h3 className="wishlist-item-name">{shoe.name}</h3>
                    <div className="wishlist-price-section">
                      <span className="wishlist-original-price">₹{shoe.cost}</span>
                      <span className="wishlist-discount-price">
                        ₹{(shoe.cost - (shoe.cost * (shoe.discount / 100))).toFixed(2)}
                      </span>
                    </div>
                    <button className="wishlist-add-to-cart" onClick={() => addToCart(shoe)}>Add to Cart</button>
                    <button className="wishlist-delete-btn" onClick={() => handleRemoveItem(item.id, shoe.id)}>
                      <MdOutlineDelete />
                    </button>
                  </div>
                ))
              ) : (
                <p></p>
              )}
            </div>
          ))
        )}
      </div>
      <Footers />
    </>
  );
};

export default Wishlist;
