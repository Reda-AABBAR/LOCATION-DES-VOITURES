import React, { useState } from 'react';
import { useNavigate,useParams } from "react-router-dom";
import axios from 'axios';


export default function AddPhoto() {
  const [file, setFile] = useState(null);
  const [message, setMessage] = useState(null);
  const { id } = useParams();
  let navigate = useNavigate();

  const handleFileChange = (event) => {
    setFile(event.target.files[0]);
  };

  const handleUpload = async () => {
    if (!file) {
      setMessage('Please select a file');
      return;
    }

    const formData = new FormData();
    formData.append('file', file);

    try {
      const response = await axios.post(`http://localhost:8080/vehicules/images/${id}`, formData, {
        headers: {
          'Content-Type': 'multipart/form-data',
        },
      });
      navigate("/car");
      setMessage(response.data);
      setFile(null);
    } catch (error) {
      if (error.response) {
        setMessage(error.response.data);
      } else {
        setMessage('An error occurred while uploading the image');
      }
    }
  };

  return (
    <div style={{ height: '100vh', display: 'flex', justifyContent: 'center' }}>
      <div style={{ height: '80vh', width: '60vw', marginTop: '2%', padding: '50px', boxShadow: '10px 10px 20px rgba(0, 0, 0, 0.5)', borderRadius: '10px' }}>
        <div style={{ height: '80px', display: 'flex', justifyContent: 'center', padding: '10px' }}>
          <h2>Add a Photo</h2>
        </div>

        <div style={{ marginTop: '2%', width: "40vw", display: 'flex', justifyContent: 'center', marginLeft: '50px', backgroundColor: '#1a237e' }}>
          <div className="mb-3 row m-5" style={{ color: 'white' }}>
            <label className="col-sm-2 col-form-label">photo</label>
            <div className="col-sm-10">
              <input type="file" className="form-control" onChange={handleFileChange} />
            </div>
          </div>
          <div className="d-flex justify-content-center align-items-center" style={{ marginTop: '20px' }}>
            <button className="btn btn-primary py-2 m-3" onClick={handleUpload}>Add</button>
          </div>
        </div>

        <div style={{ margin: '20px' }}>
          <img src='../Image/voiture.PNG' alt="p1 msg" style={{ width: '200px' }} />
        </div>
      </div>
    </div>
  );
}
