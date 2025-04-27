export default function ApplicationItem({ app, onEdit, onDelete }) {
  return (
    <li className="application-card">
      <strong>{app.companyName}</strong> - {app.role}
      <div className="buttons">
        <button onClick={() => onEdit(app)}>Edit</button>
        <button onClick={() => onDelete(app.id)}>Delete</button>
      </div>
    </li>
  );
}
