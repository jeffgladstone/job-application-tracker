export default function ApplicationItem({ app, onEdit, onDelete }) {
  return (
    <li className="application-card">
      <div style={{ marginBottom: "10px" }}>
        <strong>{app.companyName}</strong> - {app.role}
      </div>
      <div>Status: {app.status}</div>
      <div>Date Applied: {app.dateApplied}</div>
      <div>Location: {app.location}</div>
      <div>Salary Expectation: ${app.salaryExpectation}</div>
      <div>Notes: {app.notes}</div>

      <div className="buttons" style={{ marginTop: "15px" }}>
        <button onClick={() => onEdit(app)}>Edit</button>
        <button onClick={() => onDelete(app.id)} style={{ marginLeft: "10px" }}>Delete</button>
      </div>
    </li>
  );
}
