import React, { useState, useEffect } from "react";
import CustomerDataService from "../services/CustomerDataService";
import { useNavigate } from "react-router-dom";
import "../components/login-register-forms-style.css";

// This is the Register page for a new user
function CustomerRegister() {
  // cuName = customerUserName ; cuPassword = customerUserPassword ; cName = customerName
  // allCustomers contains the data of all customers from the server
  const navigate = useNavigate();
  const [cuName, setCustomerUserName] = useState("");
  const [cuPassword, setCustomerUserPassword] = useState("");
  const [cName, setCustomerName] = useState("");
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

  // Handler for submitting the customer register form.
  const submitRegisterHandler = (event) => {
    const customerUserNameCheck = allCustomers.find(
      (customer) => customer.customerUserName === cuName
    );
    event.preventDefault();
    // Basic verfication - if this username already taken (check with database)
    if (!customerUserNameCheck) {
      var dataToServer = {
        customerUserName: cuName,
        customerUserPassword: cuPassword,
        customerName: cName,
        customerCardsInPossessionList: [],
      };
      CustomerDataService.createCustomer(dataToServer)
        .then(() => {
          navigate("/profile/" + cuName);
        })
        .catch((e) => {
          console.log(e);
        });
    } else {
      alert("Username is taken");
    }
  };

  return (
    // Simple form with Login username, password and Register button
    <div className="wrapper">
      <form onSubmit={submitRegisterHandler}>
        <h1>Please Register</h1>
        <br />
        <div className="form-container">
          <label>User name:</label>
          <input
            className="form-control"
            type="text"
            name="customerUserName"
            value={cuName}
            onChange={(e) => setCustomerUserName(e.target.value)}
          />
          <br />
        </div>
        <div>
          <label>Password:</label>
          <input
            className="form-control"
            type="password"
            name="customerUserPassword"
            value={cuPassword}
            onChange={(e) => setCustomerUserPassword(e.target.value)}
          />
          <br />
        </div>
        <div>
          <label>Name:</label>
          <input
            className="form-control"
            type="text"
            name="customerName"
            value={cName}
            onChange={(e) => setCustomerName(e.target.value)}
          />
          <br />
        </div>
        <div className="button-container">
          <button className="action-button" type="submit">
            Register
          </button>
        </div>
      </form>
    </div>
  );
}

export default CustomerRegister;
