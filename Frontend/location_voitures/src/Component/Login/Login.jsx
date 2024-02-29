import React, { useState } from 'react';
import {Link , useNavigate } from 'react-router-dom'
import axios from 'axios';
import jwt_decode from 'jwt-decode';

export default function Login() {

    let navigate = useNavigate();

    const [login,setLogin]=useState({
        email:"",
        password:""
    })

    const onInputChange=(e)=>{
        setLogin({...login, [e.target.name]: e.target.value});
      };

      const onSubmit = async (e) => {
        e.preventDefault();
        try {
            const response = await axios.post('http://localhost:8080/auth/login', null, {
                params: {
                    email: login.email,
                    password: login.password
                }
            }, { withCredentials: true });
            // Récupérer le token depuis la réponse de l'API
            const accessToken = response.data['access-token'];
          

            // Stocker le token dans le localStorage
            localStorage.setItem('accessToken', accessToken);
            const decoded =  jwt_decode(accessToken);
            console.log('Token décodé :', decoded);
            // Vous pouvez également imprimer le token pour vérification
            console.log('Token stocké dans le localStorage:', accessToken);
            axios.defaults.headers.common['Authorization'] = `Bearer ${accessToken}`;

           
            console.log(response)
            if(decoded.scope=='ADMIN')
            {
                navigate("/admin");
            }
           else  if(decoded.scope=='LIVREUR')
            {
                navigate("/livreur");
            }
            else  if(decoded.scope=='CLIENT')
            {
                navigate(`/client/${decoded.sub}`);
            }
            else
            {
                navigate(`/service_client/${decoded.sub}`);
            }

            
        } catch (error) {
            console.error("Error:", error.response); // Affichez la réponse d'erreur dans la console
        }
    };

  return (
    <div style={{
        height: '100vh',
        backgroundImage: 'url("./Image/bg.PNG")',
        backgroundPosition: 'center',
        backgroundSize: 'cover',
        display:'flex',
        alignContent:'center',
        justifyContent:'center',
        
      }}>
        <div style={{width:'400px',height:'300px',marginTop:'7%',padding:'40px',backgroundColor :'#1a237e' }}>
        <form  onSubmit={(e) => onSubmit(e)}>
            <div className="mb-3" style={{color:'white'}}>
                <label htmlFor="userName">Email  </label>
                <input 
                 value={login.email}
                 onChange={(e) => onInputChange(e)}
                className="form-control my-input my-2" 
                type="text" 
                id="email"
                name="email"
                placeholder="Email"/>
            </div>
            <div className="mb-3" style={{color:'white'}}>
                <label htmlFor="password">password </label>
                <input 
                 value={login.password}
                 onChange={(e) => onInputChange(e)}
                 className="form-control my-input my-2" 
                type="password" 
                id="password"
                name="password"
                placeholder="password"/>
            </div>
            
            <div className="d-flex justify-content-between align-items-center">
                <button type="submit" className="btn btn-light py-2 m-0" > 
                    Login
                </button>
                <Link type="submit" className="btn btn-light py-2 m-0" to="/signeup"> 
                    SignUp
                </Link>
            </div>
        </form>
        </div>
      </div>
  )
}
