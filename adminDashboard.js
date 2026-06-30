const  empId= localStorage.getItem("adminId");
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
fetch(`http://localhost:8080/api/employee/${empId}`)
.then(res =>res.json())
.then(data =>{
    document.getElementById("empId").textContent=data.empId;
    document.getElementById("profileName").textContent=data.name;
    document.getElementById("department").textContent=data.department;
    localStorage.setItem("department",data.department);
    document.getElementById("email").textContent=data.email;
}).catch(err => console.error("Failed to load Employee details",err));

//WIP
//fetch feedbacks according to department
const  department= localStorage.getItem("department");
if (!department) {
  console.error("Depatment not found in localStorage. Redirecting to login.");
  
}
console.log("department:", department);

fetch(`http://localhost:8080/api/feedback/department/${department}`)
.then(res=>res.json())
.then(feedbacks =>{
    const list=document.getElementById("feedbackList");
    feedbacks.forEach(fb=>{
   const li=document.createElement("li");
      //add text area or small card to add remark and move to resolve section
      li.className="feedback-item";
      li.innerHTML=`
      <p>${new Date(fb.submittedAt).toLocaleString()}</p>
      <p><strong>Raised By: </strong>${fb.name || "~~~"}</p>
      <p><strong>Category: </strong>${fb.category}</p>
      <p><strong>Message: </strong>${fb.message}</p>
      <div class="remark-display">${fb.adminRemark ? `<strong>Remark: </strong> ${fb.adminRemark}` : ''}</div>
      <button class="add-comment-btn">Add Comment</button>
        <div class="comment-section" style="display:none; margin-top: 10px;">
          <textarea placeholder="Enter your remark..." rows="2" cols="40"></textarea>
          <button class="submit-remark-btn">Submit</button>
        </div>
        <button class="resolve-btn" style="margin-top: 10px;">Mark as Resolved</button>`;
        //admin comment
        const addBtn = li.querySelector(".add-comment-btn");
      const commentDiv = li.querySelector(".comment-section");
      const submitBtn = li.querySelector(".submit-remark-btn");
      const textarea = li.querySelector("textarea");
      const remarkDisplay = li.querySelector(".remark-display");
      
      addBtn.addEventListener("click", () => {
        commentDiv.style.display = commentDiv.style.display === "none" ? "block" : "none";
      });

      submitBtn.addEventListener("click", () => {
        const remark = textarea.value.trim();
        if (!remark) return alert("Comment cannot be empty");

        fetch(`http://localhost:8080/api/feedback/${fb.id}/remark`, {
          method: "PUT",
          headers: { "Content-Type": "application/json" },
          body: JSON.stringify({ adminRemark: remark })
        })
        .then(res => {
          if (!res.ok) throw new Error("Failed to update remark");
           alert("Remark updated!");
        })
         .then(() => {
                // Show updated remark & hide input
                const remarkDisplay = li.querySelector(".remark-display");
                remarkDisplay.innerHTML = `<strong>Remark:</strong> ${remark}`;
                commentDiv.remove();
                addBtn.remove(); 
            })
        .catch(err => {
          console.error(err);
          alert("Error updating remark");
        });
      });
      //mark as resolved
const resolveBtn = li.querySelector(".resolve-btn");
resolveBtn.addEventListener("click", () => {
  fetch(`http://localhost:8080/api/feedback/${fb.id}/resolve`, {
    method: "PUT"
  })
  .then(response => {
    if (!response.ok) throw new Error("Failed to resolve feedback");
    
    // Remove from list on dashboard
    li.remove();
  })
  .catch(error => {
    console.error("Error resolving feedback:", error);
  });
});

      list.appendChild(li);
    });
   
}).catch(err=> console.error("Failed to load feedback",err));



//feedback
document.getElementById("res-feedbackbutton").addEventListener("click",function(){
   window.location.href="resolvedFeedback.html";
});
//logout
document.getElementById("logoutBtn").addEventListener("click",function(){
   localStorage.removeItem("empId");
   window.location.href="index.html";
});





