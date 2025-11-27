const BASE_URL = "http://localhost:8080/api";
const recruiter = JSON.parse(localStorage.getItem("user"));

if (!recruiter || recruiter.role !== "RECRUITER") {
    window.location.href = "login.html";
}

document.getElementById("userName").textContent = recruiter.fullName;

async function loadPostedJobs() {
    const res = await fetch(`${BASE_URL}/jobs/recruiter/${recruiter.id}`);
    const jobs = await res.json();

    let html = "";
    jobs.forEach(job => {
        html += `
            <div class="job-card">
                <h3>${job.title}</h3>
                <p><b>Company:</b> ${job.company}</p>
                <p><b>Location:</b> ${job.location}</p>
                <p><b>Salary:</b> ${job.salary}</p>
                <button onclick="viewApplicants(${job.id})">View Applicants</button>
            </div>
            <hr>
        `;
    });

    document.getElementById("jobList").innerHTML = html;
}

function viewApplicants(jobId) {
    window.location.href = `view-applicants.html?jobId=${jobId}`;
}

function logout() {
    localStorage.removeItem("user");
    window.location.href = "login.html";
}

loadPostedJobs();
