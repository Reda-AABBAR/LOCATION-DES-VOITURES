import React, { useState } from 'react'
import { Link } from 'react-router-dom';
import axios from 'axios';

function Signup() {

  const [client,setClient]=useState(
    {
      nom: "",
      prenom: "",
      cIN: "",
      dateNaissance: "",
      adresse: "",
      email: "",
      numTele: "",
      password: ""
    }
  )
  const onInputChange=(e)=>{
    setClient({...client, [e.target.name]: e.target.value});
};
  const onSubmit = async (e) => {
    e.preventDefault();
    try {
        await axios.post("http://localhost:8080/auth/register/client", client);
       //"  http://localhost:8080/auth/register/admin" pour enregistre l'admin
       // "http://localhost:8080/livreurs" utiliser ce lien pour enregistre le livreur 
      //"http://localhost:8080/auth/register/client" pour enregistre le client 
      //http://localhost:8080/service-clients
    } catch (error) {
        console.error("Error:", error.response); // Affichez la réponse d'erreur dans la console
    }
  };

  return(
    <div style={{
        height: '100vh',
        backgroundImage: 'url("./Image/bg.PNG")',
        backgroundPosition: 'center',
        backgroundSize: 'cover',
        display:'flex',
        alignContent:'center',
        justifyContent:'center',
        //overflowY: 'auto',
        padding:'40px'
      }}>
        <div style={{width:'400px',height:'500px',marginTop:'2%',padding:'40px',backgroundColor :'#1a237e',overflowY: 'auto' }}>
        <form onSubmit={(e) => onSubmit(e)}>
            <div className="mb-3" style={{color:'white'}}>
                <label htmlFor="userName">First Name  </label>
                <input 
                 value={client.nom}
                 onChange={(e) => onInputChange(e)}
                className="form-control my-input my-2" 
                type="text" 
                id="nom"
                name="nom"
                placeholder="first name"/>
            </div>
            <div className="mb-3" style={{color:'white'}}>
                <label htmlFor="password">Last Name </label>
                <input 
                value={client.prenom}
                onChange={(e) => onInputChange(e)}
                className="form-control my-input my-2" 
                type="text" 
                id="prenom"
                name="prenom"
                placeholder="last name"/>
            </div>
            <div className="mb-3" style={{color:'white'}}>
                <label htmlFor="password">National Identity Card</label>
                <input 
                value={client.cIN}
                onChange={(e) => onInputChange(e)}
                className="form-control my-input my-2" 
                type="text" 
                id="prenom"
                name="cIN"
                placeholder="National Identity Card"/>
            </div>
            <div className="mb-3" style={{color:'white'}}>
                <label htmlFor="password">Birthdate</label>
                <input 
                value={client.dateNaissance}
                onChange={(e) => onInputChange(e)}
                className="form-control my-input my-2" 
                type="date" 
                id="dateNaissance"
                name="dateNaissance"
                placeholder="Birthdate"/>
            </div>
            <div className="mb-3" style={{color:'white'}}>
                <label htmlFor="password">Adress</label>
                <input 
                value={client.adresse}
                onChange={(e) => onInputChange(e)}
                className="form-control my-input my-2" 
                type="text" 
                id="adresse"
                name="adresse"
                placeholder="Adress"/>
            </div>
            <div className="mb-3" style={{color:'white'}}>
                <label htmlFor="password">Email</label>
                <input 
                value={client.email}
                onChange={(e) => onInputChange(e)}
                className="form-control my-input my-2" 
                type="email" 
                id="email"
                name="email"
                placeholder="Email"/>
            </div>
            <div className="mb-3" style={{color:'white'}}>
                <label htmlFor="password">Phone Number</label>
                <input 
                value={client.numTele}
                onChange={(e) => onInputChange(e)}
                className="form-control my-input my-2" 
                type="text" 
                id="numTele"
                name="numTele"
                placeholder="Phone Number"/>
            </div>
            <div className="mb-3" style={{color:'white'}}>
                <label htmlFor="password">password</label>
                <input 
                value={client.password}
                onChange={(e) => onInputChange(e)}
                className="form-control my-input my-2" 
                type="password" 
                id="password"
                name="password"
                placeholder="password"/>
            </div>
            
            <div className="d-flex justify-content-between align-items-center">
                <Link type="submit" className="btn btn-light py-2 m-0" to="/login"> 
                    {/*isLoading ===true ? <i className="spinner-border border-3"></i>:'Login'*/}Login
                </Link>
                    {/*error?<div className="alert alert-danger py-2 m-0">{error}</div>:''*/}
                <button type="submit" className="btn btn-light py-2 m-0" > 
                    Register
                </button>
            </div>
        </form>
        </div>
      </div>
  )
}
export default Signup;