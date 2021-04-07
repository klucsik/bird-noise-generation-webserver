import React ,{useState,useEffect} from 'react'
import BootstrapTable from 'react-bootstrap-table-next';
import "react-bootstrap-table-next/dist/react-bootstrap-table2.min.css";
import LoadingMask from '../LoadingMask/LoadingMask.jsx'


function DevicesOverview() {

/* let dvData =( 
  [
    {
    "id": 1,
    "name": "DeviceToLovePopó",
    "batteryDto": {
    "id": 1,
    "name": "battery1",
    "voltage": 3.7,
    "colorcode": "#008000"
    },
    "status": "Working"
    },
    {
    "id": 2,
    "name": "Device2",
    "batteryDto": {
    "id": 1,
    "name": "battery2",
    "voltage": 3.6,
    "colorcode": "#FF0000"
    },
    "status": "Working"
    },
    {
    "id": 3,
    "name": "Device3",
    "batteryDto": {
    "id": 1,
    "name": "battery2",
    "voltage": null,
    "colorcode": "#FF0000"
    },
    "status": "Not responding"
    }
    ]
  ); */

const [ data,      setData      ]  = useState(  []   ) // [] or [{},{},{}] or  null
const [ loading,   setLoading   ]  = useState( false )
const [ tmp,       setTmp       ]  = useState( false )




let  BACKEND_URL  =  process.env.BACKEND_URL || "http://be-fe-kozos-deploy.klucsik.duckdns.org/api/deviceOverView/page";
useEffect( ()=> {
  setData([])       // hogy törlődjenek az előző keresési eredmények uj load-nál....
  setLoading(true)  // hogy mutassuk a loadingMask-ot
  

  console.log('BACKEND_URL=',BACKEND_URL );

 fetch( BACKEND_URL )
      .then    ( resp     => resp.json() )
      .then    ( adat     => setData    ( adat  ) )
      .catch   ( error    => {setData    ( null  ); console.log('fetch error=',error);} )
      .finally ( respons  => setLoading ( false ) );

 // setShowResults();
  setTmp(false);
},[tmp])







if (data)
   data.map( (item,i )=> item.batteryDto['batLevel']=<span style={{color: item.batteryDto.colorcode }}>{item.batteryDto.voltage}</span>)



const columns = [{
  dataField: 'id',
  text: 'Device ID'
}, {
  dataField: 'name',
  text: 'Name'
}, {
  dataField: 'batteryDto.name',
  text: 'Battery Name'
}, {
  dataField: 'batteryDto.batLevel',
  text: 'Voltage'

}, {
  dataField: 'status',
  text: 'Status'
}];
    return (
        
        <div className="deviceOverView">
            { !data
                 ? <h3>Opps, error fethcing data from: {BACKEND_URL}</h3>
                 : loading
                     ? <LoadingMask />
                     : <BootstrapTable keyField='id' data={ data } columns={ columns }
                     striped
                     hover
                     condensed
                     />
            }
        </div>
    )
}

export default DevicesOverview
