import React from 'react';
import { GiRunningShoe } from "react-icons/gi";
import { FaSearch } from "react-icons/fa";
import { MdOutlineShoppingBag } from "react-icons/md";
import { FaRegUser } from "react-icons/fa";
import { FaRegHeart } from "react-icons/fa6";
import { Link, useNavigate } from 'react-router-dom';
import "./Navbar.css";

const Navbar = () => {
  const navigate = useNavigate();

  const handleLogout = () => {
    // Clear the token from local storage
    localStorage.removeItem('token');
    // Redirect to the login page
    navigate('/login');
  };

  return (
    <>
      <div className="navbar">
        <div className="navbar__logo">
          <h2>SneakerSphere</h2>
        </div>
        <div className="navbar__search">
          <input type="text" placeholder="Search..." />
          <FaSearch />
        </div>
        <div className="navbar__navlist">
          <ul className="navlist__category">
            <Link to="/men"><li>MEN</li></Link>
            <Link to="/women"><li>WOMEN</li></Link>
            <Link to="/kid"><li>KIDS</li></Link>
          </ul>
          <ul className="navlist__options">
            <Link to="/wishlist"><li><FaRegHeart /></li></Link>
            <Link to="/cart"><li><MdOutlineShoppingBag /></li></Link>
            <li className="user-icon">
              <FaRegUser />
              <div className="dropdown-menu">
                <Link to="/my-orders"><div className="dropdown-item">My Orders</div></Link>
                <div className="dropdown-item" onClick={handleLogout}>Logout</div>
              </div>
            </li>
          </ul>
        </div>
      </div>
    </>
  );
};

export default Navbar;
