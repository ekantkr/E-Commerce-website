import React from 'react'
import "./Footer.css"
import { FaFacebook } from "react-icons/fa";
import { FaSquareXTwitter } from "react-icons/fa6";
import { FaInstagram } from "react-icons/fa";

const Footers = () => {
  return (
    <>
      <footer className="footer">
      <div className="footer-section">
        <h2>SneakerSphere.</h2>
        <p className="contact-email">SneakerSphere@gmail.com</p>
        <p className="contact-number">0852-9241- 0704</p>
      </div>
      <div className="footer-section">
        <h3>Home</h3>
        <ul>
          <li>New & Featured</li>
          <li>Men</li>
          <li>Women</li>
          <li>Sale</li>
        </ul>
      </div>
      <div className="footer-section">
        <h3>Company</h3>
        <ul>
          <li>About Us</li>
          <li>Community</li>
          <li>Reviews</li>
          <li>FAQ's</li>
        </ul>
      </div>
      <div className="footer-section">
        <h3>Social</h3>
        <ul>
          <li>Instagram</li>
          <li>Facebook</li>
          <li>X</li>
          <li>LinkedIn</li>
        </ul>
      </div>
      <div className="footer-section">
        <h3>Support</h3>
        <ul>
          <li>Privacy Policy</li>
          <li>Term & Condition</li>
          <li>Help Center</li>
        </ul>
      </div>
      <div className="footer-copyright">
        <p>©2023 SneakerSphere</p>
      </div>
    </footer>
    </>
  )
}

export default Footers
