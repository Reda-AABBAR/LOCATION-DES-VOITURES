import axios from 'axios';
import React, { useState, useEffect } from 'react';
import { useNavigate, useParams } from 'react-router-dom';
import { useSpring, animated } from 'react-spring';
import carStyle from './Cars.module.css';

export default function Cars() {
  let navigate = useNavigate();

  const { email } = useParams();

  const [hoveredItem, setHoveredItem] = useState(null);
  const [selectedModel, setSelectedModel] = useState(null);
  const [originalCarsData, setOriginalCarsData] = useState([]);
  const [filteredCarsData, setFilteredCarsData] = useState([]);
  const [reservations, setReservations] = useState([])
  const [loadingReservations, setLoadingReservations] = useState(true);
  const [loadingCars, setLoadingCars] = useState(true);

  const handleItemHover = (item) => {
    setHoveredItem(item);
  };

  const handleItemClick = (item) => {
    setSelectedModel(item);
  };

  const sidebarProps = useSpring({
    width: '15%', // La Sidebar prendra 1/4 de la page
    from: { width: '0%' }, // La Sidebar commence avec une largeur de 0
  });

  async function getAllReservations() {
    try {
        const response = await axios.get('http://localhost:8080/reservations');
        console.log(response);
        setReservations(response.data);
    } catch (error) {
        console.error('Error fetching reservations:', error);
    }
    finally {
      setLoadingReservations(false);
      console.log(reservations);
    }
    
}

const hasReservation = (car) => {
  return reservations.some((reservation) => {
    return (
      reservation.vehiculeResponce &&
      car &&
      car.matricule &&
      reservation.vehiculeResponce.matricule.toLowerCase() === car.matricule.toLowerCase()
    );
  });
};
  async function getAllCars() {
    try {
      const response = await axios.get('http://localhost:8080/vehicules');
      const updatedCarsData = response.data.map((data) => {
        const dateObj = new Date(data.modeleDate);
        const year = dateObj.getFullYear();
        {/*console.log(response);*/}
        return { ...data, modelYear: year };
      });

      setOriginalCarsData(updatedCarsData);
      setFilteredCarsData(updatedCarsData);
    } catch (error) {
      console.error('Erreur lors de la récupération des voitures :', error);
    }finally {
      setLoadingCars(false);
    }
  }

  
  useEffect(() => {
    getAllReservations();
  }, []);


  console.log(reservations);
  useEffect(() => {
    getAllCars();
  }, []);

  useEffect(() => {
    filterCarsByModel();
  }, [selectedModel]);

  function filterCarsByModel() {
    if (selectedModel) {
      const filteredCars = originalCarsData.filter((car) => car.modele === selectedModel);
      setFilteredCarsData(filteredCars);
    } else {
      // Si aucun modèle n'est sélectionné, affiche toutes les voitures
      setFilteredCarsData(originalCarsData);
    }
  }

  function moveToReservation(carData) {
    navigate('/reservation', {
      state: {
        carData: {
          ...carData,
          modelYear: carData.modelYear, // Ajoutez l'année du modèle ici
        },
        email: email,
      },
    });
  }

  return (
    <div style={{ display: 'flex', height: '100vh' }}>
      {/* Sidebar */}
      <animated.div style={{ ...sidebarProps }} className={carStyle.sidebarScrollbarStyle}>
        <div>
          <h2 style={{ fontSize: '1.5em', marginBottom: '15px', color: '#FFFF00' }}>Make</h2>
          <ul style={{ listStyle: 'none', padding: 0 }}>
            {originalCarsData.map((car, index) => (
              <li
                key={index}
                style={{
                  marginBottom: '10px',
                  cursor: 'pointer',
                  backgroundColor: hoveredItem === car.modele ? '#FFFF00' : 'transparent',
                  transition: 'background-color 0.3s ease',
                  color: hoveredItem === car.modele ? '#1a237e' : '#fff',
                }}
                onMouseEnter={() => handleItemHover(car.modele)}
                onMouseLeave={() => handleItemHover(null)}
                onClick={() => handleItemClick(car.modele)}
              >
                {car.modele}
              </li>
            ))}
          </ul>
        </div>
      </animated.div>

      {/* Partie principale pour afficher les données sur les voitures */}
      <div style={{ flex: 1 }} className={carStyle.mainScrollbarStyle}>
        <div style={{ display: 'flex', flexWrap: 'wrap', justifyContent: 'space-between' }}>
          {!loadingReservations && !loadingCars &&filteredCarsData.map((car, index) => (
          <div key={index} className={carStyle.carContainer} style={{ position: 'relative' }}>
                    {!hasReservation(car) && (
                      <div className="disponible bg-success p-1 text-white position-absolute top-0 end-0">disponible</div>
                    )}
              <img
                src={`data:image/jpeg;base64,${car.photos}`}
                alt={car.name}
                style={{ width: '100%', height: '150px', objectFit: 'cover' }}
              />
              <h3 className={carStyle.titleText}>{`${car.modele} - ${car.modelYear}`}</h3>
              <p className={carStyle.text}>Matricule: {car.matricule}</p>
              <p className=''>Kilométrage: {car.kilometrage} Km</p>
              <p className=''>Prix par jour: {car.prixParJour} dh</p>
              {!hasReservation(car) && (
                <button className='btn btn-outline-primary my-2 btn-sm w-100' onClick={() => moveToReservation(car)}>Reserver</button>
              )}            </div>
          ))}
        </div>
      </div>
    </div>
  );
}
