import React from 'react';
import './NewArrivals.css';
import naa1 from "./images/naa1.png";
import naa2 from "./images/naa2.png";
import naa3 from "./images/naa3.png";
import naa4 from "./images/naa4.png";
import naa5 from "./images/naa5.png";
import naa6 from "./images/naa6.png";
import naa7 from "./images/naa7.png";
import naa8 from "./images/naa8.png";

const NewArrivals = () => {
  const products = [
    {
      name: 'Jordan 1 Mid Kids',
      price: '$64.95',
      image: naa1,
      color: '1Colour',
    },
    {
      name: 'Nike Dunk Low Retro',
      price: '$109.95',
      image: naa2,
      color: '1Colour',
    },
    {
      name: 'Adidas Unisex Samba',
      price: '$64.95',
      image: naa3,
      color: '1Colour',
    },
    {
        name: 'NB Numeric Jamie Foy',
        price: '$72.95',
        image: naa4,
        color: '1Colour',
      },
      {
        name: 'YEEZY BOOST 350 V2',
        price: '$66.95',
        image: naa5,
        color: '1Colour',
      },
      {
        name: 'Adidas Forum Low Shoes',
        price: '$97.95',
        image: naa6,
        color: '1Colour',
      },
      {
        name: 'PUMA BMW pro Sneaker',
        price: '$25.95',
        image: naa7,
        color: '1Colour',
      },
      {
        name: 'Converse Uni Sneaker',
        price: '$88.95',
        image: naa8,
        color: '1Colour',
      },
  ];

  return (
        <>
     <div className="styles-container">
      <h2 className="styles-heading">Step into Style with<br></br> New Arrivals!</h2>
      <div className="styles-products">
        {products.map((product, index) => (
          <div key={index} className="styles-product">
            <div className="styles-product-circle">
              <img src={product.image} alt={product.name} className="styles-product-image" />
            </div>
            <div className="styles-product-details">
              <h3 className="styles-product-title">{product.name}</h3>
              <p className="styles-product-color">{product.color}</p>
              <p className="styles-product-price">{product.price}</p>
            </div>
          </div>
        ))}
      </div>
    </div>
        </>
  );
};

export default NewArrivals;
