import React, { Component } from 'react';
import {Pie, Line, Bar} from 'react-chartjs-2';

class Chart extends Component {
    constructor(props) {
        super(props);
        this.state = { 
            chartData: {
                labels: ['itemName', 'quantity', 'typeOfItem'],
                datasets: [
                    {
                        label: 'Population',
                        data: [
                            10000,
                            20000,
                            15000
                        ],
                        backgroundColor: [
                            'red',    // color for data at index 0
                            'blue',   // color for data at index 1
                            'green',  // color for data at index 2
                        ]
                    }
                ]
            }
         }
    }
    render() { 
        return ( 
            <div className="container">
                <p>
                    Bar chart
                </p>
            <div className="chart">
                <Bar 
                    data={this.state.chartData}
                    // width={}
                    // height={}
                    options={{
                        maintainAspectRatio: false
                    }} 

                    />

            </div>
            </div>
         );
    }
}
 
export default Chart;