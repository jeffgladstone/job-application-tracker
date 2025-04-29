// src/services/applicationService.js
import axiosInstance from "./axiosInstance";

export async function getApplications() {
  const res = await axiosInstance.get("/");
  return res.data;
}

export async function createApplication(application) {
  await axiosInstance.post("/", application);
}

export async function updateApplication(application) {
  await axiosInstance.put(`/${application.id}`, application);
}

export async function deleteApplication(id) {
  await axiosInstance.delete(`/${id}`);
}
