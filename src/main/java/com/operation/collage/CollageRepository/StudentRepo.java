package com.operation.collage.CollageRepository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.operation.collage.CollageEntity.StudentEntity;

import java.util.List;

@Repository 
public interface StudentRepo extends JpaRepository<StudentEntity,Long>{
    StudentEntity findByAdhar(String adhar_number);

    @Query(value = """
     select s.addmission_number,
            s.student_name,
            s.course_name,
            s.year,
            f.pending_fees 
    from Student_Details s 
    inner join Fees_Details f 
    on s.addmission_number=f.addmission_no
    where s.addmission_number= :addmission
     """,nativeQuery = true)
    List<Object[]> findStudentDetails(@Param("addmission") Long addmission);


    @Query(value = """
     select * from Student_Details
     where course_name=:course  
     and year=:year
""",nativeQuery = true)
    List<Object[]> getAllStudent(@Param("course")String course,@Param("year")String year);
}
