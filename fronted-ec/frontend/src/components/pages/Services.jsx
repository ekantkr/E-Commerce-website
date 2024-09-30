import React from 'react'
import "./Services.css"
import { RiSecurePaymentFill } from "react-icons/ri";
import { BiSupport } from "react-icons/bi";
import { TbTruckDelivery } from "react-icons/tb";


const ServiceIcon = ({ title, description, icon }) => {
    return (
      <div className="service-icon">
        <h2>{icon}</h2> 
        <h3>{title}</h3>
        <p>{description}</p>
      </div>
    );
  };

const Services = () => {
    const serviceData = [
        {
          title: 'Secure Payment',
          description: 'Secure on order',
          icon: <RiSecurePaymentFill />, // Replace with your icon path
        },
        {
          title: '24/7 Support',
          description: 'Contact us 24 hrs a day',
          icon: <BiSupport/>, // Replace with your icon path
        },
        {
          title: 'Fast Delivery',
          description: 'Fast delivery on order',
          icon: <TbTruckDelivery />, // Replace with your icon path
        },
      ];
  return (
    <>
      <div className="services-container">
      {serviceData.map((service) => (
        <ServiceIcon
          key={service.title}
          title={service.title}
          description={service.description}
          icon={service.icon}
        />
      ))}
    </div>
    </>
  )
}

export default Services
