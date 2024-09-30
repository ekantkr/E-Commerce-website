import React, { useState } from 'react';
import { useNavigate } from 'react-router-dom';
import "./login.css";

const Login = () => {
  const navigate = useNavigate();
  const [email, setEmail] = useState('');
  const [password, setPassword] = useState('');
  const [error, setError] = useState('');

  const handleLogin = async (e) => {
    e.preventDefault();

    try {
      const response = await fetch('http://localhost:8080/auth/login', {
        method: 'POST',
        headers: {
          'Content-Type': 'application/json',
          // Ensure token is defined and valid
          'Authorization': `Bearer ${localStorage.getItem('token') || ''}`
        },
        body: JSON.stringify({ username: email, password: password }),
      });

      if (response.ok) {
        const data = await response.json();
        const { jwtToken, userId } = data;

        if (!userId) {
          console.error('User ID is not defined in the response.');
          setError('Login failed: User ID is missing.');
          return;
        }

        localStorage.setItem('token', jwtToken);
        localStorage.setItem('userId', userId);
        navigate('/master');
      } else {
        console.error('Login failed: Invalid credentials');
        setError('Invalid email or password. Please try again.');
      }
    } catch (error) {
      console.error('Login failed:', error);
      setError('An error occurred during login. Please try again later.');
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
          <h1 className="s">Login</h1>
          <form onSubmit={handleLogin}>
            <input
              type="email"
              placeholder="Email address"
              required
              value={email}
              onChange={(e) => setEmail(e.target.value)}
            />
            <input
              type="password"
              placeholder="Password"
              required
              value={password}
              onChange={(e) => setPassword(e.target.value)}
            />
            <a href="/register">Register here</a>
            <input type="submit" value="Login" />
          </form>
          {error && <p className="error">{error}</p>}
        </div>
      </div>
    </>
  );
};

export default Login;
