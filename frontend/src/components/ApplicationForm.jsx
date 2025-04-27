import { useState, useEffect } from "react";

export default function ApplicationForm({ onSubmit, editingApplication }) {
  const [formData, setFormData] = useState({
    companyName: "",
    role: "",
    status: "",
    dateApplied: "",
    notes: "",
    location: "",
    salaryExpectation: ""
  });

  useEffect(() => {
    if (editingApplication) {
      setFormData(editingApplication);
    } else {
      setFormData({
        companyName: "",
        role: "",
        status: "",
        dateApplied: "",
        notes: "",
        location: "",
        salaryExpectation: ""
      });
    }
  }, [editingApplication]);

  function handleChange(e) {
    const { name, value } = e.target;
    setFormData(prev => ({ ...prev, [name]: value }));
  }

  function handleSubmit(e) {
    e.preventDefault();
    onSubmit(formData);
  }

  return (
    <form onSubmit={handleSubmit} style={{ marginBottom: "20px" }}>
      <h2>{editingApplication ? "Edit Application" : "New Application"}</h2>

      {/* Centering Wrapper */}
      <div style={{ display: "flex", flexDirection: "column", alignItems: "center" }}>
        <input
          name="companyName"
          placeholder="Company Name"
          value={formData.companyName}
          onChange={handleChange}
          required
          style={{ width: "100%", maxWidth: "400px", marginBottom: "10px" }}
        />

        <input
          name="role"
          placeholder="Role"
          value={formData.role}
          onChange={handleChange}
          required
          style={{ width: "100%", maxWidth: "400px", marginBottom: "10px" }}
        />

        <input
          name="status"
          placeholder="Status"
          value={formData.status}
          onChange={handleChange}
          required
          style={{ width: "100%", maxWidth: "400px", marginBottom: "10px" }}
        />

        <input
          name="dateApplied"
          type="date"
          value={formData.dateApplied}
          onChange={handleChange}
          required
          style={{ width: "100%", maxWidth: "400px", marginBottom: "10px" }}
        />

        <input
          name="location"
          placeholder="Location"
          value={formData.location}
          onChange={handleChange}
          style={{ width: "100%", maxWidth: "400px", marginBottom: "10px" }}
        />

        <input
          name="salaryExpectation"
          type="number"
          placeholder="Salary Expectation"
          value={formData.salaryExpectation}
          onChange={handleChange}
          style={{ width: "100%", maxWidth: "400px", marginBottom: "10px" }}
        />

        <textarea
          name="notes"
          placeholder="Notes"
          value={formData.notes}
          onChange={handleChange}
          rows="3"
          style={{ width: "100%", maxWidth: "400px", marginBottom: "10px", resize: "vertical" }}
        />

        <button type="submit" style={{ width: "100%", maxWidth: "400px" }}>
          {editingApplication ? "Update" : "Create"}
        </button>
      </div>
    </form>
  );
}
