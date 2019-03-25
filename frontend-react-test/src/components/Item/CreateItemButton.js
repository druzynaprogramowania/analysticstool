import React from 'react';
import { Link } from 'react-router-dom';

const CreateItemButton = () => {
    return ( 
        <React.Fragment>
            <Link to="/addItem" className="btn btn-lg btn-info">
                Create a Item
            </Link>
        </React.Fragment>
     );
}
 
export default CreateItemButton;