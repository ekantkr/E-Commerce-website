import React, { useEffect, useState } from 'react';
import axios from 'axios';
import { FaRegHeart, FaHeart } from "react-icons/fa";
import { FaShoppingCart } from "react-icons/fa";
import Navbar from '../Navbar/Navbar';
import "./kids.css";
import { useNavigate } from 'react-router-dom'; // Import useNavigate

const Kids = () => {
  const [shoes, setShoes] = useState([]);
  const [error, setError] = useState(null);
  const navigate = useNavigate(); // Initialize useNavigate

  useEffect(() => {
    const fetchShoes = async () => {
      const token = localStorage.getItem('token');
      console.log("Token from localStorage:", token); // Debugging line

      if (!token) {
        console.error("No token found in localStorage");
        setError("Authorization failed. Please log in again.");
        return;
      }

      try {
        const response = await axios.get('http://localhost:8080/api/shoes/kids', {
          headers: {
            'Authorization': `Bearer ${token}`
          }
        });
        setShoes(response.data);
      } catch (error) {
        console.error('There was an error fetching the shoes!', error);
        setError('Failed to fetch shoes. Please try again later.');
      }
    };

    fetchShoes();
  }, []);

  const handleAddToWishlist = async (shoeId) => {
    const userId = localStorage.getItem('userId'); // Get user ID from local storage
    const token = localStorage.getItem('token'); // Get token for authorization
  
    if (!userId || !token) {
      alert('Please log in to add items to your wishlist.');
      return;
    }
  
    try {
      const response = await axios.post(`http://localhost:8080/api/wishlists/${userId}/add`, {
        shoeId,
      }, {
        headers: {
          'Authorization': `Bearer ${token}`,
          'Content-Type': 'application/json',
        }
      });
  
      if (response.status === 201) {
        alert('Shoe added to wishlist!');
      } else {
        alert('Failed to add shoe to wishlist.');
      }
    } catch (error) {
      console.error('Error adding to wishlist:', error);
      alert('Failed to add shoe to wishlist.');
    }
  };

  const handleAddToCart = async (shoeId) => {
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
          shoesId: shoeId,
          quantity: 1,
        },
        {
          headers: {
            'Authorization': `Bearer ${token}`,
            'Content-Type': 'application/json',
          },
        }
      );

      if (response.status === 200) {
        alert('Shoe added to cart successfully!');
        navigate('/cart'); // Navigate to Cart page
      } else {
        alert('Failed to add shoe to cart. Please try again.');
      }
    } catch (error) {
      console.error('Error adding shoe to cart:', error);
      alert('An error occurred while adding the shoe to the cart. Please try again.');
    }
  };

  return (
    <>
      <Navbar />
      <div className="App-Kids">
        <h1 className="heading-Kids">Kids' Sneakers</h1>
        {error ? (
          <p className="error-message">{error}</p>
        ) : (
          <div className="shoes-grid-Kids">
            {shoes.map(({ id, image, name, cost, discount }) => (
              <div key={id} className="shoe-card-Kids">
                <div className="shoe-image-container-Kids">
                  <img src={image} alt={name} className="shoe-image-Kids" />
                  <span className="favorite-icon-Kids" onClick={() => handleAddToWishlist(id)}>
                    <FaRegHeart className="icon-border-Kids" />
                    <FaHeart className="icon-filled-Kids" />
                  </span>
                </div>
                <div className="shoe-info-Kids">
                  <h3 className="shoe-name-Kids">{name}</h3>
                  <div className="price-section-Kids">
                    <span className="discount-price-Kids">
                      ₹{(cost - (cost * (discount / 100))).toFixed(2)}
                    </span>
                    <span className="original-price-Kids">₹{cost}</span>
                  </div>
                  <div className="rating-Kids">
                    <span className="stars-Kids">⭐ 4.9</span>
                    <span className="reviews-Kids">(123 reviews)</span>
                  </div>
                </div>
                <button className="add-to-cart-Kids" onClick={() => handleAddToCart(id)}>
                  <FaShoppingCart />
                </button>
              </div>
            ))}
          </div>
        )}
      </div>
    </>
  );
};

export default Kids;
