import React from "react";
import "../components/login-register-forms-style.css";

// Simple homepage with a description about the app
function Home() {
  return (
    <div>
      <h1>This is Home page</h1>
      <br />
      <div className="general-data-container">
        <p>Hello and welcome to my first web application</p>
        <p>
          This app helps Yugi-Oh! TCG (Trading Card Game) players to keep track
          of their card prices. Also, the app attempt to predict the Yugioh
          cards prices by modeling that I've created as well as analyzing data.{" "}
        </p>
        <p>
          Try to create your own user, and add cards from the marketplace which
          represent the Top-50 Cards that sold in USA last year.
        </p>
      </div>
    </div>
  );
}

export default Home;
