import React, { useState } from 'react';
import { Link, useNavigate } from 'react-router-dom';
import axios from 'axios';
import "./login.css";

const Register = () => {
  const navigate = useNavigate();
  const [formData, setFormData] = useState({
    firstName: '',
    lastName: '',
    email: '',
    password: '',
    phoneNumber: ''
  });

  const handleChange = (e) => {
    setFormData({
      ...formData,
      [e.target.name]: e.target.value
    });
  };

  const handleSubmit = async (e) => {
    e.preventDefault();
    try {
      const response = await axios.post('http://localhost:8080/auth/register', formData);
      if (response.status === 201) {
        // Registration successful, redirect to login page
        navigate('/login');
      }
    } catch (error) {
      console.error('Registration failed:', error);
      // Handle registration error (e.g., show an error message)
    }
  };

  return (
    <>
      <nav className="navbar-login">
        <div className="navbar__container-login">
          <h2>SneakerSphere</h2>
        </div>
      </nav>
      <div className="main-login">
        <div className="login">
          <h1 className="s">Register</h1>
          <form onSubmit={handleSubmit}>
            <input
              type="text"
              name="firstName"
              placeholder="First Name"
              value={formData.firstName}
              onChange={handleChange}
              required
            />
            <input
              type="text"
              name="lastName"
              placeholder="Last Name"
              value={formData.lastName}
              onChange={handleChange}
              required
            />
            <input
              type="email"
              name="email"
              placeholder="Email address"
              value={formData.email}
              onChange={handleChange}
              required
            />
            <input
              type="password"
              name="password"
              placeholder="Password"
              value={formData.password}
              onChange={handleChange}
              required
            />
            <input
              type="number"
              name="phoneNumber"
              placeholder="Phone number"
              value={formData.phoneNumber}
              onChange={handleChange}
              required
            />
            <Link to="/login">Already have an account?</Link>
            <input type="submit" value="Register" />
          </form>
        </div>
      </div>
    </>
  );
};

export default Register;
