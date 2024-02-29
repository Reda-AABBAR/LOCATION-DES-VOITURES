import React, { useEffect, useState } from 'react'
import { Form, Container, Row, Col } from 'react-bootstrap';
import axios from 'axios';
import { useParams } from 'react-router-dom';



export default function Question() {

    const [question, setQuestion] = useState({
        question : '',
        clientRequest: {
            nom: "hanane",
            prenom: "berami",
            cIN: "WB23651",
            dateNaissance: "2002-03-02",
            adresse: "settat",
            email: "hanane@gmail.com",
            numTele: "07653432132",
            password: "1544"
        }
    });

    const { email } = useParams();
    const [client, setClient] = useState({
        nom: "",
        prenom: "",
        cIN: "",
        dateNaissance: "",
        adresse: "",
        email: "",
        numTele: "",
        password: ""
    });

    useEffect(() => {
        async function getClient() {
            try {
                let response = await axios.get(`http://localhost:8080/clients/email/${email}`);
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
                setQuestion(prevQuestion => ({
                    ...prevQuestion,
                    clientRequest: updatedClient
                }));
            } catch (error) {
                console.error('Error fetching client data:', error);
            }
        }

        getClient();
    }, [email]);

    const handleChange = (e) => {
        setQuestion({ ...question, question: e.target.value });
    };

    async function handleSubmit() {
        try {
            let response = await axios.post('http://localhost:8080/questions', question);
            console.log('Response from server:', JSON.stringify(question, null, 2));
            console.log('Question envoyée :', question);
        } catch (error) {
            console.error('Error submitting question:', error);
        }
    };

    async function submitQuestion(e) {
        e.preventDefault();
        await handleSubmit();
        setQuestion({ ...question, question: '' });
    }
    

  return <>
  <Container style={{backgroundColor: '#1a237e'}}>
            <Row className="mt-5">
                <Col className="mx-auto">
                    <h2 style={{color: 'white'}}>Comment pouvons-nous vous aider ?</h2>
                    <p style={{color: 'white'}}>Écrivez votre question ou votre message ci-dessous :</p>
                    <Form>
                        <Form.Group controlId="formQuestion">
                            <Form.Control
                                as="textarea"
                                rows={5}
                                placeholder="Écrivez votre question ici..."
                                value={question.question}
                                onChange={handleChange}
                                style={{ width: '60%', margin: 'auto'  }} 
                            />
                        </Form.Group>
                        <button variant="primary" onClick={submitQuestion}>
                        Envoyer
                        </button>
                    </Form>
                </Col>
            </Row>
        </Container>
  </>
}

