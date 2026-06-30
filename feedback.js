document.getElementById("cancelBtn").addEventListener("click",function (e){
    window.location.href="/empDashboard.html";
})

const  empId= localStorage.getItem("empId");
if (!empId) {
  console.error("Employee ID not found in localStorage. Redirecting to login.");
  window.location.href = "login.html";
}
console.log("empId:", empId);
 
window.onload=function(){
document.getElementById("feedbackform").addEventListener("submit", 
    function (e){
        e.preventDefault(); 
        console.log("form Submitted ");
        const data={
            empId: empId,
            name: document.getElementById("name").value,
            category: document.getElementById("category").value,
            message: document.getElementById("feedback").value,
            department: document.getElementById("department").value
        };
        

        console.log("Sending data")

        fetch("http://localhost:8080/api/feedback",
            {
                method: "POST",
                headers:{"Content-Type":"application/json"},
                body: JSON.stringify(data)
            }
        )
        


        .then(res=>res.text())
        .then(msg=>{
            document.getElementById("responseMsg").innerText=msg;
            document.getElementById("feedbackform").reset();
            alert("Feedback Submitted");
            }).catch(err =>{
                document.getElementById("responseMsg").innerText="Error Submitting Feedback.";
                alert("Feedback not Submitted");
                });
                console.log("data sent");
           
       
    });
};
    
   