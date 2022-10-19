import "./App.css";
import { Link, Route, Routes } from "react-router-dom";
import "bootstrap/dist/css/bootstrap.min.css";
import CustomerRegister from "./pages/CustomerRegister";
import CustomerProfile from "./pages/CustomerProfile";
import Home from "./pages/Home";
import CustomerLogin from "./pages/CustomerLogin";
import Marketplace from "./pages/Marketplace";
import CardPricesDataComponent from "./components/CardPricesDataComponent";
import "./components/nav-style.css";
function App() {
  return (
    // Setting a navbar to navigate easily in the app
    <>
      <nav className="nav">
        <ul>
          <li>
            <Link to="/">Home page</Link>
          </li>
          <li>
            <Link to="/register">Register page</Link>
          </li>
          <li>
            <Link to="/login">Login page</Link>
          </li>
        </ul>
      </nav>

      {/* Creating for each Component/Page a specific route to maintain the app better */}
      <Routes>
        <Route path="/" element={<Home />} />
        <Route
          path="/profile/:customerUserName"
          element={<CustomerProfile />}
        />
        <Route
          path="/marketplace/:customerUserName"
          element={<Marketplace />}
        />
        <Route path="/register" element={<CustomerRegister />} />
        <Route path="/login" element={<CustomerLogin />} />
        <Route
          path="/card-prices/:cardId"
          element={<CardPricesDataComponent />}
        />
      </Routes>
    </>
  );
}

export default App;
