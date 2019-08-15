import React, { Component } from 'react'
import Item from '../components/Item/Item';
import CreateItemButton from '../components/Item/CreateItemButton';
import { connect } from 'react-redux';
import { getItems } from '../actions/itemActions';
import PropType from 'prop-types';

class Dashboard extends Component {   
  componentDidMount() {
    this.props.getItems();
    }
  constructor(){
    super()

    this.state = {
      search: "",
      allItems: [],
      currentItems: [],
      currentPage: null,
      totalPages: null
    }
    this.updateSearch = this.updateSearch.bind(this);

  }

    updateSearch(e) {
      this.setState ({
        search: e.target.value,
      });
    }

    

    render() { 
      const { items } = this.props.item;

        return ( 
            <div className="items">
              <div className="container">
              <br />
                <div className="row">
                  <div className="col text-center align-middle">
                    <CreateItemButton />
                    <input className="ml-5" type="text" id="filter" placeholder="Searching by name"
                      value={this.state.search}
                      onChange={this.updateSearch}
                    />
                  </div>
              </div>
              <br />
              <table className="table table-striped">
                  <thead>
                    <tr className="rows">
                      <th className="align-middle">Identifier</th>
                      <th className="align-middle">Item Name</th>                   
                      <th className="align-middle">Type of Item</th>
                      <th className="align-middle">Quantity</th>
                      <th className="align-middle">Description</th>
                      <th className="align-middle">Start Date</th>
                      <th className="align-middle">End Date</th>
                      <th className="align-middle">Delete</th>
                      <th className="align-middle">Update</th>
                      </tr>
                  </thead>
                  <tbody>
                    {items
                      .filter(item => {
                        return item.itemName.toLocaleLowerCase().indexOf(this.state.search.toLocaleLowerCase()) !== -1;
                      })
                      .map(item => (
                        <Item key={item.id} item={item} />
                    ))}
                    </tbody>
              </table>
              </div>
            </div>
         );
    }
}

Dashboard.propType = {
    item: PropType.object.isRequired,
    getItems: PropType.func.isRequired
};

const mapStateToProps = state => ({
    item: state.item
});

 
export default connect(
    mapStateToProps,
    { getItems }
) (Dashboard);