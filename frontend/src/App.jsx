import { BrowserRouter as Router, Routes, Route, Link, Navigate } from "react-router-dom";
import { useState, useEffect } from "react";
import HomePage from "./pages/HomePage";
import StatsPage from "./pages/StatsPage";
import LoginPage from "./pages/LoginPage";
import SignUpPage from "./pages/SignUpPage"; // <-- ADD THIS

export default function App() {
  const [isAuthenticated, setIsAuthenticated] = useState(false);

  useEffect(() => {
    const token = localStorage.getItem("token");
    if (token) {
      setIsAuthenticated(true);
    }
  }, []);

  function handleLoginSuccess() {
    setIsAuthenticated(true);
  }

  function handleLogout() {
    localStorage.removeItem("token");
    setIsAuthenticated(false);
  }

  return (
    <Router>
      <nav style={{ backgroundColor: "#333", padding: "10px" }}>
        {isAuthenticated ? (
          <>
            <Link to="/" style={{ color: "white", marginRight: "10px" }}>Home</Link>
            <Link to="/stats" style={{ color: "white", marginRight: "10px" }}>Stats</Link>
            <span
              onClick={handleLogout}
              style={{
                color: "white",
                marginRight: "10px",
                cursor: "pointer",
                textDecoration: "none"
              }}
            >
              Logout
            </span>
          </>
        ) : (
          <>
            <Link to="/login" style={{ color: "white", marginRight: "10px" }}>Login</Link>
            <Link to="/signup" style={{ color: "white" }}>Sign Up</Link>
          </>
        )}
      </nav>


      <Routes>
        <Route path="/" element={isAuthenticated ? <HomePage /> : <Navigate to="/login" />} />
        <Route path="/stats" element={isAuthenticated ? <StatsPage /> : <Navigate to="/login" />} />
        <Route path="/login" element={<LoginPage onLoginSuccess={handleLoginSuccess} />} />
        <Route path="/signup" element={<SignUpPage onSignUpSuccess={handleLoginSuccess} />} /> {/* ADD THIS */}
      </Routes>
    </Router>
  );
}
