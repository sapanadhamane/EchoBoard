package com.example.feedback.controller;
 import com.example.feedback.model.Feedback;
 import com.example.feedback.repository.FeedbackRepository;

import java.util.List;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
//import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

//import org.springframework.web.bind.annotation.RequestBody;






 @RestController
 @RequestMapping("/api")
 @CrossOrigin(origins = "*") //Use the frontend domain to get requests (REST api)
public class FeedbackController {

    @Autowired
    private FeedbackRepository repository;


    // @GetMapping("/feedback/{empId}")
    // public List<Feedback>getAllFeedback(){
    //     return repository.findAll();
    // }

    // to get feedbacks for employee
     @GetMapping("/feedback/{empId}")
    public List<Feedback>getFeedbacksByEmpId(@PathVariable Long empId){
        return repository.findByEmpId(empId);
    }

    // to get feedbacks for admin
      @GetMapping("/feedback/department/{department}")
    public List<Feedback>getFeedbacksByDepartment(@PathVariable String department){
        return repository.findUnresolvedByDepartment(department);
    }


    @PostMapping("/feedback")
    public ResponseEntity<String> submitFeedback(@RequestBody Feedback feedback){
        System.out.println("Received: " + feedback.getName() + ", " + feedback.getMessage());
        repository.save(feedback);
        return ResponseEntity.ok("Feedback submitted Successfully!");
    }

   
@PutMapping("/feedback/{id}/remark")
public ResponseEntity<String> updateAdminRemark(@PathVariable Long id, @RequestBody Map<String, String> body) {
    Feedback fb = repository.findById(id).orElse(null);
    if (fb == null) return ResponseEntity.notFound().build();

    fb.setAdminRemark(body.get("adminRemark"));
    repository.save(fb);
    return ResponseEntity.ok("Admin remark updated");
}

@PutMapping("/feedback/{id}/resolve")
@Transactional
public ResponseEntity<?> markAsResolved(@PathVariable Long id) {
    repository.markAsResolved(id);
    return ResponseEntity.ok().build();
}

// Get resolved feedbacks for a specific department 
@GetMapping("/feedback/{department}/resolved")
public List<Feedback> getResolvedFeedbacksByDepartment(@PathVariable String department) {
    return repository.findByDepartmentAndResolved(department, "resolved");
}






    
    
}
