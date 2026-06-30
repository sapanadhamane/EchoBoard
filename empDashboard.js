const  empId= localStorage.getItem("empId");
if (!empId) {
  console.error("Employee ID not found in localStorage. Redirecting to login.");
  window.location.href = "login.html";
}
console.log("empId:", empId);


const profilePhoto=document.getElementById("profilePhoto");
const fileInput=document.getElementById("fileInput");
const status=document.getElementById("status");

profilePhoto.src=`http://localhost:8080/api/photo/${empId}`;




// profilePhoto.addEventListener("click",()=> fileInput.click());
fileInput.addEventListener("change",function(){
    const file=this.files[0];
    if(!file) return;
     const formData=new FormData();
     formData.append("file",file);
     fetch(`http://localhost:8080/api/upload/${empId}`,{
        method:"POST",
        body:formData
     })
     .then(res =>{
        if(!res.ok)throw new Error("Upload failed");
        return res.text();
     })
     .then(msg=>{
        status.textContent="Photo Uploaded!";
        profilePhoto.src=`http://localhost:8080/api/photo/${empId}?t`+new Date().getTime();
     })
     .catch(err=>{
        status.textContent="Error uploading photo!";
        console.error(err);
        
     })
     .finally(()=>{
        fileInput.value="";
     })
});

//details on employee profile
fetch(`http://localhost:8080/api/employee/${empId}`)
.then(res =>res.json())
.then(data =>{
    document.getElementById("empId").textContent=data.empId;
    document.getElementById("profileName").textContent=data.name;
    document.getElementById("department").textContent=data.department;
    document.getElementById("email").textContent=data.email;
}).catch(err => console.error("Failed to load Employee details",err));


//fetch feedbacks
fetch(`http://localhost:8080/api/feedback/${empId}`)
.then(res=>res.json())
.then(data =>{
   data.sort((a, b) => new Date(b.submittedAt) - new Date(a.submittedAt));
   const list=document.getElementById("feedbackList");
   data.forEach(fb=>{
      const li=document.createElement("li");

      li.className="feedback-item";
      li.innerHTML=`
      <p>${new Date(fb.submittedAt).toLocaleString()}</p>
      <p><strong>Category:</strong>${fb.category}</p>
      <p><strong>Message:</strong>${fb.message}</p>
      <p><strong>Admin Remark:</strong>${fb.adminRemark || "N/A"}</p>
      ${fb.resolved ==="resolved"?`<span style="color:green;">&#10004; Resolved</span>`:''}`;
      list.appendChild(li);
      

   });
}).catch(err=> console.error("Failed to load feedback",err));

//feedback
document.getElementById("feedbackbutton").addEventListener("click",function(){
   window.location.href="feedback.html";
});
//logout
document.getElementById("logoutBtn").addEventListener("click",function(){
   localStorage.removeItem("empId");
   window.location.href="index.html";
});
