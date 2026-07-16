import React from 'react';
import CalculateScore from './Components/CalculateScore';

function App() {
  return (
    <div>
      <h1 style={{ textAlign: 'center', marginTop: '20px' }}>Student Management Portal</h1>
      
      {/* Calling the component and passing data as props */}
      <CalculateScore 
        Name="Aarav Patel" 
        School="Delhi Public School" 
        Total={420} 
        goal={500} 
      />
    </div>
  );
}

export default App;