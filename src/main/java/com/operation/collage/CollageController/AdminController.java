package com.operation.collage.CollageController;

import com.operation.collage.CollageEntity.FeesEntity;
import com.operation.collage.DTO.FeesDeposite;
import jakarta.validation.constraints.Pattern;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import com.operation.collage.CollageEntity.FeesDetails;
import com.operation.collage.CollageEntity.StudentEntity;
import com.operation.collage.CollageServices.AdminServices;

import jakarta.validation.Valid;

import java.util.List;

@Validated
@RestController 
@RequestMapping("/admin")
public class AdminController {

    private final AdminServices adminServices;
    public AdminController(AdminServices adminServices) {
        this.adminServices=adminServices;
    }

    @PostMapping("/addmission")
    public ResponseEntity<StudentEntity> addStd(@Valid @RequestBody StudentEntity studentEntity,FeesDetails feesDetails){
        adminServices.saveStd(studentEntity,feesDetails);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @PostMapping("/payfees/{addmission_no}")
    public ResponseEntity<FeesEntity>  payfees(@PathVariable @Pattern(
            message = "Enter Valid Addmisstion Number !",
            regexp ="^[1-9]\\d{0,5}$"
    ) String  addmission_no, @RequestBody FeesDeposite feesDeposite, FeesEntity feesEntity){
        return adminServices.payamt(Long.parseLong(addmission_no),feesEntity,feesDeposite);
    }

    @GetMapping("/student/{addmission_no}")
    public List<Object[]> showStudent(@PathVariable @Pattern(
            message = "Enter valid Addmission Number",
            regexp ="^[1-9]\\d{0,5}$"
    )String addmission_no){
        Long l=Long.parseLong(addmission_no);
            return adminServices.studentDetail(l);
    }

    @GetMapping("/transaction/{addmission_no}")
    public List<Object[]> checkTransaction(@PathVariable @Pattern(
            message = "Enter valid Addmission Number",
            regexp ="^[1-9]\\d{0,5}$"
    )String addmission_no){
        Long l=Long.parseLong(addmission_no);
        return adminServices.getTransactions(l);
    }

    @GetMapping("/all_student/{course_name}/{year}")
    public List<Object[]> all_student(@PathVariable @Pattern(
            message = "Enter Valid Course Name\n",
            regexp = "^[a-zA-Z]{1,10}$"
    )String course_name,@PathVariable @Pattern(
          message = "Enter valid Course year\n" ,
            regexp = "^[1-5]$"
    )String year){
    return adminServices.getAllStd(course_name,year);
    }
}
