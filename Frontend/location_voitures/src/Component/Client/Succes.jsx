import React from 'react';

export default function Succes() {
  return (
    <div className="container mt-5">
      <div className="alert alert-success" role="alert">
        <h4 className="alert-heading">Réservation réussie !</h4>
        <p>Votre réservation a été effectuée avec succès. Merci de votre confiance.</p>
        <hr />
        <p className="mb-0">Pour plus d'informations, veuillez consulter votre historique de réservations.</p>
      </div>
    </div>
  );
}
