export default function ApplicationItem({ app, onEdit, onDelete }) {
  return (
    <li className="application-card">
      {/* Top section: Company + Role + Status */}
      <div style={{ marginBottom: "10px" }}>
        <strong style={{ fontSize: "20px" }}>{app.companyName}</strong> — {app.role}
        <div style={{ color: "#4d4d4d", fontSize: "14px", marginTop: "5px" }}>
          Status: {app.status}
        </div>
      </div>

      {/* Bottom section: Details */}
      <div style={{ color: "#4d4d4d", fontSize: "14px", marginBottom: "10px" }}>
        <div>Date Applied: {app.dateApplied}</div>
        <div>Location: {app.location}</div>
        <div>Salary Expectation: ${app.salaryExpectation}</div>
        <div>Notes: {app.notes}</div>
      </div>

      {/* Action buttons */}
      <div className="buttons" style={{ marginTop: "10px", display: "flex", gap: "10px" }}>
        <button onClick={() => onEdit(app)}>Edit</button>
        <button onClick={() => onDelete(app.id)}>Delete</button>
      </div>
    </li>
  );
}
