const BASE_URL = "http://localhost:8080/api";
const recruiter = JSON.parse(localStorage.getItem("user"));

if (!recruiter || recruiter.role !== "RECRUITER") {
    window.location.href = "login.html";
}

document.getElementById("postJobForm").addEventListener("submit", async (e) => {
    e.preventDefault();

    const job = {
        title: document.getElementById("title").value,
        company: document.getElementById("company").value,
        location: document.getElementById("location").value,
        salary: document.getElementById("salary").value,
        description: document.getElementById("description").value,
    };

    const res = await fetch(`${BASE_URL}/jobs/post/${recruiter.id}`, {
        method: "POST",
        headers: { "Content-Type": "application/json" },
        body: JSON.stringify(job),
    });

    if (res.ok) {
        alert("Job Posted Successfully!");
        window.location.href = "recruiter.dashboard.html";
    } else {
        alert("Failed to post job");
    }
});

function back() {
    window.location.href = "recruiter.dashboard.html";
}

function logout() {
    localStorage.removeItem("user");
    window.location.href = "login.html";
}
