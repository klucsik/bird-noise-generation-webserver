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

  let  extBACKEND_URL  =  process.env.REACT_APP_BACKEND_URL || window.location.href.replace(/#/g,'');// || "http://be-fe-kozos-deploy.klucsik.duckdns.org";
  console.log('extBACKEND_UR=',extBACKEND_URL.replace(/\/#/g,'').replace(/\/\//g,'') );
  extBACKEND_URL=  typeof(extBACKEND_URL)=== 'undefined' ? extBACKEND_URL.replace(/#/g,'') : extBACKEND_URL
const [ data,      setData      ]  = useState(  []   ) // [] or [{},{},{}] or  null
const [ loading,   setLoading   ]  = useState( false )
//const [ BACKEND_URL,  setBACKEND_URL  ]  = useState( extBACKEND_URL )

if (process.env.NODE_ENV !== 'production') {
  console.log('No production')//, process.env.REACT_APP_BACKEND_URL , process.env.development.REACT_APP_BACKEND_URL );
 // setBACKEND_URL(BACKEND_URL)
}


useEffect( ()=> {
  setData([])       // hogy törlődjenek az előző keresési eredmények uj load-nál....
  setLoading(true)  // hogy mutassuk a loadingMask-ot
  
let fetchURL  =  extBACKEND_URL + '/api/deviceOverView/page';
console.log('fetch from :',fetchURL);
 fetch( fetchURL )
      .then    ( resp     => resp.json() )
      .then    ( adat     => setData    ( adat  ) )
      .catch   ( error    => {setData    ( null  ); console.log('fetch error=',error);} )
      .finally ( respons  => setLoading ( false ) );

}, [] )







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
          {process.env.NODE_ENV } {process.env.REACT_APP_BACKEND_URL} 
            { !data
                 ? <h3>Opps, error fethcing data from: {extBACKEND_URL}</h3>
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
