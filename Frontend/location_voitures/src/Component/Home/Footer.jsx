import React from 'react';
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';
import { faFacebook, faTwitter, faInstagram, faYoutube, faLinkedin } from '@fortawesome/free-brands-svg-icons';
import { faMapMarkerAlt , faClock } from '@fortawesome/free-solid-svg-icons';

const Footer = () => {
    const iconStyle = {
        display: 'flex',
        justifyContent: 'space-around', // Adjusted to 'space-around' for equal spacing
        alignItems: 'center', // Vertically center the content
        height: '100%', // 100% height of the parent container
     
    };
      
  return (<>
    <div  className="container-fluid d-flex align-items-center justify-content-center custom-navbar" style={{  height: '90px' }}>
    <div style={iconStyle} >
        <div style={{margin: '0 10px'}}>
        <FontAwesomeIcon icon={faFacebook} size="2x" />
        <p><a href="https://www.facebook.com" style={{color:'black'}}>facebook</a></p>
        </div>
      <div style={{margin: '0 10px'}}>
      <FontAwesomeIcon icon={faTwitter} size="2x" />
        <p><a href="https://twitter.com/" style={{color:'black'}}>twitter</a></p>
      </div>
    <div style={{margin: '0 10px'}}>
    <FontAwesomeIcon icon={faInstagram} size="2x" />
    <p><a href="https://www.instagram.com/" style={{color:'black'}}>instagram</a></p>
    </div>
      
      <div style={{margin: '0 10px'}}>
        <FontAwesomeIcon icon={faYoutube} size="2x" />
        <p><a href="https://www.youtube.com/" style={{color:'black'}}>youtube</a></p> 
      </div>
      <div style={{margin: '0 10px'}}>
      <FontAwesomeIcon icon={faLinkedin} size="2x" />
      <p><a href="https://www.linkedin.com/" style={{color:'black '}} >linkedin</a></p> 
      </div>
      
    </div>
    </div>
    <div >
    <div className="container-fluid d-flex align-items-center justify-content-center "  >
        <FontAwesomeIcon icon={faMapMarkerAlt} size="2x" style={{margin: '0 10px'}}/>
        <p style={{color:'black'}}>  Automobile Street, Settat</p>
    </div>
    <div className="container-fluid d-flex align-items-center justify-content-center " >
          <FontAwesomeIcon icon={faClock} size="2x" style={{margin: '0 10px'}} />
          <p style={{color:'black'}} >Monday - Friday : 9h00 - 18h00</p>
        
    </div>
    </div>
    <div>
        <p style={{color:'black'}}> all rights reserved for the car rental application </p>
    </div>
    </>
  );
};

export default Footer;

