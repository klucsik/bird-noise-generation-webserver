
import './App.css';
import React,{useState} from 'react';
import Intro from       './components/Intro/Intro.jsx'  
import DevicesOverview from './components/DevicesOverview/DevicesOverview.jsx';

function App() {
 
 
  const [ intro, setIntro] = useState(true)

 console.log('intro=',intro);
  

  return (
    <div className="App">
      <header className="App-header">
          { intro 
            ? <Intro setIntro={setIntro} />
            : <DevicesOverview  />
          }
      </header>
    </div>
  );
}

export default App;
