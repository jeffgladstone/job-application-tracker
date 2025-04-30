import { useState } from "react";
import axios from "axios";
import { useNavigate } from "react-router-dom";
import "../styles/AuthPage.css";

export default function SignUpPage({ onSignUpSuccess }) {
  const [name, setName] = useState("");
  const [email, setEmail] = useState("");
  const [password, setPassword] = useState("");
  const [errorMessage, setErrorMessage] = useState("");
  const [loading, setLoading] = useState(false);
  const navigate = useNavigate();

  async function handleSubmit(e) {
    e.preventDefault();
    setLoading(true);
    setErrorMessage("");
    try {
      const baseUrl = process.env.REACT_APP_API_URL || 'http://localhost:8080';
      const response = await axios.post(`${baseUrl}/auth/signup`, {
        name,
        email,
        password,
      });

      const token = response.data.token;
      localStorage.setItem("token", token);
      onSignUpSuccess();
      navigate("/");
    } catch (error) {
      console.error(error);
      setErrorMessage("Signup failed. Please try again.");
    } finally {
      setLoading(false);
    }
  }

  const isProduction = process.env.NODE_ENV === "production";

  return (
    <div className="auth-page">
      {loading && (
        <div className="spinner-overlay">
          <div className="spinner" />
        </div>
      )}

      <h1>Sign Up</h1>
      <form onSubmit={handleSubmit} className="auth-form">
        <input
          type="text"
          placeholder="Name"
          value={name}
          onChange={(e) => setName(e.target.value)}
          required
          disabled={loading}
          className="auth-input"
        />
        <input
          type="email"
          placeholder="Email"
          value={email}
          onChange={(e) => setEmail(e.target.value)}
          required
          disabled={loading}
          className="auth-input"
        />
        <input
          type="password"
          placeholder="Password"
          value={password}
          onChange={(e) => setPassword(e.target.value)}
          required
          disabled={loading}
          className="auth-input"
        />
        <button
          type="submit"
          disabled={loading}
          className="auth-button"
        >
          {loading ? "Signing up..." : "Sign Up"}
        </button>
      </form>

      {errorMessage && <p className="auth-error">{errorMessage}</p>}

      {isProduction && (
        <footer className="auth-footer">
          Server spins down with inactivity, which can delay requests by 50 seconds or more.
        </footer>
      )}
    </div>
  );
}
