import React from 'react';
import '../Stylesheets/mystyle.css'; // Importing the CSS file we will create next

function CalculateScore({ Name, School, Total, goal }) {
  // Calculating the percentage based on the Total and Goal (max marks)
  const scorePercentage = Math.round((Total / goal) * 100);

  return (
    <div className="score-card">
      <h2>Student Score Details</h2>
      <p><strong>Name:</strong> {Name}</p>
      <p><strong>School:</strong> {School}</p>
      <p><strong>Total Marks:</strong> {Total}</p>
      <p><strong>Goal (Max Marks):</strong> {goal}</p>
      <hr />
      <h3>Average Score: {scorePercentage}%</h3>
    </div>
  );
}

export default CalculateScore;