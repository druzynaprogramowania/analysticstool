import React, { Component } from 'react'
import Item from '../components/Item/Item';
import CreateItemButton from '../components/Item/CreateItemButton';
import { connect } from 'react-redux';
import { getItems } from '../actions/itemActions';
import PropType from 'prop-types';
import Pagination from '../components/Pagination';

class Dashboard extends Component {   
  constructor(){
    super()
    // an example array of 150 items to be paged
    var exampleItems = [...Array(150).keys()].map(i => ({ id: (i+1), name: 'Item ' + (i+1) }));


    this.state = {
      search: "",
      exampleItems: exampleItems,
      pageOfItems: []
    }
    this.updateSearch = this.updateSearch.bind(this);
     // bind function in constructor instead of render (https://github.com/yannickcr/eslint-plugin-react/blob/master/docs/rules/jsx-no-bind.md)
     this.onChangePage = this.onChangePage.bind(this);
  }
    componentDidMount() {
    this.props.getItems();
    }

    updateSearch(e) {
      this.setState ({
        // search: e.target.value.substr(0,20)
        search: e.target.value
      });
    }

    onChangePage(pageOfItems) {
      // update state with new page of items
      this.setState({ pageOfItems: pageOfItems });
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
                      {/* <th>id</th> */}
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
                        // return item.itemName.toLocaleLowerCase().indexOf(this.state.search) !== -1;
                        return item.itemName.toLocaleLowerCase().indexOf(this.state.search.toLocaleLowerCase()) !== -1;
                      })
                      .map(item => (
                        <Item key={item.id} item={item} />
                    ))}
                    
                    </tbody>
              </table>
              {/* <div className="text-center">
                        {this.state.pageOfItems.map(item =>
                            <div key={item.id}>{item.name}</div>
                        )}
                        <Pagination items={this.state.exampleItems} onChangePage={this.onChangePage} />
                    </div> */}
              </div>
            </div>
         );
    }
}
// debugger
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