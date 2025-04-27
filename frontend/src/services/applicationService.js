const API_URL = "/api/applications";

export async function getApplications() {
  const res = await fetch(API_URL);
  return await res.json();
}

export async function createApplication(application) {
  await fetch(API_URL, {
    method: "POST",
    headers: { "Content-Type": "application/json" },
    body: JSON.stringify(application)
  });
}

export async function updateApplication(application) {
  await fetch(`${API_URL}/${application.id}`, {
    method: "PUT",
    headers: { "Content-Type": "application/json" },
    body: JSON.stringify(application)
  });
}

export async function deleteApplication(id) {
  await fetch(`${API_URL}/${id}`, {
    method: "DELETE"
  });
}
