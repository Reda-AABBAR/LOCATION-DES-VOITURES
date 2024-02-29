import axios from 'axios';
import React, { useEffect, useState } from 'react'
import { Link } from 'react-router-dom';
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';
import { faFolderPlus } from '@fortawesome/free-solid-svg-icons';
import { faTrashAlt } from '@fortawesome/free-solid-svg-icons';
import { faEdit } from '@fortawesome/free-solid-svg-icons';
 function Car() {

  const [vehicles, setVehicles] = useState([]);

  useEffect(() => {
    getAllVehicles();
  }, []);
  
  const getAllVehicles = async () => {
    try {
      const result = await axios.get(" http://localhost:8080/vehicules");
      console.log(result);
      setVehicles(result.data); // Correction ici
    } catch (error) {
      console.log("Erreur :" + error);
    }
  }
  const supprimerCar =async(id)=>{
    try {
     await axios.delete(`http://localhost:8080/vehicules/${id}`);
     getAllVehicles ();
    } catch (error) {
    console.log("erreu :"+error);
  }
  }
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
  

  return (
    <div style={{height:'100vh',display:'flex',justifyContent:'center',margin:'2%',overflowY: 'auto'}}>
      <div style={{width:'95vw',boxShadow:' 10px 10px 20px rgba(0, 0, 0, 0.5)' }}>
        <div style={{ height: '15vh', display: 'flex', justifyContent: 'space-between', alignContent: 'center', color: 'white', backgroundColor: '#1a237e' }}>
          <h2 style={{ margin: '15px', }}>All Car</h2>
          <div style={{ marginLeft: 'auto', marginRight: '15px', marginTop: '15px' }}>
            <Link className="btn btn-outline-light" to="/addvehicle">Add Vehicle</Link>
          </div>
        </div>
        <div style={{display:'flex',justifyContent:'center',backgroundColor: 'white',padding:'20px',marginTop:'2%'}}>
        <table className="table w-70 table-secondary table-striped shadow p-3 mb-5 bg-white rounded">
          <thead>
            <tr >
            <th scope="col">Photo</th>
              <th scope="col">Serial number</th>
              <th scope="col">car model</th>
              <th scope="col">Daily Rate</th>
              <th scope="col">Mileage</th>
              <th scope="col">Date model</th>
              <th scope="col">Company</th>
              <th scope="col">characteristic</th>
              <th scope="col">constraint</th>
              <th scope="col">Action</th>
            </tr>
          </thead>
          <tbody>
          {
            vehicles.map((vehicle, index) => (
              <tr key={index}>
                <td>
                  {Array.isArray(vehicle.photos) && vehicle.photos.length > 0 ? (
                    vehicle.photos.map((photo, index) => (
                      <img
                        key={index}
                        src={`data:image/png;base64,${photo}`} 
                        alt={`Photo ${index + 1}`}
                        style={{ width: '150px' }}
                      />
                    ))
                  ) : (
                    <Link to={`/addphoto/${vehicle.id}`}>
                      <FontAwesomeIcon icon={faFolderPlus} style={{ color: 'black' }} />
                    </Link>
                  )}
                </td>

                
                <td>{vehicle.matricule}</td>
                <td>{vehicle.modele}</td>
                <td>{vehicle.prixParJour}</td>
                <td>{vehicle.kilometrage}</td>
                <td>{obtenirPartieDate(vehicle.modeleDate)}</td>
                <td>{vehicle.companieResponse ? vehicle.companieResponse.nom : ""}</td>
                <td>{vehicle.caractiristiqueResponses.length > 0 ? vehicle.caractiristiqueResponses[0].nom : ""}</td>
                <td>
                  {vehicle.contrats.map((contrat, index) => (
                    <p key={index}>{contrat}</p>
                  ))}
                </td>
                <td>
                  <button
                    className="btn  mx-2"
                    onClick={() => supprimerCar(vehicle.id)}
                  >
                  <FontAwesomeIcon icon={faTrashAlt} style={{ color: 'red' }} />
                  </button>
                  <Link className="btn  mx-2" to={`/editcar/${vehicle.id}`}>
                    <FontAwesomeIcon icon={faEdit} style={{ color: 'blue' }} />
                  </Link>
                </td>
              </tr>
            ))
          }
          </tbody>
        </table>
        </div>
      </div>
    </div>
  )
}
export default Car;