import { StrictMode } from 'react'
import { createRoot } from 'react-dom/client'
import App from './App.jsx'
import Navbar from './components/Navbar/Navbar.jsx'
import Landing from './components/pages/Landing.jsx'
import Master from './components/pages/Master.jsx'
import Mens from "../src/components/Men/Mens.jsx"
import Womens from './components/Women/Womens.jsx'
import { BrowserRouter } from 'react-router-dom';
import Login from './components/logreg/login.jsx'
import Register from './components/logreg/Register.jsx'

createRoot(document.getElementById('root')).render(
 
  <BrowserRouter>
  <App />
</BrowserRouter>

)
