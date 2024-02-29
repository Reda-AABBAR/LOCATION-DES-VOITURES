import React, {useEffect, useState } from 'react'
import axios from 'axios';
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';
import { faCar } from '@fortawesome/free-solid-svg-icons';
import {useNavigate} from "react-router-dom";

export default function AddVehicle() {

    let navigate = useNavigate();

    const [companies,setCompanies]=useState([]);
    const [agences,setAgences]=useState([]);
    const [caracteristiques,setCaracteristiques]=useState([]);
    const [vehicle,setVehicle]=useState({
        matricule:"",
        modele:"",
        prixParJour:0,
        kilometrage:0,
        modeleDate:"",
        photos:[],
        contrats : [],
        caractiristiqueRequests: [{
            nom:""
        }],
        companieRequest:{
            nom:""
        },
        agenceRequest:{
            nom:"",
            ville:"",
            adresse:""
        }
    })
    const handleChange = (event) => {
        const selectedAgence = event.target.value;
        const selectedAgenceObject = agences.find(agence => agence.nom === selectedAgence);

        // Update the agenceRequest property in the vehicle state with the selected agence object
        setVehicle((prevVehicle) => ({
            ...prevVehicle,
            agenceRequest: {
                nom: selectedAgenceObject.nom || "",
                ville: selectedAgenceObject.ville || "",
                adresse: selectedAgenceObject.adresse || ""
            }
        }));
    };

    const handleChangeCaracteristique = (event) => {
        const selectedCaracteristique = event.target.value;
        
        // Update the caractiristiqueRequests property in the vehicle state
        setVehicle((prevVehicle) => ({
            ...prevVehicle,
            caractiristiqueRequests: [{
                nom: selectedCaracteristique
            }]
        }));
    };
    const handleChangeCompany = (event) => {
        const selectedCompany = event.target.value;
        
        // Update the companieRequest property in the vehicle state
        setVehicle((prevVehicle) => ({
            ...prevVehicle,
            companieRequest: {
                nom: selectedCompany
            }
        }));
    };

    const handleInputChange = (fieldName, value) => {
        setVehicle((prevVehicle) => ({
            ...prevVehicle,
            [fieldName]: value
        }));
    };

    useEffect (()=>{
        getAllCompanies();
      },[]);
    
      const getAllCompanies=async()=>{
        try {
          const result1 = await axios.get("http://localhost:8080/companies");
          const result2 = await axios.get("http://localhost:8080/agences");
          const result3 = await axios.get(" http://localhost:8080/caractiristiques");
          console.log(result1.data);
          setCompanies(result1.data);
          setAgences(result2.data);
          setCaracteristiques(result3.data);
          } catch (error) {
          console.log("erreu :"+error);
        }
      }

      const onSubmit = async (e) => {
        e.preventDefault();
        try {
            // Convertissez les champs prixParJour et kilometrage en nombres
            const prixParJour = parseFloat(vehicle.prixParJour);
            const kilometrage = parseFloat(vehicle.kilometrage);
    
            // Utilisez selectedAgence pour obtenir le nom de l'agence sélectionnée
            const result = await axios.post("http://localhost:8080/vehicules", {
                ...vehicle,
                prixParJour,
                kilometrage,
            });
            console.log(vehicle);
            navigate("/car");
        } catch (error) {
            console.error("Error:", error.response);
        }
    };
    const [newContrat, setNewContrat] = useState(""); // État pour la nouvelle valeur saisie

    const onSubmit2 = (e) => {
        e.preventDefault();

        // Ajouter la nouvelle valeur saisie dans le tableau contrats
        setVehicle((prevVehicle) => ({
            ...prevVehicle,
            contrats: [...prevVehicle.contrats, newContrat],
        }));

        // Réinitialiser la valeur saisie après l'ajout
        setNewContrat("");
    };


  return (
    <div style={{height:'100vh',display: 'flex', justifyContent: 'space-around',marginBottom:'6%'}}>
      
        <div style={{width:'70vw',height:'100vh'}}>
            <div><h1>Add a Vehicul</h1></div>
            <div style={{marginTop:'3%',padding:'50px'}}>
                <form onSubmit={(e) => onSubmit(e)}>  
                <div style={{justifyContent:'space-around',display: 'flex'}}>
                        <div style={{width:'50vw',borderRight:'1px solid rgba(0, 0, 0, 0.5)',justifyContent:'center',padding:'30px'}}>
                        
                        <div className="mb-3 row">
                            <label  className="col-sm-2 col-form-label">Serial number</label>
                            <div className="col-sm-10">
                            <input
                                type="text"
                                className="form-control"
                                value={vehicle.matricule}
                                onChange={(e) => handleInputChange("matricule", e.target.value)}
                            />
                            </div>
                        </div>

                        <div className="mb-3 row">
                            <label  className="col-sm-2 col-form-label">car model</label>
                            <div className="col-sm-10">
                            <input type="text" className="form-control" id="inputPassword"
                             value={vehicle.modele}
                             onChange={(e) => handleInputChange("modele", e.target.value)}/>
                            </div>
                        </div>

                        <div className="mb-3 row">
                            <label  className="col-sm-2 col-form-label">Daily Rate</label>
                            <div className="col-sm-10">
                            <input type="number" className="form-control" id="inputPassword"
                            value={vehicle.prixParJour}
                            onChange={(e) => handleInputChange("prixParJour", e.target.value)}/>
                            </div>
                        </div>

                        <div className="mb-3 row">
                            <label  className="col-sm-2 col-form-label">Mileage</label>
                            <div className="col-sm-10">
                            <input type="number" className="form-control" id="inputPassword"
                            value={vehicle.kilometrage}
                            onChange={(e) => handleInputChange("kilometrage", e.target.value)}/>
                            </div>
                        </div>

                        <div className="mb-3 row">
                            <label  className="col-sm-2 col-form-label">Date model</label>
                            <div className="col-sm-10">
                            <input type="Date" className="form-control" id="inputPassword"
                            value={vehicle.modeleDate}
                            onChange={(e) => handleInputChange("modeleDate", e.target.value)}/>
                            </div>
                        </div>

                        

                        </div>
                        <div style={{width:'50vw',justifyContent:'center',justifyContent:'center',padding:'30px'}}>
                            <div className="mb-3 row">
                                <label  className="col-sm-2 col-form-label">Agency</label>
                                <select
                                    className="form-select form-select-lg mb-3"
                                    aria-label="Large select example"
                                    onChange={handleChange}
                                    value={vehicle.agenceRequest.nom}
                                >
                                
                                    {agences.map((agence, index) => (
                                        <option key={index} value={agence.nom}>
                                            {agence.nom}
                                        </option>
                                    ))}
                                </select>
                            </div>

                            <div className="mb-3 row">
                                <label  className="col-sm-2 col-form-label">Company</label>
                                <select
                                    className="form-select form-select-lg mb-3"
                                    aria-label="Large select example"
                                    onChange={handleChangeCompany}
                                    value={vehicle.companieRequest.nom}
                                >
                                    {companies.map((company, index) => (
                                        <option key={index} value={company.nom}>
                                            {company.nom}
                                        </option>
                                    ))}
                                </select>
                            </div>

                            <div className="mb-3 row">
                                <label  className="col-sm-2 col-form-label">characteristic</label>
                                <select
                                    className="form-select form-select-lg mb-3"
                                    aria-label="Large select example"
                                    onChange={handleChangeCaracteristique}
                                    value={vehicle.caractiristiqueRequests[0].nom}
                                >
                                    {caracteristiques.map((caracteristique, index) => (
                                        <option key={index} value={caracteristique.nom}>
                                            {caracteristique.nom}
                                        </option>
                                    ))}
                                </select>
                            </div>
                            <div className="d-flex justify-content-center align-items-center">
                                <button type="submit"  className="btn btn-primary py-2 m-0">
                                Add
                                </button>
                            </div>
                        </div>
                    </div>
                </form>
            </div>
        </div>
        <div  style={{width:'30vw',height:'100vh',backgroundColor: '#1a237e'}}>
            <div style={{marginTop:'6%',padding:'40px',color:'white'}}>
            <div  style={{ color: '#1a237e' ,backgroundColor:'#d3d3d3',height:'55px' }}><h2>add constraint</h2></div>
            <div style={{marginTop:'3%'}}>
            <form onSubmit={(e) => onSubmit2(e)}>
                <div>
                <div className="row g-3 align-items-center">
                    <div className="col-auto">
                        <label htmlFor="inputPassword6" className="col-form-label">constraint</label>
                    </div>
                    <div className="col-auto">
                        <input type="text"  className="form-control" aria-describedby="passwordHelpInline"
                        value={newContrat}
                        onChange={(e) => setNewContrat(e.target.value)}
                         />
                        
                    </div>
                </div>
                    <div className="d-flex justify-content-center align-items-center">
                        <button type="submit"  className="btn btn-primary py-2 m-3">
                        Add
                        </button>
                    </div>
                </div>
                </form>
                <div >
                <FontAwesomeIcon icon={faCar} style ={{ width:'170px', height:'100px',color:'black',marginTop:'50px'}}/>
                </div>
            </div>
               
            </div>
            
        </div>
        
    </div>
  )
}
