import React from 'react'
import {Link , useNavigate, useParams } from 'react-router-dom'
import axios from 'axios';
import  {useEffect, useState } from 'react'
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';
import { faBuilding } from '@fortawesome/free-regular-svg-icons';

export default function Edit() {
    const { id } = useParams();
    let navigate = useNavigate();

    const [companie,setCompanie]=useState({
      nom:""
    });
    
    useEffect (()=>{
      loadCompanie()
    },[])

    const onInputChange=(e)=>{
      setCompanie({...companie, [e.target.name]: e.target.value});
    };
    const onSubmit = async (e) => {
      e.preventDefault();
      try {
          await axios.put(`http://localhost:8080/companies/${id}`, companie);
          navigate("/companie")
      } catch (error) {
          console.error("Error:", error.response); // Affichez la réponse d'erreur dans la console
      }
    };
    const loadCompanie =async ()=>{
      const result = await axios.get(`http://localhost:8080/companies/${id}`);
    }

  return (
    <div style={{ display: 'flex', justifyContent: 'space-between' }}>
    <div className='listcompanies' style={{ backgroundColor: 'white', height: '100vh', flex: 1 }}>
      <div style={{margin:'50px'}}>
      <img src='../Image/setting.PNG' alt="p1 msg" style={{width:'410px'}} />
      </div>  
    </div>
    <div className='ajoutercompanie' style={{ backgroundColor: '#1a237e', height: '100vh', width: "450px" }}>
    <div  style={{ color: '#1a237e' ,backgroundColor:'#d3d3d3',height:'55px'}}><h2>Edit a company</h2></div>
    <div style={{width:'400px',height:'300px',marginTop:'7%',padding:'40px' }}>
      
    <form onSubmit={(e) => onSubmit(e)}>
      <div className="mb-3">
      <label htmlFor="userName" style={{ color: 'white' }}>Name </label>
          <input 
            value={companie.nom}
            onChange={(e) => onInputChange(e)}
          className="form-control my-input my-2" 
          type="text" 
          id="nom"
          name="nom"
          placeholder="name"/>
      </div>
      <div className="d-flex justify-content-between align-items-center">
        <button type="submit"  className="btn btn-secondary py-2 m-0">
        Edit
        </button>
        <Link type="submit"  className="btn btn-danger py-2 m-0" to="/companie">
        Cancel
        </Link>
      </div>
    </form>
    <div style={{marginTop:'15%'}}>
    <FontAwesomeIcon icon={faBuilding} style={{color:'white',height:'100px'}}/>
    </div>
    </div>
    </div>
</div>
  )
}
