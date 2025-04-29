// src/pages/LoginPage.jsx

import { useState } from "react";
import axios from "axios";
import { useNavigate } from "react-router-dom";

export default function LoginPage({ onLoginSuccess }) {
  const [email, setEmail] = useState("");
  const [password, setPassword] = useState("");
  const [errorMessage, setErrorMessage] = useState("");
  const navigate = useNavigate();

  async function handleSubmit(e) {
    e.preventDefault();
    try {
      const baseUrl = process.env.REACT_APP_API_URL || 'http://localhost:8080';
      const response = await axios.post(`${baseUrl}/api/auth/login`, {
        email,
        password,
      });

      const token = response.data.token;
      localStorage.setItem("token", token);
      onLoginSuccess();
      navigate("/");
    } catch (error) {
      console.error(error);
      setErrorMessage("Login failed. Please check your credentials.");
    }
  }

  return (
    <div style={{ display: "flex", flexDirection: "column", alignItems: "center", marginTop: "100px" }}>
      <h1>Login</h1>
      <form onSubmit={handleSubmit} style={{ display: "flex", flexDirection: "column", width: "300px", alignItems: "center" }}>
        <input
          type="email"
          placeholder="Email"
          value={email}
          onChange={(e) => setEmail(e.target.value)}
          required
          style={{ marginBottom: "10px", padding: "10px" }}
        />
        <input
          type="password"
          placeholder="Password"
          value={password}
          onChange={(e) => setPassword(e.target.value)}
          required
          style={{ marginBottom: "10px", padding: "10px" }}
        />
        <button type="submit" style={{ padding: "10px", backgroundColor: "#4CAF50", color: "white", border: "none" }}>
          Login
        </button>
      </form>
      {errorMessage && <p style={{ color: "red", marginTop: "10px" }}>{errorMessage}</p>}
      <p style={{ fontSize: "16px", position: "fixed", bottom: "25px", padding: "20px", textAlign: "center" }}>
        (Server spins down with inactivity, which can delay requests by 50 seconds or more)
      </p>
    </div>
  );
}
