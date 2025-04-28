import { useState } from "react";

export default function ApplicationList({ applications, onEdit, onDelete }) {
  const [sortField, setSortField] = useState(null);
  const [sortDirection, setSortDirection] = useState("asc"); // 'asc' or 'desc'
  const [statusFilter, setStatusFilter] = useState("All");

  function handleSort(field) {
    if (sortField === field) {
      setSortDirection(sortDirection === "asc" ? "desc" : "asc");
    } else {
      setSortField(field);
      setSortDirection("asc");
    }
  }

  function handleFilterChange(e) {
    setStatusFilter(e.target.value);
  }

  function renderSortIcon(field) {
    if (sortField !== field) return null;
    return sortDirection === "asc" ? "▲" : "▼";
  }

  // Sort applications
  const sortedApplications = [...applications].sort((a, b) => {
    if (!sortField) return 0;
    const aValue = a[sortField];
    const bValue = b[sortField];
    if (aValue < bValue) return sortDirection === "asc" ? -1 : 1;
    if (aValue > bValue) return sortDirection === "asc" ? 1 : -1;
    return 0;
  });

  // Apply filter
  const filteredApplications = sortedApplications.filter((app) => {
    if (statusFilter === "All") return true;
    return app.status === statusFilter;
  });

  return (
    <div style={{ overflowX: "auto", marginTop: "20px" }}>
      <div style={{ marginBottom: "10px", textAlign: "center" }}>
        <select value={statusFilter} onChange={handleFilterChange} style={{ padding: "8px", borderRadius: "5px" }}>
          <option value="All">All Statuses</option>
          <option value="Applied">Applied</option>
          <option value="Interviewing">Interviewing</option>
          <option value="Offer">Offer</option>
          <option value="Rejected">Rejected</option>
          <option value="Accepted">Accepted</option>
        </select>
      </div>
      <table style={tableStyle}>
        <thead>
          <tr style={theadRowStyle}>
            <th style={thStyle} onClick={() => handleSort("companyName")}>Company {renderSortIcon("companyName")}</th>
            <th style={thStyle} onClick={() => handleSort("role")}>Role {renderSortIcon("role")}</th>
            <th style={thStyle} onClick={() => handleSort("status")}>Status {renderSortIcon("status")}</th>
            <th style={thStyle} onClick={() => handleSort("dateApplied")}>Date Applied {renderSortIcon("dateApplied")}</th>
            <th style={thStyle} onClick={() => handleSort("location")}>Location {renderSortIcon("location")}</th>
            <th style={thStyle} onClick={() => handleSort("salaryExpectation")}>Salary {renderSortIcon("salaryExpectation")}</th>
            <th style={thStyle}>Notes</th>
            <th style={thStyle}>Actions</th>
          </tr>
        </thead>
        <tbody>
          {filteredApplications.map((app, index) => (
            <tr key={app.id} style={getRowStyle(index)}>
              <td style={tdStyle}>{app.companyName}</td>
              <td style={tdStyle}>{app.role}</td>
              <td style={tdStyle}>{app.status}</td>
              <td style={tdStyle}>{app.dateApplied}</td>
              <td style={tdStyle}>{app.location}</td>
              <td style={tdStyle}>${app.salaryExpectation}</td>
              <td style={tdStyle}>{app.notes}</td>
              <td style={tdStyle}>
                <button onClick={() => onEdit(app)} style={editButtonStyle}>Edit</button>
                <button onClick={() => onDelete(app.id)} style={deleteButtonStyle}>Delete</button>
              </td>
            </tr>
          ))}
        </tbody>
      </table>
    </div>
  );
}

// row styling helper
function getRowStyle(index) {
  return {
    backgroundColor: index % 2 === 0 ? "#f5fff5" : "#eaffea",
    textAlign: "center",
    transition: "background-color 0.3s",
  };
}

// Reusable styles
const tableStyle = {
  width: "100%",
  borderCollapse: "separate",
  borderSpacing: "0 8px", // adds spacing between rows
  backgroundColor: "#bbfcbb",
  borderRadius: "8px",
  overflow: "hidden",
};

const thStyle = {
  padding: "12px 8px",
  backgroundColor: "#bf9463",
  color: "white",
  fontWeight: "bold",
  fontSize: "16px",
};

const theadRowStyle = {
  textAlign: "center",
};

const tdStyle = {
  padding: "12px 8px",
  borderBottom: "1px solid #ddd",
};

const editButtonStyle = {
  backgroundColor: "#4CAF50",
  color: "white",
  border: "none",
  padding: "6px 12px",
  marginRight: "5px",
  borderRadius: "20px",
  cursor: "pointer",
  fontSize: "14px",
};

const deleteButtonStyle = {
  backgroundColor: "#e74c3c",
  color: "white",
  border: "none",
  padding: "6px 12px",
  borderRadius: "20px",
  cursor: "pointer",
  fontSize: "14px",
};
