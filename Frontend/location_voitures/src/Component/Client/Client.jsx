import React from 'react'
import {Link, useNavigate, useParams} from "react-router-dom";
import { useState, useEffect } from 'react';
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';
import { faCar } from '@fortawesome/free-solid-svg-icons';
import { faQuestionCircle } from '@fortawesome/free-solid-svg-icons';
import { faBuilding } from '@fortawesome/free-regular-svg-icons';
import { faHome } from '@fortawesome/free-solid-svg-icons';
import { faSearch } from '@fortawesome/free-solid-svg-icons';
import { faPen } from '@fortawesome/free-solid-svg-icons';
import { faCarRear } from '@fortawesome/free-solid-svg-icons';
import axios from 'axios';
import img from './images/img.jpg';

export default function Client() {
    const { email } = useParams();
    const [client, setClient] = useState({
      nom: "",
      prenom: "",
      cIN: "",
      dateNaissance: "",
      adresse: "",
      email: "",
      numTele: "",
      password: ""
    })

    async function getClient() {
      let response = await axios.get(`http://localhost:8080/clients/email/${email}`)
     
      const { nom, prenom, cIN, dateNaissance, adresse, numTele, password } = response.data;
      const updatedClient = {
        nom,
        prenom,
        cIN,
        dateNaissance,
        adresse,
        numTele,
        password,
      };
      setClient(updatedClient);

    
    }

    useEffect(() => {
      getClient();
      
    },)


  return (
<div style={{display:'flex'}}>
      <div style={{ backgroundColor: '#1a237e', height: '100vh', width: "430px", textAlign: 'left',padding:'20px' }}>
        
{client &&       <Link to={`/cars/${email}`}  style={{color:'white',textDecoration:'none', margin: '10px',display:'flex'}}>
            <FontAwesomeIcon icon={faCar} style={{color:'white',height:'30px',marginRight:'10px'}}/>
            <h4 >Cars</h4>
        </Link>}

        <Link to={`/my_reservations/${email}`}  style={{color:'white',textDecoration:'none', margin: '10px',display:'flex'}}>
        <FontAwesomeIcon icon={faCarRear}  style={{color:'white',height:'30px',marginRight:'10px'}}/>
          <h4>My Reservations</h4>
        </Link>
        
        

        <Link to={`/question/${email}`}  style={{color:'white',textDecoration:'none', margin: '10px',display:'flex'}}>
          <FontAwesomeIcon icon={faQuestionCircle} style={{color:'white',height:'30px',marginRight:'10px'}}/>
          <h4 >help</h4>
        </Link>
      </div>
      <div style={{ backgroundColor: 'white', height: '100vh', display: 'flex', flexDirection: 'column', justifyContent: 'center', alignItems: 'center', flex: '1' }}>        
        <img src={img} alt="p1 msg" style={{ width: '100%', height: '98%', objectFit: 'cover', marginLeft:'20px' }} />
      </div>
    </div>
  );
}
