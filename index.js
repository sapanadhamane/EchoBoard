window.onload=()=>{
    fetch("http://localhost:8080/api/feedback")
    .then(res =>res.json()
    .then(data=>{
        
    })
)
}

const toggleButton = document.getElementById('toggleSidebar');
const sidebarMenu = document.getElementById('sidebarMenu');



toggleButton.addEventListener('click', () => {
  sidebarMenu.classList.toggle('hidden');
  
});


document.addEventListener('click', (e) => {
  if (!sidebarMenu.contains(e.target) && e.target !== toggleButton) {
    sidebarMenu.classList.add('hidden');
  }
});
 