import { useState } from 'react'
// import './App.css'
import { Route, Routes } from 'react-router-dom'
import Master from './components/pages/Master'
import Mens from './components/Men/Mens'
import Womens from './components/Women/Womens'
import Kids from './components/Kids/Kids'
import Wishlist from './components/Wishlist/Wishlist'
import Cart from './components/Cart/Cart'
import Login from './components/logreg/Login'
import Register from './components/logreg/Register'
import Checkout from './components/checkout/Checkout'

function App() {
 

  return (
    <>
      <Routes>
        <Route path='/' element={<Login/>} />
        <Route path='/login' element={<Login />} />
        <Route path='/register' element={<Register />} />
        <Route path='/master' element={<Master />} /> 
        <Route path='/men' element={<Mens />} />
        <Route path='/women' element={<Womens />} />
        <Route path='/kid' element={<Kids />} />
        <Route path='/wishlist' element={<Wishlist />} />
        <Route path='/cart' element={<Cart />} />
        <Route path='/Checkout' element={<Checkout/>} />
      </Routes>
    </>
  )
}

export default App
