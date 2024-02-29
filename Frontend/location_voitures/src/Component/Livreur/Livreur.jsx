import axios from 'axios';
import React, { useEffect, useState } from 'react';

 function Livreur() {

  const [books,setBooks]=useState([]);

  useEffect(() => {
      getAllBook();
    }, []);
    
    const getAllBook = async () => {
      try {
        const result = await axios.get("  http://localhost:8080/reservations/confermed/notDelivred");
        console.log(result);
        setBooks(result.data); // Correction ici
      } catch (error) {
        console.log("Erreur :" + error);
      }
    }
    const bookDelivery =async(id)=>{
      try {
       await axios.put(`   http://localhost:8080/reservations/livrer/${id}`);
       getAllBook();
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
    <div style={{height:'100vh',display:'flex',justifyContent:'center',alignContent:'center'}}>
        <div style={{marginTop:'4%',width:'80vw',boxShadow:' 10px 10px 20px rgba(0, 0, 0, 0.5)',marginBottom:'40px'}}>
            <div style={{height:'10vh',backgroundColor:'#1a237e',color:'white',display:'flex',justifyContent:'center',alignContent:'center'}}><h1>List of Books</h1></div>
            <div style={{height:'90vh',overflowY: 'auto',marginTop:'2%',padding:'40px'}}>
            <table className="table w-70 table-secondary table-striped shadow p-3 mb-5 bg-white rounded">
                <thead>
                    <tr >
                    <th scope="col">#</th>
                    <th scope="col">client's name</th>
                    <th scope="col">start date</th>
                    <th scope="col">end date</th>
                    <th scope="col">delivery driver</th>
                    <th scope="col">car registration number</th>
                    <th>Action</th>
                    </tr>
                </thead>
                <tbody>
                {
                    books.map((book,index)=>(
                        <tr key={index}>
                            <td>{book.id}</td>
                            <td>{book.clientResponse.nom}</td>
                            <td>{obtenirPartieDate(book.dateDebut)}</td>
                            <td>{obtenirPartieDate(book.dateFin)}</td>
                            <td>{book.livreurResponse.nom}</td>
                            <td>{book.vehiculeResponce.matricule}</td>
                            <td>
                              <button
                                className="btn btn-outline-success" 
                                onClick={() => bookDelivery(book.id)}
                              >
                              Delivery
                              </button>
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
export default Livreur;