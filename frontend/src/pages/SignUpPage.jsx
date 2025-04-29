import { useState } from "react";
import axios from "axios";
import { useNavigate } from "react-router-dom";

export default function SignUpPage({ onSignUpSuccess }) {
  const [name, setName] = useState("");
  const [email, setEmail] = useState("");
  const [password, setPassword] = useState("");
  const [errorMessage, setErrorMessage] = useState("");
  const navigate = useNavigate();

  async function handleSubmit(e) {
    e.preventDefault();
    try {
      const response = await axios.post("http://localhost:8080/api/auth/signup", {
        name,
        email,
        password,
      });

      const token = response.data.token; // Assuming backend returns { token: "..." }
      localStorage.setItem("token", token);
      onSignUpSuccess();
      navigate("/");
    } catch (error) {
      console.error(error);
      setErrorMessage("Signup failed. Please try again.");
    }
  }

  return (
    <div style={{ display: "flex", flexDirection: "column", alignItems: "center", marginTop: "100px" }}>
      <h1>Sign Up</h1>
      <form onSubmit={handleSubmit} style={{ display: "flex", flexDirection: "column", width: "300px", alignItems: "center" }}>
        <input
          type="text"
          placeholder="Name"
          value={name}
          onChange={(e) => setName(e.target.value)}
          required
          style={{ marginBottom: "10px", padding: "10px" }}
        />
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
          Sign Up
        </button>
      </form>
      {errorMessage && <p style={{ color: "red", marginTop: "10px" }}>{errorMessage}</p>}
    </div>
  );
}
