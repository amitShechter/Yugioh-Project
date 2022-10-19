import axios from "axios";

// Creating an axios and export him in order to have cleaner, readable code
// Used in all services as http
export default axios.create({
  baseURL: "http://localhost:8080/api",
  headers: {
    "Content-type": "application/json",
  },
});
