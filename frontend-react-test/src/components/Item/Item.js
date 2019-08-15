import React, { Component } from 'react';
import { Link } from 'react-router-dom';
import PropTypes from 'prop-types';
import { connect } from 'react-redux';
import { deleteItem } from '../../actions/itemActions';

class Item extends Component {
    
    onDeleteClick = (id) => {
        this.props.deleteItem(id);
    }

    render() { 
        const { item } = this.props;
        return ( 
            <>
            <tr className="rows">
                <td>
                    {item.itemIdentifier}
                </td>
                <td>                 
                    {item.itemName}
                </td>
                <td>
                    {item.typeOfItem}
                </td>
                <td>
                    {item.quantity}
                </td>
                <td> 
                    {item.description}
                </td>
                <td> 
                    {item.start_date}
                </td>
                <td> 
                    {item.end_date}
                </td>
                <td>
                    <li
                        className="btn btn-danger"
                        onClick={this.onDeleteClick.bind(
                            this,
                            item.itemIdentifier
                        )}
                        >
                        Delete
                    </li>
                </td>
                <td>
                    <Link to={`/updateItem/${item.itemIdentifier}`}>
                            <li className="btn btn-warning">                  
                                    Update
                            </li>
                    </Link>
                </td>
            </tr>
           </> 
         );
    }
}

Item.propTypes = {
    deleteItem: PropTypes.func.isRequired
}
 
export default connect(
    null,
    { deleteItem }
)(Item);