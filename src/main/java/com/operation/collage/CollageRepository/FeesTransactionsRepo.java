package com.operation.collage.CollageRepository;

import com.operation.collage.CollageEntity.FeesEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface FeesTransactionsRepo extends JpaRepository<FeesEntity,Long> {
    @Query(value = """
   select s.student_name,
          s.course_name,
          s.year, 
          f.total_fees,
          f.deposite_amt,
          f.pending_amt 
  from student_details s 
  inner join fees_transaction f 
  on s.addmission_number=f.addmission_number
  where s.addmission_number=:addmission
 """,nativeQuery = true
    )
    List<Object[]> getTransaction(@Param("addmission")Long addmission);
}
