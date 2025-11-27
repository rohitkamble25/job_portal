const BASE_URL = "http://localhost:8080/api";

async function loadJobs() {
    const res = await fetch(`${BASE_URL}/jobs/all`);
    const jobs = await res.json();

    let output = "";
    jobs.forEach(job => {
        output += `
          <div class="job-card">
            <h3>${job.title}</h3>
            <p><b>Company:</b> ${job.company}</p>
            <p><b>Location:</b> ${job.location}</p>
            <p>${job.description}</p>
            <p><b>Salary:</b> ${job.salary}</p>
            <button onclick="apply(${job.id})">Apply</button>
          </div>
          <hr>
        `;
    });

    document.getElementById("jobList").innerHTML = output;
}

async function apply(jobId) {
    const res = await fetch(`${BASE_URL}/applications/apply?jobId=${jobId}&userId=${user.id}`, {
        method: "POST"
    });

    if (res.ok) {
        alert("Applied Successfully!");
    } else {
        alert("You already applied or something went wrong.");
    }
}

function logout() {
    localStorage.removeItem("user");
    window.location.href = "login.html";
}

loadJobs();
