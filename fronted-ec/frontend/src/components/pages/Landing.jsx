import React from 'react';
import Navbar from "../Navbar/Navbar.jsx";
import "./Landing.css";
import landpage from "../pages/landpage.png"

const Landing = () => {
  return (
    <>
      <Navbar />
      <div className="landing">
        <div className="landing__heading">
          <h1>
            <span className="highlight">DISCOVER</span> LIMITED SNEAKERS <br />
            WITHOUT <span className="highlight-orange">LIMITATION</span>
          </h1>
        </div>

        <div className="landing__content">
          <p className="hero-text">
          Celebrate MJ's legacy with this shout-out to Chicago's 312 area code. With elements from three iconic Jordans (the AJ3, AJ1 and Air Alpha Force), it's a modern mash-up that reps the best.
          </p>

          <div className="sneaker-wrapper">
            <div className="nike-bg-text">NIKE AIR JORDAN</div>
            <img src={landpage} alt="Air Jordan Max" className="sneaker-image" />
          </div>

          <div className="price-tag">
            <span className="product-name">Nike</span>
            <span className="product-price">Air Jordan Legacy 312 men</span>
            <span className="product-rating">⭐⭐⭐⭐⭐</span>
          </div>
        </div>
      </div>
    </>
  );
};

export default Landing;
