import ApplicationItem from "./ApplicationItem";

export default function ApplicationList({ applications, onEdit, onDelete }) {
  return (
    <div>
      <h2>Applications</h2>
      {applications.length === 0 ? (
        <p>No applications found.</p>
      ) : (
        <ul style={{ listStyle: "none", padding: 0 }}>
          {applications.map(app => (
            <ApplicationItem
              key={app.id}
              app={app}
              onEdit={onEdit}
              onDelete={onDelete}
            />
          ))}
        </ul>
      )}
    </div>
  );
}
