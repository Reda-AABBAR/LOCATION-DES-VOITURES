import React, { useEffect, useState } from 'react';
import Joi from 'joi';
import { useLocation, useNavigate } from 'react-router-dom';
import DatePicker from 'react-datepicker';
import 'react-datepicker/dist/react-datepicker.css';
import axios from 'axios';

export default function Reservation() {

  const location = useLocation();
  const navigate = useNavigate();
  const carData = location.state ? location.state.carData : null;
  const email = location.state.email;

  const [startDate, setStartDate] = useState(new Date());
  const [endDate, setEndDate] = useState(new Date());
  const [prixParJour, setPrixParJour] = useState(carData.prixParJour);
  const [totalPrice, setTotalPrice] = useState(0);
 
  const [reservation,setReservation] = useState({
    dateDebut: "2024-01-17",
    dateFin: "2024-01-21",
    estContratSegnie: true,
    estPaye: false,
    estConfirmet: false,
    estLivrer: false,
    clientRequest: {
      nom: "",
      prenom: "",
      cIN: "",
      dateNaissance: "",
      adresse: "",
      email: "",
      numTele: "",
      password: ""
    },
    livreurRequest:{
      nom: "addakirir",
      prenom: "addakiri",
      cIN: "addakiri",
      dateNaissance: "2024-01-24",
      adresse: "fdtey",
      email: "add@addoum",
      numTele: "1234",
      password: "1234"
    },
    vehiculeRequest: {
      matricule: "ahn54fr",
      modele: "Toyota Corolla",
      prixParJour: 300,
      kilometrage: 150000,
      modeleDate: "2017-01-04",
      photos: null,
      contrats: null,
      reservationRequest: null,
      caractiristiqueRequests : null,
      companieRequest: {nom : "x"},
      agenceRequest : null
    },
    administrateurRequest:{
      nom: "admin",
      prenom: "admin",
      cIN: "admin",
      dateNaissance: "2024-01-23",
      adresse: "admin",
      email: "admin@admin",
      numTele: "456789",
      password: "1234"
    }
  })

  const [acceptConditions, setAcceptConditions] = useState(false);

  const [paymentMethod, setPaymentMethod] = useState('');
  const [showCardFields, setShowCardFields] = useState(false);
  const [isPaymentSectionValid, setIsPaymentSectionValid] = useState(false);
  const [fields, setFields] = useState({
    numero_carte : 0,
    code_securite : 0
  })

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
    setReservation((prevReservation) => ({
      ...prevReservation,
      clientRequest : client
    }));
  
  }

  useEffect(() => {
    getClient();
    
  },)


  const [errorList, setErrorList] = useState([])
    // Fonction pour gérer le changement de mode de paiement
    const handlePaymentMethodChange = (event) => {
      const selectedMethod = event.target.value;
      setPaymentMethod(selectedMethod);
  
      // Afficher les champs de carte uniquement si le mode de paiement est par carte
      setShowCardFields(selectedMethod === 'par_carte');
    };
  
    // Affichez les champs de carte uniquement si le mode de paiement est par carte
    const cardFields = showCardFields && (
      <>
        {/* Ajoutez ici les champs pour le paiement par carte */}
        <div>
        {errorList.map((err, index) =>{
          if(err.context.label === 'numero_carte')
          {
              return <div key={index} className='alert alert-danger my-2'>le numero de la carte doit etre compose de 16 chiffres</div>
          }
          else{
              return <div key={index} className='alert alert-danger my-2'>le code de securite doit etre compose de 3 chiffres</div>
          }
      })}
          <label style={{color: 'white'}} htmlFor='numero_carte'>Numéro de carte :</label><br></br>
          <input type="text" name='numero_carte' id='numero_carte' onChange={getCardData}/>
        </div>
        <div>
          <label style={{color: 'white'}}>Date d'expiration :</label><br></br>
          <input type="Date" />
        </div>
        <div>
          <label style={{color: 'white'}} htmlFor='code_securite'>Code de sécurité :</label><br></br>
          <input type="text" name='code_securite' id='code_securite' onChange={getCardData}/><br></br>
          <button type="button" onClick={cardFieldsValid} >
                Valider
          </button>
        </div>
      </>
    );

    console.log(email);
  const handlePrixParJourChange = (event) => {
    setPrixParJour(Number(event.target.value)); // Convertir en nombre
  };

  const handleEndDateChange = (date) => {
    setEndDate(date);

    setReservation((prevReservation) => ({
      ...prevReservation,
      dateFin: date
    }));

    // Calculer le nombre de jours entre la date de début et la nouvelle date de fin
    const numberOfDays = Math.floor((date - startDate) / (24 * 60 * 60 * 1000));

    // Vérifier que le prix par jour est défini et est un nombre positif
    if (prixParJour && typeof prixParJour === 'number' && prixParJour > 0) {
      // Calculer le nouveau prix total
      const calculatedTotalPrice = numberOfDays * prixParJour;
      setTotalPrice(calculatedTotalPrice);
    }
  };

  const handleStartDateChange = (date) => {
    setStartDate(date);

    setReservation((prevReservation) => ({
      ...prevReservation,
      dateDebut: date
    }));

    // Calculer le nombre de jours entre la nouvelle date de début et la date de fin
    const numberOfDays = Math.floor((endDate - date) / (24 * 60 * 60 * 1000));

    // Vérifier que le prix par jour est défini et est un nombre positif
    if (prixParJour && typeof prixParJour === 'number' && prixParJour > 0) {
      // Calculer le nouveau prix total
      const calculatedTotalPrice = numberOfDays * prixParJour;
      setTotalPrice(calculatedTotalPrice);
    }
  };

  const handleReservationSubmit = (event) => {
    event.preventDefault();

    // Calculer le nombre de jours entre la date de début et la date de fin
    const numberOfDays = Math.floor((endDate - startDate) / (24 * 60 * 60 * 1000));

    // Calculer le prix total
    const calculatedTotalPrice = numberOfDays * prixParJour;
    setTotalPrice(calculatedTotalPrice);

    // Ajouter ici la logique pour envoyer les données de réservation au backend, par exemple, avec Axios.
    // Une fois la réservation effectuée, vous pouvez rediriger l'utilisateur ou afficher un message de confirmation.
  };



  useEffect(() => {
    updateVehiculeReservation();
  }, [])
  

  function updateVehiculeReservation (){
    setReservation((prevReservation) => ({
      ...prevReservation,
      vehiculeRequest: {
        ...prevReservation.vehiculeRequest,
        matricule: carData.matricule,
        modele: carData.modele,
        prixParJour: carData.prixParJour,
        kilometrage: carData.kilometrage,
        modeleDate: carData.modeleDate,
        companieRequest: {nom: carData.companieResponse.nom},

      },
    }));

  }

  function getCardData(eventInfo)
  {
    let cardFields = {...fields}
    cardFields[eventInfo.target.name] = eventInfo.target.value;
    setFields(cardFields)
    console.log(fields)
  }

  function validatePaymentSection()
  {
   let scheme = Joi.object({
       numero_carte:Joi.number().min(1000000000000000).max(7999999999999999).required(),
       code_securite:Joi.number().min(100).max(999).required()
   });
   return scheme.validate(fields, {abortEarly:false})
  }

  function cardFieldsValid(){
    let validation = validatePaymentSection();
    if(validation.error){
      setErrorList(validation.error.details)
    }
    setReservation((prevReservation) => ({
      ...prevReservation,
      estPaye : true
    }));
  }


  async function sentReservation() {
    try {
      let response = await axios.post('http://localhost:8080/reservations', reservation);
     console.log('Response from server:', JSON.stringify(reservation, null, 2));
     console.log(carData);
    } catch (error) {
      console.error('Error submitting reservation:', error);
    }}

  async function submitReservation(e){
    e.preventDefault();
    await sentReservation();
   // console.log('Submitting reservation:', JSON.stringify(reservation, null, 2));
   navigate('/succes');
  }
  return (
    <div className="d-flex justify-content-center align-items-center" style={{ height: '100vh' ,backgroundColor: '#1a237e'}}>
      <div className="mx-auto w-50 d-flex">
      <form className="mx-auto w-50 my-form">
        <label htmlFor="car" style={{color: 'white'}}> Car :</label>
        <input
          type="text"
          className="form-control my-input my-2"
          name="car"
          id="car"
          value={carData ? `${carData.modele} - ${carData.modelYear}` : ''}
          readOnly
        />

        <label htmlFor="dateDebut" style={{color: 'white'}}> Date Debut :</label>
        <div className="d-flex flex-column">
          <DatePicker selected={startDate} onChange={handleStartDateChange} className="form-control my-input my-2 w-100" />
        </div>

        <label htmlFor="dateFin" style={{color: 'white'}}> Date Fin :</label>
        <div className="d-flex flex-column">
          <DatePicker selected={endDate} onChange={handleEndDateChange} className="form-control my-input my-2 w-100" />
        </div>

        <label htmlFor="prixParJour" style={{color: 'white'}}> Prix Par Jour :</label>
        <input
          type="text" // Utiliser le type number pour l'entrée de prix par jour
          className="form-control my-input my-2"
          name="prixParJour"
          id="prixParJour"
          value={prixParJour}
          onChange={handlePrixParJourChange}
          readOnly
          onWheel={(e) => e.preventDefault()}
        />

        {/* Afficher le prix total calculé au-dessus du bouton "Confirm" */}
        {totalPrice > 0 && (
            <div style={{ backgroundColor: 'yellow' , color:'#1a237e'}}>
            <p>Prix Total : {totalPrice} DH</p>
            </div>
        )}
 {/* Conditions */}
 <div style={{ padding: '20px', marginBottom: '20px', maxHeight: '100px', overflow: 'auto', border: '1px solid #ccc', borderRadius: '8px', backgroundColor: '#f9f9f9' }}>
  <h4 style={{ color: '#1a237e', marginBottom: '15px' }}>Conditions de location :</h4>
  <p style={{ textAlign: 'justify', lineHeight: '1.5' }}>
    En cochant la case "J'accepte les conditions générales", vous acceptez les termes et conditions suivants :
    <br /><br />
    <strong>Documents nécessaires :</strong> Vous devez présenter un permis de conduire valide et une carte d'identité ou un passeport pour louer une voiture. Vous pouvez également être invité à fournir un justificatif de domicile et une carte de crédit valide.
    <br /><br />
    <strong>Conditions financières :</strong> Vous devez payer le montant total de la location avant de prendre possession de la voiture. Un dépôt de garantie peut également être requis. Les frais supplémentaires, tels que les frais de carburant, les frais de nettoyage ou les frais de retard, peuvent être facturés en cas de non-respect des conditions de location.
    <br /><br />
    <strong>Responsabilité civile :</strong> Vous êtes responsable de tout dommage causé à la voiture pendant la période de location. Vous devez également vous assurer que vous êtes couvert par une assurance responsabilité civile en cas d'accident impliquant une autre personne.
    <br /><br />
    <strong>Utilisation de la voiture :</strong> Vous devez utiliser la voiture conformément aux lois et règlements en vigueur. Vous ne pouvez pas utiliser la voiture pour des activités illégales ou dangereuses.
    <br /><br />
    <strong>Retour de la voiture :</strong> Vous devez retourner la voiture à l'heure convenue et dans l'état dans lequel vous l'avez reçue. Des frais supplémentaires peuvent être facturés en cas de retard ou de dommages à la voiture.
    <br /><br />
    <strong>Annulation de la réservation :</strong> Vous pouvez annuler votre réservation à tout moment, mais des frais d'annulation peuvent s'appliquer en fonction des conditions de location.
    <br /><br />
    En acceptant ces conditions générales, vous reconnaissez avoir lu et compris les termes et conditions de la location de voiture et vous vous engagez à les respecter.
  </p>
  {/* Checkbox pour accepter les conditions */}
  <label style={{ display: 'block', marginTop: '15px' }}>
    <input
      type="checkbox"
      checked={acceptConditions}
      onChange={() => {
        setAcceptConditions(!acceptConditions)
        setReservation(prevReservation => ({
          ...prevReservation,
          estContratSegnie : !acceptConditions
        }))
      
      }}
    />
    &nbsp;J'accepte les conditions de location
  </label>
</div>




      {/* Bouton "Confirm" */}
      <button type="submit" className="btn btn-info" disabled={!acceptConditions} onClick={submitReservation}>
        Confirm
      </button>
      </form>
          {/* Affichez les champs de carte à côté du formulaire */}
    <div className="payment-section">
      
    {/* Ajoutez le bloc de sélection du mode de paiement */}
    <br></br>
    <br></br>
    <label style={{color: 'white'}}>Choisir méthode de paiement :</label><br></br>
    <select onChange={handlePaymentMethodChange}>
      <option value="espece">Espèce</option>
      <option value="par_carte">Par carte</option>
    </select><br></br>

      {cardFields}<br></br>


    </div>
    </div>
    </div>
  );
}
