
import React, { useState, useEffect } from 'react';
import { useSpring, animated } from 'react-spring';

function Home() {
  const [carIndex, setCarIndex] = useState(0);

  const cars = [
    'bmw',
    'Fiat',
    'jeep',
    'mercedes',
    'Renault',
    'Volkswagen',
  ];

  const scrollSpring = useSpring({
    opacity: 1,
    from: { opacity: 0 },
  });

  useEffect(() => {
    const intervalId = setInterval(() => {
      setCarIndex((prevIndex) => (prevIndex + 1) % cars.length);
    }, 1500);

    return () => clearInterval(intervalId);
  }, []);

  return (
    <animated.div style={scrollSpring}>
      <div style={{ height: '100vh', display: 'flex', alignItems: 'center', justifyContent: 'space-between', backgroundColor: '#1a237e', marginTop: '0' }}>
        <div className={cars[carIndex]} style={{marginLeft:'40px'}}>
          <img src={`./Image/${cars[carIndex]}.PNG`} alt={`${cars[carIndex]} car`} style={{ width: '600px' }} />
        </div>
        <div>
            <h1 style={{color:'white'}}>CarRental</h1>
            <br/>
            <p style={{color:'white',margin:'40px',fontFamily: 'Rubik, sans-serif',fontWeight:'550'}} >
        Don't miss the opportunity to live an exceptional driving experience. Book today with CarRental and let us turn your journey into an unforgettable adventure. Endless roads await you, and we're here to help you explore them with ease. Make your reservation now and get ready to experience the freedom of the road with CarRental!
        </p></div>
      </div>
    </animated.div>
  );
}

export default Home;

