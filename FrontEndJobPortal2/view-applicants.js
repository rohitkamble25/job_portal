const BASE_URL = "http://localhost:8080/api";
const recruiter = JSON.parse(localStorage.getItem("user"));
if (!recruiter || recruiter.role !== "RECRUITER") window.location.href = "login.html";

const params = new URLSearchParams(window.location.search);
const jobId = params.get("jobId");

async function loadApplicants() {
    const res = await fetch(`${BASE_URL}/applications/applicants?jobId=${jobId}`);
    const applicants = await res.json();

    let html = "";

    if (applicants.length === 0) {
        html = `<p>No applicants yet.</p>`;
    } else {
        applicants.forEach(app => {
            html += `
                <div class="job-card">
                    <h3>${app.jobSeeker.fullName}</h3>
                    <p><b>Email:</b> ${app.jobSeeker.email}</p>
                    <p><b>Status:</b> ${app.status}</p>
                    <p><b>Applied Date:</b> ${app.apppliedDate}</p>
                </div>
                <hr>
            `;
        });
    }

    document.getElementById("applicantsList").innerHTML = html;
}

function back() {
    window.history.back();
}

function logout() {
    localStorage.removeItem("user");
    window.location.href = "login.html";
}

loadApplicants();
