import axios from 'axios'
import React, { useState, useEffect } from 'react'
import { useLocation, useParams } from 'react-router-dom';
import { Link } from 'react-router-dom';
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';
import { faTrashAlt, faTimes  } from '@fortawesome/free-solid-svg-icons';

export default function MyReservations() {

  //const location = useLocation();
  //const client = location.state ? location.state.client : {}; 

  const { email } = useParams();

  const [myReservations, setMyReservations] = useState([])

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
    
    setMyReservations(response.data.reservationResponses);
   
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

//console.log(`client : ${client.nom}`);
console.log(email)
  const supprimerReservation =async(id)=>{
    try {
     await axios.delete(`http://localhost:8080/reservations/${id}`);
     getClient();
     
    } catch (error) {
    console.log("erreu :"+error);
  }
}
  const currentDate = new Date();
  console.log(myReservations);

  const obtenirPartieDate = (dateComplet) => {
    const dateObj = new Date(dateComplet);
    
    // Obtenir l'année, le mois et le jour
    const annee = dateObj.getFullYear();
    const mois = (dateObj.getMonth() + 1).toString().padStart(2, '0'); // Les mois vont de 0 à 11
    const jour = dateObj.getDate().toString().padStart(2, '0');
    
    // Format de sortie : "yyyy-mm-dd"
    const partieDate = `${annee}-${mois}-${jour}`;
    
    return partieDate;
  };

  return <>
    <h1>My Reservations</h1>
    <div style={{height:'100vh',display:'flex',justifyContent:'center',margin:'2%',overflowY: 'auto'}}>
      <div style={{width:'95vw',boxShadow:' 10px 10px 20px rgba(0, 0, 0, 0.5)' }}>
      <div style={{display:'flex',justifyContent:'center',backgroundColor: 'white',padding:'20px',marginTop:'2%'}}>
        <table className="table w-70 table-secondary table-striped shadow p-3 mb-5 bg-white rounded">
          <thead>
            <tr >
              <th></th>
              <th scope="col">Date Debut</th>
              <th scope="col">Date Fin</th>
              <th scope="col">Date Reservation</th>
              <th scope="col">livree</th>
              <th scope="col">confirmee</th>
              <th scope="col">payee</th>
              <th scope="col">modele voiture</th>
              <th scope="col">matricule voiture</th>
              
            </tr>
          </thead>
          <tbody>
            {myReservations.map((reservation, index) => (
              <tr key={index}>
                <td>
                <img
                        key={index}
                        src={`data:image/png;base64,${reservation.vehiculeResponce.photos}`} 
                        alt={`Photo ${index + 1}`}
                        style={{ width: '150px' }}
                      />
                </td>
              <td>{obtenirPartieDate(reservation.dateDebut)}</td>
                <td>{obtenirPartieDate(reservation.dateFin)}</td>
                <td>{obtenirPartieDate(reservation.dateReservation)}</td>
                <td>{reservation.estLivrer?'oui' : 'pas encore'}</td>
                <td>{reservation.estConfirmet?'oui' : 'pas encore'}</td>
                <td>{reservation.estPaye?'oui' : 'pas encore'}</td>
                <td>{reservation.vehiculeResponce.modele}</td>
                <td>{reservation.vehiculeResponce.matricule}</td>
                
              </tr>
            ))}
          </tbody>
        </table>
        </div>
      </div>
    </div>
</>
}
