import React from 'react';
import "./Brands.css";
import adidas from "../pages/images/adidaslogo.png"
import nike from "../pages/images/nikelogo.png"
import newbalance from "../pages/images/newbalancelogo.png"
import puma from "../pages/images/pumalogo.png"
import converse from "../pages/images/pngegg.png"

const Brands = () => {
  return (
    <div className="brands-container">
      <h2 className="brands-heading">Top Brands</h2>
      <p className="brands-subheading">Find your dream shoe pair from 5000+ collection</p>
      <div className="brands-logos">
        <div className="brand-logo">
          <img src={nike} alt="Nike" />
        </div>
        <div className="brand-logo">
          <img src={adidas} alt="Adidas" />
        </div>
        <div className="brand-logo">
          <img src={newbalance} alt="New Balance" />
        </div>
        <div className="brand-logo">
          <img src={puma} alt="Puma" />
        </div>
        <div className="brand-logo">
          <img src={converse} alt="Reebok" />
        </div>
      </div>
    </div>
  );
}

export default Brands;
