import React from 'react';
import styles from './CohortDetails.module.css'; // Importing the CSS Module

function CohortDetails(props) {
  // Determine color based on status
  const titleColor = props.status.toLowerCase() === 'ongoing' ? 'green' : 'blue';

  return (
    <div className={styles.box}>
      {/* Applying the dynamic inline style */}
      <h3 style={{ color: titleColor }}>
        {props.cohortName}
      </h3>
      <dl>
        <dt>Started On</dt>
        <dd>{props.startedOn}</dd>
        
        <dt>Current Status</dt>
        <dd>{props.status}</dd>
        
        <dt>Coach</dt>
        <dd>{props.coach}</dd>
        
        <dt>Trainer</dt>
        <dd>{props.trainer}</dd>
      </dl>
    </div>
  );
}

export default CohortDetails;