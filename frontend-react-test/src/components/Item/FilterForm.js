import React from 'react';

const FilterForm = (props) => {

   const updateList = props.items.filter(item => item.updateList)

        return ( 
            <>
            <label className="ml-5" htmlFor="filter">
                Filter by item
            </label>
            <input className="ml-2" type="text" id="filter" placeholder="Search"
                value={this.updateList}
                onChange={this.handleChange}
            />
            </>
         );
    }

 
export default FilterForm;