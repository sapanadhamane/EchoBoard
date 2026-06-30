package com.example.feedback.repository;

import com.example.feedback.model.Feedback;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
//import org.springframework.stereotype.Repository;



public interface FeedbackRepository extends JpaRepository <Feedback,Long>{

    // Custome method to fetch feedback fro emp
    List<Feedback> findByEmpId(Long empId);

    //custome method to fetch feedback for admin
    @Query("SELECT f FROM Feedback f WHERE f.department = :department AND (f.resolved IS NULL OR f.resolved <> 'resolved')")
List<Feedback> findUnresolvedByDepartment(@Param("department") String department);
    List<Feedback> findByDepartmentAndResolved(String department, String resolved);
   
    @Modifying
    @Transactional
    @Query("UPDATE Feedback f SET f.adminRemark = :remark WHERE f.id = :id")
    void updateAdminRemark(@Param("id") Long id, @Param("remark") String remark);


    @Modifying
@Query("UPDATE Feedback f SET f.resolved = 'resolved',f.resolvedOn=CURRENT_TIMESTAMP WHERE f.id = :id")
void markAsResolved(@Param("id") Long id);



    
}


