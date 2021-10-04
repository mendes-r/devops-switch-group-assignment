import React from 'react';
import './addRelationModal.css';

const Trigger = ({ triggerText, buttonRef, showModal }) => {
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
export default Trigger;
