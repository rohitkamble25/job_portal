const BASE_URL = "http://localhost:8080/api";
document.getElementById("registerForm")?.addEventListener("submit", async (e) => {
    e.preventDefault();
    const user = {
        fullName: document.getElementById("fullName").value,
        email: document.getElementById("email").value,
        password: document.getElementById("password").value,
        role: document.getElementById("role").value,
    };
    const res = await fetch(`${BASE_URL}/users/register`,{
        method:"POST",
        headers:{"Content-Type":"application/json"},
        body:JSON.stringify(user)
    });
    if(res.ok){
        alert("Registration successful! please login now.");
        window.location.href = "login.html";
    }else{
        alert("Registration Failed.Try Again.");
    }
});

document.getElementById("loginForm")?.addEventListener("submit",async(e)=>{
    e.preventDefault();
    const loginData = {
        email:document.getElementById("email").value,
        password:document.getElementById("password").value,
    };
    const res = await fetch(`${BASE_URL}/users/login`,{
         method:"POST",
        headers:{"Content-Type":"application/json"},
        body:JSON.stringify(loginData)
    });


     if (res.ok) {
        const user = await res.json();
        localStorage.setItem("user", JSON.stringify(user));

        if (user.role === "JOB_SEEKER") {
            window.location.href = "seeker.dashboard.html";
        } else if (user.role === "RECRUITER") {
            window.location.href = "recruiter.dashboard.html";
        }
    } else {
        alert("Invalid email or password");
    }

});