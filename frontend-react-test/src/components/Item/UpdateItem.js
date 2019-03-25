import React, { Component } from 'react'
import { getItem, createItem } from '../../actions/itemActions';
import PropTypes from 'prop-types';
import { connect } from 'react-redux';
import classnames from 'classnames';

class UpdateItem extends Component {
    constructor() {
        super();
        this.state = { 
            id: "",
            itemName: "",
            itemIdentifier: "",
            typeOfItem: "",
            quantity: "",
            description: "",
            start_date: "",
            end_date: "",
            errors: {}
         };

         this.onChange = this.onChange.bind(this);
         this.onSubmit = this.onSubmit.bind(this); 
    }

    componentWillReceiveProps(nextProps) {
        if (nextProps.errors) {
          this.setState({ errors: nextProps.errors });
        }
        const {
            id,
            itemName,
            itemIdentifier,
            typeOfItem,
            quantity,
            description,
            start_date,
            end_date
        } = nextProps.item;

        this.setState ({
            id,
            itemName,
            itemIdentifier,
            typeOfItem,
            quantity,
            description,
            start_date,
            end_date
        })
    }

    componentDidMount() {
        const { id } = this.props.match.params;
        this.props.getItem(id, this.props.history);
      }
    
      onChange(e) {
        this.setState({ [e.target.name]: e.target.value });
      }
    
      onSubmit(e) {
        e.preventDefault();
        const updateItem = {
            id: this.state.id,
            itemName: this.state.itemName,
            itemIdentifier: this.state.itemIdentifier,
            typeOfItem: this.state.typeOfItem,
            quantity: this.state.quantity,
            description: this.state.description,
            start_date: this.state.start_date,
            end_date: this.state.end_date
        };
        this.props.createItem(updateItem, this.props.history);
    }

    render() { 
        const { errors } = this.state;
        return ( 
            <div className="item">
                <div className="container">
                    <div className="row">
                        <div className="col-md-8 m-auto">
                        <h5 className="display-4 text-center">Update Item form</h5>
                        <hr />
                        <form onSubmit={this.onSubmit}>
                            <div className="form-group">
                            <input
                                type="text"
                                className={classnames("form-control form-control-lg", {
                                "is-invalid": errors.itemName
                                })}
                                placeholder="Item Name"
                                name="itemName"
                                value={this.state.itemName}
                                onChange={this.onChange}
                            />
                            {errors.itemName && (
                                <div className="invalid-feedback">{errors.itemName}</div>
                            )}
                            </div>
                            <div className="form-group">
                            <input
                                type="text"
                                className="form-control form-control-lg"
                                placeholder="Unique Item ID"
                                name="itemIdentifier"
                                value={this.state.itemIdentifier}
                                onChange={this.onChange}
                                disabled
                            />
                            </div>
                            <div className="form-group">
                                <select
                                    className="form-control form-control-lg"
                                    name="typeOfItem"
                                    value={this.state.typeOfItem}
                                    onChange={this.onChange}
                                >
                                    <option value="">Select Type</option>
                                    <option value="SAMSUNG">SAMSUNG</option>
                                    <option value="APPLE">APPLE</option>
                                    <option value="NOKIA">NOKIA</option>
                                </select>
                                {errors.typeOfItem && (
                                    <div className="invalid-feedback">
                                        {errors.typeOfItem}
                                    </div>
                                )}
                                </div>
                            <div className="form-group">
                                <input
                                    type="text"
                                    className={classnames("form-control form-control-lg", {
                                        "is-invalid": errors.quantity
                                    })}
                                    placeholder="Quantity of item"
                                    name="quantity"
                                    value={this.state.quantity}
                                    onChange={this.onChange}
                                />
                                {errors.quantity && (
                                    <div className="invalid-feedback">
                                        {errors.quantity}
                                    </div>
                                )}
                            </div>
                            <div className="form-group">
                            <textarea
                                className={classnames("form-control form-control-lg", {
                                "is-invalid": errors.description
                                })}
                                placeholder="Item Description"
                                name="description"
                                onChange={this.onChange}
                                value={this.state.description}
                            />
                            {errors.description && (
                                <div className="invalid-feedback">{errors.description}</div>
                            )}
                            </div>
                            <h6>Start Date</h6>
                            <div className="form-group">
                            <input
                                type="date"
                                className="form-control form-control-lg"
                                name="start_date"
                                value={this.state.start_date}
                                onChange={this.onChange}
                            />
                            </div>
                            <h6>Estimated End Date</h6>
                            <div className="form-group">
                            <input
                                type="date"
                                className="form-control form-control-lg"
                                name="end_date"
                                value={this.state.end_date}
                                onChange={this.onChange}
                            />
                            </div>

                            <input
                            type="submit"
                            className="btn btn-primary btn-block mt-4"
                            />
                        </form>
                        </div>
                    </div>
                </div>
            </div>
         );
    }
}

UpdateItem.propTypes = {
    getItem: PropTypes.func.isRequired,
    createItem: PropTypes.func.isRequired,
    item: PropTypes.object.isRequired,
    errors: PropTypes.object.isRequired
};

const mapStateToProps = state => ({
    item: state.item.item,
    errors: state.errors
})
 
export default connect(
    mapStateToProps,
    { getItem, createItem}
)(UpdateItem);