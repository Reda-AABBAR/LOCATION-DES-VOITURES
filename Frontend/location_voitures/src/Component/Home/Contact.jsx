import React from 'react'
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';
import { faEnvelope } from '@fortawesome/free-solid-svg-icons';
import { faPhone } from '@fortawesome/free-solid-svg-icons';

function Contact() {
  return (
  <div style={{ height: '100vh' }}>
    <div style={{  display: 'flex', flexDirection: 'column' }}>
      <div style={{ height: '50vh', backgroundColor: 'white', display: 'flex', flexDirection: 'column', alignItems: 'center', justifyContent: 'center' }}>
        <FontAwesomeIcon icon={faEnvelope} style={{ fontSize: '2rem', color: '#1a237e' }} />
        <br/>
        <h1 style={{ color: '#1a237e' }}>
          <span style={{ color: 'black' }}>Contact </span>CarRental
        </h1>
      </div>
      <div style={{ height: '50vh', backgroundColor: '#1a237e', justifyContent: 'space-around',display: 'flex', flexDirection: 'row'  }}>
        <div style={{height: '50vh',  width: '50vw', borderRight: '1px solid white', paddingRight: '10px',marginTop:'3%' ,color:'white'}}>
          <div style={{ display: 'flex', flexDirection: 'column', alignItems: 'center', justifyContent: 'center'}} >
            <FontAwesomeIcon icon={faPhone} style={{ fontSize: '2rem', color: 'white' }} />
            <p style={{marginTop:'10px'}}>
              <h2>Contact us by phone</h2>
            </p>
            <p style={{width:'200px'}}>Fast and efficient for any questions, do not hesitate to call us at :</p>
            <p >212 657 67 54 32</p>
          </div>
        </div>
        <div style={{height: '50vh', width: '50vw', paddingLeft: '10px' ,marginTop:'3%'}}>
          <div style={{height: '50vh',  width: '50vw', borderRight: '1px solid white', paddingRight: '10px' ,color:'white'}}>
            <div style={{ display: 'flex', flexDirection: 'column', alignItems: 'center', justifyContent: 'center'}} >
            <FontAwesomeIcon icon={faEnvelope} style={{ fontSize: '2rem', color: 'white' }} />
              <p style={{marginTop:'10px'}}>
                <h2>Contact us by email</h2>
              </p>
              <p style={{width:'200px'}}>If you have any questions, please feel free to send us an email at the address :</p>
              <p >car.rental@gmail.com</p>
            </div>
          </div>
        </div>
      </div>
    </div>
  </div>
  )
}
export default Contact;