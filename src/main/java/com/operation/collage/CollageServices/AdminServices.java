package com.operation.collage.CollageServices;



import com.operation.collage.CollageEntity.FeesEntity;
import com.operation.collage.CollageRepository.FeesTransactionsRepo;
import com.operation.collage.DTO.FeesDeposite;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.operation.collage.CollageEntity.FeesDetails;
import com.operation.collage.CollageEntity.StudentEntity;
import com.operation.collage.CollageRepository.FeesDetailsRepo;
import com.operation.collage.CollageRepository.StudentRepo;

import jakarta.transaction.Transactional;

import java.util.List;
import java.util.Locale;
import java.util.Optional;

@Service
public class AdminServices {

    private final StudentRepo studentRepo;
    private final FeesDetailsRepo feesDetailsRepo;
    private final FeesTransactionsRepo feesTransactionsRepo;
    public AdminServices(StudentRepo studentRepo, FeesDetailsRepo feesDetailsRepo, FeesTransactionsRepo feesTransactionsRepo) {
        this.studentRepo=studentRepo;
        this.feesDetailsRepo=feesDetailsRepo;
        this.feesTransactionsRepo=feesTransactionsRepo;
    }

    //Addmission
    @Transactional 
    public ResponseEntity<StudentEntity> saveStd(StudentEntity student,FeesDetails fees) {
        StudentEntity ex = studentRepo.findByAdhar(student.getAdhar());
        if (ex != null) {
            throw new RuntimeException("Adhar Number Already Exist !\n");
        }
        student.setYear("1");
        StudentEntity st = studentRepo.save(student);
        if (st.getAddmission_no() == null) {
            throw new RuntimeException("Addmission Number is Null !\n");
        }
        fees.setAddmission_no(st.getAddmission_no());
        fees.setCourse(st.getCname());
        String temp = st.getCname().toLowerCase();
        if (temp.equals("bca") || temp.equals("bba") || temp.equals("ba") || temp.equals("b.com")) {
            fees.setTotal_fees(65000.00);
            fees.setPending_fees(65000.00);
        } else if (temp.equals("mba") || temp.equals("ma") || temp.equals("mca") || temp.equals("m.com")) {
            fees.setTotal_fees(70000.00);
            fees.setPending_fees(70000.00);
        } else {
            throw new RuntimeException("Invalid Course Name\n");
        }
        fees.setYear("1");
        feesDetailsRepo.save(fees);
        return new ResponseEntity<>(st, HttpStatus.CREATED
        );
    }
        //Pay Fees
        @Transactional
        public ResponseEntity<FeesEntity> payamt(Long adm_no,FeesEntity feesEntity, FeesDeposite feesDeposite){
         Optional<FeesDetails> ex=feesDetailsRepo.findById(adm_no);
         if(ex.isEmpty()){
             throw new RuntimeException("Addmission Number Does Not Exist\n");
         }
         double amount=Double.parseDouble(feesDeposite.getAmount().trim());
         if(ex.get().getPending_fees()<amount) {
             throw new RuntimeException("Your amount is greater than your pending fees\n");
         }
         feesEntity.setAddmission_no(ex.get().getAddmission_no());
         feesEntity.setDeposite_fees(amount);
         feesEntity.setTotal_fees(ex.get().getPending_fees());
         feesEntity.setAvl_fees(ex.get().getPending_fees()-amount);
         FeesEntity ft=feesTransactionsRepo.save(feesEntity);
         ex.get().setPending_fees(ft.getAvl_fees());
         feesDetailsRepo.save(ex.get());
         return new ResponseEntity<>(ft,HttpStatus.CREATED);
    }

    //Get Detail By Addmission No
    public List<Object[]> studentDetail(Long addmission_no){
        List<Object[]> ex=studentRepo.findStudentDetails(addmission_no);
        if (ex.isEmpty()){
            throw  new RuntimeException("Addmission Number Does Not Exist !\n");
        }
        return ex;
    }

    //Get student's Transactions
    public List<Object[]> getTransactions(Long addmission_no){
        List<Object[]> ex=feesTransactionsRepo.getTransaction(addmission_no);
        if(ex.isEmpty()){
            throw new RuntimeException("Addmission Number Does Not Exist !\n");
        }
        return ex;
    }

    //Get All Students List
    public List<Object[]> getAllStd(String course,String year){
        String temp = course.toLowerCase();
        List<Object[]> ex=studentRepo.getAllStudent(course,year);
        if(ex.isEmpty()){
            throw new RuntimeException("No More Student !\n");
        }
        return ex;
    }
}
