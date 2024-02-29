import React from 'react';
import { Link } from 'react-router-dom';
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';
import { faBuilding } from '@fortawesome/free-regular-svg-icons';
import { faCar } from '@fortawesome/free-solid-svg-icons';
import { faHome } from '@fortawesome/free-solid-svg-icons';
import { faSearch } from '@fortawesome/free-solid-svg-icons';
import { faPen } from '@fortawesome/free-solid-svg-icons';

export default function Admin() {
  return (
    <div style={{display:'flex',justifyContent:'space-between'}}>
      <div style={{ backgroundColor: '#1a237e', height: '100vh', width: "430px", textAlign: 'left',padding:'20px' }}>
        
        <Link to='/companie'  style={{color:'white',textDecoration:'none', margin: '10px',display:'flex'}}>
          <FontAwesomeIcon icon={faBuilding} style={{color:'white',height:'30px',marginRight:'10px'}}/>
          <h4>management of companies</h4>
        </Link>
        
        
        <Link to='/car'  style={{color:'white',textDecoration:'none', margin: '10px',display:'flex'}}>
          <FontAwesomeIcon icon={faCar} style={{color:'white',height:'30px',marginRight:'10px'}}/>
          <h4 >management of vehicle</h4>
        </Link>
        <Link to='/book'  style={{color:'white',textDecoration:'none', margin: '10px',display:'flex'}}>
          <FontAwesomeIcon icon={faSearch} style={{color:'white',height:'30px',marginRight:'10px'}}/>
          <h4 >management of Book</h4>
        </Link>
        <Link to='/characteristic'  style={{color:'white',textDecoration:'none', margin: '10px',display:'flex'}}>
          <FontAwesomeIcon icon={faPen} style={{color:'white',height:'30px',marginRight:'10px'}}/>
          <h4 >management of Characteristic</h4>
        </Link>
        <Link to='/agency'  style={{color:'white',textDecoration:'none', margin: '10px',display:'flex'}}>
          <FontAwesomeIcon icon={faHome} style={{color:'white',height:'30px',marginRight:'10px'}}/>
          <h4 >management of Agency</h4>
        </Link>
      
      </div>
      <div style={{ backgroundColor: 'white', height: '100vh', display: 'flex', justifyContent: 'center', alignItems: 'center' }}>
        <h1>Admin</h1>
        <img src='../Image/admin.PNG' alt="p1 msg" style={{ width: '100%', height: '100%', objectFit: 'contain',marginLeft:'20px' }} />
      </div>
    </div>
  )
}
