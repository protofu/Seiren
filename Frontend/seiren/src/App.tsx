import React from 'react';
import duckdance from './assets/duckdance.gif';
import MainPage from "./pages/MainPage";
import './App.css';

function App() {
  return (
    <div className="App">
      <header className="App-header">
        <img src={duckdance} className="App-logo" alt="logo" />

      </header>
    </div>
  );
}

export default App;
