document.getElementById("loginForm").addEventListener("submit", function(e){
    e.preventDefault();
    const empId=document.getElementById("empId").value.trim();
    const password=document.getElementById("password").value.trim();
    const error=document.getElementById("error");


    if(empId==="" || password===""){
        error.textContent="Please enter both employee Id and password.";
        return;
    }

    //Simulated backend check(Replace with actual API call)
    fetch("http://localhost:8080/api/login", {
        method:"POST",
        headers:{"Content-Type":"application/json"},
        body:JSON.stringify({empId,password})
    })

    .then(res => res.json())
    .then(data =>{
        console.log("Login response data:", data);
        // localStorage.setItem("empId",data.empId);
// if employee logged in then different page and  admin  logs in then different page.
        if(data.success){
            if(data.role==="Admin"){
                localStorage.setItem("adminId",data.empId);
                window.location.href="/adminDashboard.html"
            }
            else{
            //add code to check role 
            localStorage.setItem("empId",data.empId);
            window.location.href="/empDashboard.html";
            }
        }
        else{ error.textContent=data.message || "Invalid Login Credentials."};
    })
    .catch(()=>{
        error.textContent="Server error. Please try again later."
    });
});