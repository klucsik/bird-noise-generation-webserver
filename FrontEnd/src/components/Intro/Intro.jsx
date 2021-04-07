import React,{useEffect} from 'react' 
import logo from './birdlogo.gif';

function Intro({setIntro}) {

  useEffect( () => {
    if ( typeof(document.querySelector('.App-header')) !== 'undefined')
    document.querySelector('.App-header').display = "flex";
    document.querySelector('.App-header').backgroundColor = "#282c34";
  })


    return (
        <div>
            <img src={logo} className="App-logo" alt="logo" />
        <p>
          Bird noise...
        </p>
        <a className="App-link"
            href='#'      
            rel="noopener noreferrer"
            onClick={ () => (setIntro(false))}
        >
          Enter
        </a>
        </div>
    )
}

export default Intro
