import React, { useState, useEffect } from "react";
import { useNavigate } from "react-router-dom";
import CustomerDataService from "../services/CustomerDataService";
import "../components/login-register-forms-style.css";

// This is the Login page for the user
function CustomerLogin() {

  // cuName = customerUserName ; cuPassword = customerUserPassword
  // allCustomers contains the data of all customers from the server
  const navigate = useNavigate();
  const [cuName, setCustomerUserName] = useState("");
  const [cuPassword, setCustomerUserPassword] = useState("");
  const [allCustomers, setAllCustomers] = useState([]);

  // Using the following service to fetch all customers data from the server
  useEffect(() => {
    CustomerDataService.getAllCustomers()
      .then((res) => {
        setAllCustomers(res.data);
      })
      .catch((err) => {
        console.log("Error");
      });
  }, []);

  // Handler for submitting the login form
  const submitLoginHandler = (event) => {
    // Check if login data is valid, if it is - navigate the user to its profile page.
    // Otherwise, alert for wrong details.
    const customerLoginCheck = allCustomers.find(
      (customer) =>
        customer.customerUserName === cuName &&
        customer.customerUserPassword === cuPassword
    );
    event.preventDefault();
    if (customerLoginCheck) {
      navigate("/profile/" + cuName);
    } else {
      alert("Wrong details. Try again");
    }
  };

  
  return (
    // Simple form with Login username, password and Login button
    <div div className="wrapper">
      <form onSubmit={submitLoginHandler}>
        <h1>Please Login</h1>
        <br />
        <div>
          <label>User Name:</label>
          <input
            className="form-control"
            type="text"
            value={cuName}
            onChange={(e) => setCustomerUserName(e.target.value)}
          />
        </div>
        <br />
        <div>
          <label>User Password:</label>
          <input
            className="form-control"
            type="password"
            value={cuPassword}
            onChange={(e) => setCustomerUserPassword(e.target.value)}
          />
        </div>
        <div className="button-container">
          <button className="action-button" type="submit">
            Login
          </button>
        </div>
      </form>
    </div>
  );
}

export default CustomerLogin;
