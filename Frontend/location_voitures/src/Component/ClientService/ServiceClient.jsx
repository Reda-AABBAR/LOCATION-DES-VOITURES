import axios from 'axios';
import React, { useEffect, useState } from 'react';
import { Form, Container, Row, Col, Button } from 'react-bootstrap';
import { useParams } from 'react-router-dom';

export default function ServiceClient() {

    const { email } = useParams();
    const [questions, setQuestions] = useState([]);
    const [response, setResponse] = useState({
        reponse: ''
        
    });

    const [clientService, setClientService] = useState({
        nom: "",
        prenom: "",
        cIN: "",
        dateNaissance: "",
        adresse: "",
        email: "",
        numTele: "",
        password: "",
        agenceRequest: {
            nom: 'z',
            ville: 'settat',
            adresse: 'xx-xx-settat',
        }
      })
  
      async function getClientService() {
        let response = await axios.get(`http://localhost:8080/service-clients/email/${email}`)
       
        const { nom, prenom, cIN, dateNaissance, adresse, numTele, password , agenceResponse} = response.data;
        const updatedClientService = {
          nom,
          prenom,
          cIN,
          dateNaissance,
          adresse,
          numTele,
          password,
          agenceResponse
        };
        setResponse({
            ...response,
            serviceClientRequest :{
                nom : updatedClientService.nom,
                prenom : updatedClientService.prenom,
                cIN : updatedClientService.cIN,
                dateNaissance : updatedClientService.dateNaissance,
                adresse : updatedClientService.adresse,
                numTele : updatedClientService.numTele,
                password : updatedClientService.password,
                agenceRequest : updatedClientService.agenceResponse
            }
        });
        console.log(response);
  
      
      }
  
      
      useEffect(() => {
        getClientService();
      }, []);
  
  

    async function getQuestions() {
        try {
            const response = await axios.get('http://localhost:8080/questions');
            setQuestions(response.data);
            console.log(response.data);
        } catch (error) {
            console.error('Error fetching questions:', error);
        }
    }

    useEffect(() => {
        getQuestions();
    }, []);

    const handleChange = (e, id) => {
        setResponse({
            ...response,
            reponse: e.target.value,
            questionId: id
        });
    };

    async function handleSubmit() {
        try {
            let resp = await axios.post('http://localhost:8080/reponders', response);
            console.log('Response from server:', JSON.stringify(response, null, 2));
            console.log('Question envoyée :', response);
        } catch (error) {
            console.error('Error submitting response:', error);
        }
    }
    
    async function submitResponse(e){
        e.preventDefault();
        await handleSubmit();
        //setQuestion({ ...question, question: ''});
    }

    return (
        <Container style={{backgroundColor : '#1a237e'}}>
            <Row>
                <Col>
                    <h1 style={{color : '#8B94A3'}}>Réponses aux questions</h1>
                    {questions.map((question) => (
                        <div key={question.id}>
                            <h5 style={{color : '#fff'}}>{question.question}</h5>
                            <Form>
                                <Form.Group controlId={`formQuestion-${question.id}`}>
                                    <Form.Control
                                        as="textarea"
                                        rows={5}
                                        placeholder="Réponse ..."
                                        onChange={(e) => handleChange(e, question.id)}
                                        style={{ width: '60%', margin: 'auto' }}
                                    />
                                </Form.Group>
                                <Button variant="primary" onClick={submitResponse}>
                                    Envoyer
                                </Button>
                            </Form>
                        </div>
                    ))}
                </Col>
            </Row>
        </Container>
    );
}
