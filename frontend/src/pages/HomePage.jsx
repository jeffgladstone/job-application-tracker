import { useState, useEffect } from "react";
import ApplicationList from "../components/ApplicationList";
import ApplicationForm from "../components/ApplicationForm";
import SearchBar from "../components/SearchBar";
import { getApplications, createApplication, updateApplication, deleteApplication } from "../services/applicationService";
import Toast from "../components/Toast";

export default function HomePage() {
  const [applications, setApplications] = useState([]);
  const [searchQuery, setSearchQuery] = useState("");
  const [editingApplication, setEditingApplication] = useState(null);
  const [toastMessage, setToastMessage] = useState("");

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
    setToastMessage("Application Created!");
  }

  async function handleUpdate(appData) {
    await updateApplication(appData);
    setEditingApplication(null);
    await loadApplications();
    setToastMessage("Application Updated!");
  }

  async function handleDelete(id) {
    await deleteApplication(id);
    await loadApplications();
    setToastMessage("Application Deleted!");
  }

  const filteredApplications = applications.filter(app =>
    app.companyName.toLowerCase().includes(searchQuery.toLowerCase()) ||
    app.role.toLowerCase().includes(searchQuery.toLowerCase())
  );

  return (
    <div className="container">
      <Toast message={toastMessage} onClose={() => setToastMessage("")} />
      <h1>Job Application Tracker</h1>
      <ApplicationForm onSubmit={editingApplication ? handleUpdate : handleCreate} editingApplication={editingApplication} />
      <SearchBar setSearchQuery={setSearchQuery} />
      <ApplicationList
        applications={filteredApplications}
        onEdit={setEditingApplication}
        onDelete={handleDelete}
      />
    </div>
  );
}
