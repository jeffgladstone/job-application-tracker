import { useState, useEffect } from "react";
import ApplicationList from "../components/ApplicationList";
import ApplicationForm from "../components/ApplicationForm";
import SearchBar from "../components/SearchBar";
import { getApplications, createApplication, updateApplication, deleteApplication } from "../services/applicationService";

export default function HomePage() {
  const [applications, setApplications] = useState([]);
  const [searchQuery, setSearchQuery] = useState("");
  const [editingApplication, setEditingApplication] = useState(null);

  useEffect(() => {
    loadApplications();
  }, []);

  async function loadApplications() {
    const data = await getApplications();
    setApplications(data);
  }

  async function handleCreate(appData) {
    await createApplication(appData);
    await loadApplications();
  }

  async function handleUpdate(appData) {
    await updateApplication(appData);
    setEditingApplication(null);
    await loadApplications();
  }

  async function handleDelete(id) {
    await deleteApplication(id);
    await loadApplications();
  }

  const filteredApplications = applications.filter(app =>
    app.companyName.toLowerCase().includes(searchQuery.toLowerCase()) ||
    app.role.toLowerCase().includes(searchQuery.toLowerCase())
  );

  return (
    <div style={{ padding: "20px", maxWidth: "800px", margin: "0 auto" }}>
      <h1>Job Application Tracker</h1>
      <SearchBar setSearchQuery={setSearchQuery} />
      <ApplicationForm onSubmit={editingApplication ? handleUpdate : handleCreate} editingApplication={editingApplication} />
      <ApplicationList
        applications={filteredApplications}
        onEdit={setEditingApplication}
        onDelete={handleDelete}
      />
    </div>
  );
}
