import React from 'react';
import './ProductShowcase.css';
import ps from "./images/ps3.png"

const ProductShowcase = () => {
  return (
    <div className="product-showcase-wrapper">
      <div className="product-showcase">
        <div className="image-container">
        <img src={ps} alt="Shoe" />
        </div>
        <div className="text-container">
          <h2>Dedicated to quality and result</h2>
          <p>
            Providing the best shoe of various types and manufacturing them for
            true travel lovers. These items are lightweight and comfortable.
          </p>
          <button className="read-more-btn">Read more</button>
        </div>
      </div>
    </div>
  );
};

export default ProductShowcase;
