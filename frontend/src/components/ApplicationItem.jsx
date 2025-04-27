export default function ApplicationItem({ app, onEdit, onDelete }) {
  return (
    <li style={{ marginBottom: "10px", borderBottom: "1px solid #ccc", paddingBottom: "10px" }}>
      <strong>{app.companyName}</strong> - {app.role}
      <div>
        <button onClick={() => onEdit(app)}>Edit</button>
        <button onClick={() => onDelete(app.id)} style={{ marginLeft: "10px" }}>Delete</button>
      </div>
    </li>
  );
}
