import React, { useState } from 'react';
import { Link } from 'react-router-dom';
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';
import { faCar } from '@fortawesome/free-solid-svg-icons';


function NavBarHome() {

    const [hoveredHome, setHoveredHome] = useState(false);
    const [hoveredAboutAs, setHoveredAboutAs] = useState(false);
    const [hoveredFeedBack, setHoveredFeedBack] = useState(false);
    const [hoveredContact, setHoveredContact] = useState(false);
    const linkStyleHome = {
        color: hoveredHome ? '#1a237e' : 'black',
        transition: 'color 0.3s',
        fontWeight:'700',
      };
      const linkStyleAboutAs = {
        color: hoveredAboutAs ? '#1a237e' : 'black',
        transition: 'color 0.3s',
        fontWeight:'700',
      };
      const linkStyleFeedBack  = {
        color: hoveredFeedBack ? '#1a237e' : 'black',
        transition: 'color 0.3s',
        fontWeight:'700',
      };
      const linkStyleContact = {
        color: hoveredContact ? '#1a237e' : 'black',
        transition: 'color 0.3s',
        fontWeight:'700',
      };

  return (
    <div>
      <nav className="navbar navbar-expand-lg bg-body-tertiary"  >
        <div className="container-fluid" >
          <Link className="navbar-brand" to="#" > <FontAwesomeIcon icon={faCar} style ={{ width:'70px', height:'30px'}}/><p style={{fontWeight:'850',color:'#1a237e'}}>CarRental</p></Link>
          <button className="navbar-toggler" type="button" data-bs-toggle="collapse" data-bs-target="#navbarNav" aria-controls="navbarNav" aria-expanded="false" aria-label="Toggle navigation">
            <span className="navbar-toggler-icon"></span>
          </button>
          <div className="collapse navbar-collapse" id="navbarNav">
            <ul className="navbar-nav">
              <li className="nav-item">
                <Link className="nav-link active font-family-custom" style={linkStyleHome}
                  onMouseEnter={() => setHoveredHome(true)}
                  onMouseLeave={() => setHoveredHome(false)}  to="/">Home</Link>
              </li>
              <li className="nav-item">
                <Link className="nav-link font-family-custom" style={linkStyleAboutAs}
                  onMouseEnter={() =>  setHoveredAboutAs(true)}
                  onMouseLeave={() =>  setHoveredAboutAs(false)}  to="/aboutus">About Us</Link>
              </li>
              <li className="nav-item">
                <Link className="nav-link font-family-custom"  style={linkStyleFeedBack}
                  onMouseEnter={() => setHoveredFeedBack(true)}
                  onMouseLeave={() => setHoveredFeedBack(false)} to="/feedback">Feedback</Link>
              </li>
              <li className="nav-item">
                <Link className="nav-link font-family-custom" style={linkStyleContact}
                  onMouseEnter={() => setHoveredContact(true)}
                  onMouseLeave={() => setHoveredContact(false)}  to="/contact">Contact</Link>
              </li>
            </ul>
            <div className="ms-auto" role="search">
              <Link className="btn btn-outline-dark"  to="/login" >Login</Link>
            </div>
          </div>
        </div>
      </nav>
    </div>
  );
}

export default NavBarHome;
