import React from 'react';
import './addMemberModal.css';

const TriggerPerson = ({ triggerText, buttonRef, showModal }) => {
    return (
        <button
            className="new-family"
            ref={buttonRef}
            onClick={showModal}
        >
            {triggerText}
        </button>
    );
};
export default TriggerPerson;
