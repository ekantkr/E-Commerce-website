import React from 'react'
import Navbar from "../Navbar/Navbar.jsx"
import Landing from "../pages/Landing.jsx"
import Brands from './Brands.jsx'
import NewArrivals from './NewArrivals.jsx'
import Services from './Services.jsx'
import Footers from '../Footer/Footers.jsx'
import ProductShowcase from './ProductShowcase.jsx'
const Master = () => {
  return (
    <>
      <Navbar></Navbar>
      <Landing></Landing>
      <Brands></Brands>
      <NewArrivals></NewArrivals>
      <Services></Services>
      <ProductShowcase></ProductShowcase>
      <Footers></Footers>
     
    </>
  )
}

export default Master
