import React from 'react';
import CohortDetails from './CohortDetails';

function App() {
  return (
    <div style={{ padding: '20px', fontFamily: 'Arial' }}>
      <h1>Cohorts Details</h1>
      
      <CohortDetails 
        cohortName="INTADMDF10 -.NET FSD"
        startedOn="22-Feb-2022"
        status="Scheduled"
        coach="Aathma"
        trainer="Jojo Jose"
      />
      
      <CohortDetails 
        cohortName="ADM21JF014 -Java FSD"
        startedOn="10-Sep-2021"
        status="Ongoing"
        coach="Apoorv"
        trainer="Elisa Smith"
      />
      
      <CohortDetails 
        cohortName="CDBJF21025 -Java FSD"
        startedOn="24-Dec-2021"
        status="Ongoing"
        coach="Aathma"
        trainer="John Doe"
      />
    </div>
  );
}

export default App;