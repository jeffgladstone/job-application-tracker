export default function ApplicationList({ applications, onEdit, onDelete }) {
  return (
    <div>
      <h2>Applications</h2>
      {applications.length === 0 ? (
        <p>No applications found.</p>
      ) : (
        <ul style={{ listStyle: "none", padding: 0 }}>
          {applications.map(app => (
            <li key={app.id} style={{ marginBottom: "10px", borderBottom: "1px solid #ccc", paddingBottom: "10px" }}>
              <strong>{app.companyName}</strong> - {app.role}
              <div>
                <button onClick={() => onEdit(app)}>Edit</button>
                <button onClick={() => onDelete(app.id)} style={{ marginLeft: "10px" }}>Delete</button>
              </div>
            </li>
          ))}
        </ul>
      )}
    </div>
  );
}
