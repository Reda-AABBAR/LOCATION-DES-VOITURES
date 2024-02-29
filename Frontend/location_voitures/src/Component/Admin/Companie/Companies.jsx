import React, {useEffect, useState } from 'react'
import axios from 'axios';
import { Link , useNavigate} from 'react-router-dom';
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';
import { faTrashAlt } from '@fortawesome/free-solid-svg-icons';
import { faEdit } from '@fortawesome/free-solid-svg-icons';
import { faBuilding } from '@fortawesome/free-regular-svg-icons';

 function Companies() {
  let navigate = useNavigate();
  const [companies,setCompanies]=useState([]);
  const [companie,setCompanie]=useState({
    nom:""
  });

  useEffect (()=>{
    getAllCompanies();
  },[]);

  const getAllCompanies=async()=>{
    try {
      const result = await axios.get("http://localhost:8080/companies");
      console.log(result.data);
      setCompanies(result.data);
      } catch (error) {
      console.log("erreu :"+error);
    }
  }
  const supprimerCompanie =async(id)=>{
    try {
     await axios.delete(`http://localhost:8080/companies/${id}`);
     getAllCompanies();
    } catch (error) {
    console.log("erreu :"+error);
  }
  }

const onInputChange=(e)=>{
    setCompanie({...companie, [e.target.name]: e.target.value});
};

const onSubmit = async (e) => {
    e.preventDefault();
    try {
        await axios.post("http://localhost:8080/companies", companie);
        getAllCompanies();
    } catch (error) {
        console.error("Error:", error.response); // Affichez la réponse d'erreur dans la console
    }
};


  return (
    <div style={{ display: 'flex', justifyContent: 'space-between' }}>
    <div className='listcompanies' style={{ backgroundColor: 'white', height: '100vh', flex: 1 }}>
      <div style={{margin:'50px'}}>
      <table className="table w-70 table-secondary table-striped shadow p-3 mb-5 bg-white rounded">
          <thead>
            <tr>
              <th scope="col">#</th>
              <th scope="col">Name</th>
              <th scope="col">Action</th>
            </tr>
          </thead>
          <tbody>
            {
              companies.map((companie,index)=>(
                <tr key={index}>
                  <td>{companie.id}</td>
                  <td>{companie.nom}</td>
                  <td>
                    <button
                      className="btn  mx-2"
                      onClick={() => supprimerCompanie(companie.id)}
                    >
                    <FontAwesomeIcon icon={faTrashAlt} style={{ color: 'red' }} />
                    </button>
            
                    <Link className="btn  mx-2" to={`/edit/${companie.id}`}>
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
    <div className='ajoutercompanie' style={{ backgroundColor: '#1a237e', height: '100vh', width: "450px" }}>
    <div  style={{ color: '#1a237e' ,backgroundColor:'#d3d3d3',height:'55px'}}><h2>add a company</h2></div>
    <div style={{width:'400px',height:'300px',marginTop:'7%',padding:'40px' }}>
      
    <form onSubmit={(e) => onSubmit(e)}>
            <div className="mb-3">
            <label htmlFor="userName" style={{ color: 'white' }}>Name </label>
                <input 
                 value={companie.nom}
                 onChange={(e) => onInputChange(e)}
                className="form-control my-input my-2" 
                type="text" 
                id="nom"
                name="nom"
                placeholder="name"/>
            </div>
            <div className="d-flex justify-content-between align-items-center">
              <button type="submit"  className="btn btn-secondary py-2 m-0">
                  Add
              </button>
            </div>
        </form>
        <div style={{marginTop:'15%'}}>
          <FontAwesomeIcon icon={faBuilding} style={{color:'white',height:'100px'}}/>
        </div>
        </div>
    </div>
</div>

  )
}
export default Companies;
