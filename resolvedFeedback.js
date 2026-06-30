const  department= localStorage.getItem("department");
if (!department) {
  console.error("Depatment not found in localStorage. Redirecting to login.");
  
}
console.log("department:", department);

fetch(`http://localhost:8080/api/feedback/${department}/resolved`)
  .then(res => res.json())
  .then(data => {
    const resolvedList = document.getElementById("resolvedList");

    const resolvedFeedbacks = data.filter(fb => fb.resolved === "resolved");
    if (resolvedFeedbacks.length === 0) {
      resolvedList.innerHTML = "<li>No resolved feedbacks found.</li>";
    }

    resolvedFeedbacks.forEach(fb => {
      const li = document.createElement("li");
      li.innerHTML = `
        <p><em>Submitted on: ${new Date(fb.submittedAt).toLocaleString()}</em></p>
        <p><strong>Category:</strong> ${fb.category}</p>
        <p><strong>Message:</strong> ${fb.message}</p>
        <p><strong>Remark:</strong> ${fb.adminRemark || "No remark"}<em><strong>  Resolved on: </strong> ${new Date(fb.resolvedOn).toLocaleString()}</em></p>
        
      `;
      resolvedList.appendChild(li);
    });
  })
  .catch(err => {
    console.error("Failed to load resolved feedbacks", err);
  });
