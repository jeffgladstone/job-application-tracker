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
      <input name="companyName" placeholder="Company Name" value={formData.companyName} onChange={handleChange} required />
      <input name="role" placeholder="Role" value={formData.role} onChange={handleChange} required />
      <input name="status" placeholder="Status" value={formData.status} onChange={handleChange} required />
      <input name="dateApplied" type="date" value={formData.dateApplied} onChange={handleChange} required />
      <input name="location" placeholder="Location" value={formData.location} onChange={handleChange} />
      <input name="salaryExpectation" type="number" placeholder="Salary Expectation" value={formData.salaryExpectation} onChange={handleChange} />
      <textarea name="notes" placeholder="Notes" value={formData.notes} onChange={handleChange} />
      <br />
      <button type="submit">{editingApplication ? "Update" : "Create"}</button>
    </form>
  );
}
