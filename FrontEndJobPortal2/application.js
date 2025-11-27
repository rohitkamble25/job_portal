const BASE_URL = "http://localhost:8080/api";
const loggedUser = JSON.parse(localStorage.getItem("user"));

if (!loggedUser) window.location.href = "login.html";

async function loadMyApplications() {
    const res = await fetch(`${BASE_URL}/applications/user?userId=${loggedUser.id}`);
    const applications = await res.json();

    let html = "";

    if (applications.length === 0) {
        html = `<p>You haven't applied for any jobs yet.</p>`;
    } else {
        applications.forEach(app => {
            html += `
                <div class="job-card">
                    <h3>${app.job.title}</h3>
                    <p><b>Company:</b> ${app.job.company}</p>
                    <p><b>Location:</b> ${app.job.location}</p>
                    <p><b>Salary:</b> ${app.job.salary}</p>
                    <p><b>Status:</b> ${app.status}</p>
                    <p><b>Applied On:</b> ${app.apppliedDate}</p>
                </div>
                <hr>
            `;
        });
    }

    document.getElementById("applicationsList").innerHTML = html;
}

function back() {
    window.location.href = "seeker.dashboard.html";
}

function logout() {
    localStorage.removeItem("user");
    window.location.href = "login.html";
}

loadMyApplications();
