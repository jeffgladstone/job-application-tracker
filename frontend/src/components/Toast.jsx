import { useEffect } from "react";

export default function Toast({ message, onClose }) {
  useEffect(() => {
    const timer = setTimeout(() => {
      onClose();
    }, 3000); // 3 seconds

    return () => clearTimeout(timer);
  }, [onClose]);

  if (!message) return null;

  return (
    <div style={{
      position: "fixed",
      top: "20px",
      right: "20px",
      backgroundColor: "#007bff",
      color: "white",
      padding: "12px 20px",
      borderRadius: "8px",
      boxShadow: "0 2px 6px rgba(0,0,0,0.2)",
      zIndex: 1000
    }}>
      {message}
    </div>
  );
}
