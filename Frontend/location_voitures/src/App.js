import './App.css';
import { BrowserRouter as Router, Routes, Route } from "react-router-dom";

import NavBarHome from './Component/Home/NavBarHome';
import Home from './Component/Home/Home';
import AboutAs from './Component/Home/AboutAs';
import FeedBack from './Component/Home/FeedBack';
import Contact from './Component/Home/Contact';
import Footer from './Component/Home/Footer';
import Login from './Component/Login/Login';
import Admin from './Component/Admin/Admin';
import Companies from './Component/Admin/Companie/Companies';
import Edit from './Component/Admin/Companie/Edit';
import Car from './Component/Admin/Car/Car';
import AddVehicle from './Component/Admin/Car/AddVehicle';
import AddPhoto from './Component/Admin/Car/AddPhoto';
import EditCar from './Component/Admin/Car/EditCar';
import Characteristique from './Component/Admin/Characteristic/Characteristique';
import EditCharacteristic from './Component/Admin/Characteristic/EditCharacteristic';
import Agency from './Component/Admin/Agencey/Agency';
import EditAgency from './Component/Admin/Agencey/EditAgency';
import Livreur from './Component/Livreur/Livreur';
import Signup from './Component/Login/Signup';
import Book from './Component/Admin/Book/Book';
import Client from './Component/Client/Client';
import Cars from './Component/Client/Cars/Cars'
import MyReservations from './Component/Client/MyReservations';
import Question from './Component/Client/Question';
import Reservation from './Component/Client/Reservation'
import Succes from './Component/Client/Succes'
import ServiceClient from './Component/ClientService/ServiceClient';

function App() {
  return (
    <div className="App">
      <Router>
        <NavBarHome />
        <Routes>
        <Route exact path="/" element={<Home/>} />
        <Route exact path="/aboutus" element={<AboutAs/>} />
        <Route exact path="/feedback" element={<FeedBack/>} />
        <Route exact path="/contact" element={<Contact/>} />
        <Route exact path="/login" element={<Login/>} />
        <Route exact path="/admin" element={<Admin/>} />
        <Route exact path="/companie" element={<Companies/>} />
        <Route exact path="/edit/:id" element={<Edit/>} />
        <Route exact path="/car" element={<Car/>} />
        <Route exact path="/addvehicle" element={<AddVehicle/>} />
        <Route exact path="/addphoto/:id" element={<AddPhoto/>} />
        <Route exact path="/editcar/:id" element={<EditCar/>} />
        <Route exact path="/book" element={<Book/>} />
        <Route exact path="/characteristic" element={<Characteristique/>} />
        <Route exact path="/EditCharacteristic/:id" element={<EditCharacteristic/>} />
        <Route exact path="/agency" element={<Agency/>} />
        <Route exact path="/editagency/:id" element={<EditAgency/>} />
        <Route exact path="/livreur" element={<Livreur/>} />
        <Route exact path="/signeup" element={<Signup/>} />
        <Route exact path="/client/:email" element={<Client/>} />
        <Route exact path="/cars/:email" element={<Cars/>} />
        <Route exact path="/my_reservations/:email" element={<MyReservations/>} />
        <Route exact path="/question/:email" element={<Question/>} />
        <Route exact path="/reservation" element={<Reservation/>} />
        <Route exact path="/succes" element={<Succes/>} />
        <Route exact path="/service_client/:email" element={<ServiceClient/>} />
        </Routes>
        <Footer/>
      </Router>
    </div>
    );
  }

export default App;
