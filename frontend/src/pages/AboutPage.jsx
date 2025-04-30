export default function AboutPage() {
  return (
    <div style={{
      maxWidth: "800px",
      margin: "40px auto",
      padding: "20px",
      backgroundColor: "#f9f9f9",
      borderRadius: "12px",
      boxShadow: "0 4px 10px rgba(0, 0, 0, 0.1)",
      fontFamily: "'Segoe UI', Tahoma, Geneva, Verdana, sans-serif",
      lineHeight: 1.6
    }}>
      <h1 style={{ fontSize: "2rem", marginBottom: "20px", color: "#333" }}>
        About Job Application Tracker
      </h1>

      <p>
        <strong>Job Application Tracker</strong> is a productivity tool built to help job seekers
        stay organized and in control throughout their job hunt.
      </p>

      <p>
        With features like filtering, editing, and real-time tracking, you can manage all your job applications from one sleek dashboard.
      </p>

      <p>
        This app is built using <strong>React</strong> on the frontend and <strong>Spring Boot</strong> on the backend. It includes
        features like JWT authentication, dynamic stats, and search functionality to enhance your job search experience.
      </p>

      <p>
        Whether you're applying to 3 jobs or 300, this tool helps you track your progress with ease.
      </p>
    </div>
  );
}
