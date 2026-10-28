package com.operation.collage.CollageEntity;

import java.time.LocalDateTime;

import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;
import org.hibernate.annotations.CreationTimestamp;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity 
@Getter 
@Setter
@NoArgsConstructor 
@AllArgsConstructor 
@Table(name="Student_Details")
public class StudentEntity {
    
    @Column(name="addmission_number")
    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private Long addmission_no;

    @Column(name="student_name")
    @NotBlank 
    @Pattern (
        message="Enter Valid Name",
        regexp="^[a-zA-Z\\s]{1,40}$"
    )
    private String name;

    @Column(name="father_name")
    @NotBlank 
     @Pattern (
        message="Enter Valid Father Name",
        regexp="^[a-zA-Z\\s]{1,40}$"
    )
    private String fname;

    @Column(name="mother_name")
    @NotBlank 
     @Pattern (
        message="Enter Valid Mother Name",
        regexp="^[a-zA-Z\\s]{1,40}$"
    )
    private String mname;

    @Column(name="course_name")
    @NotBlank 
     @Pattern (
        message="Enter Valid Course Name",
        regexp="^[a-zA-Z]{1,20}$"
    )
    private String cname;

    @Column(name="year")
    @Pattern(
            message = "Year must be numeric only",
            regexp = "^\\d{1,5}$"
    )
    private String year;

    @Column(name="phone_number")
    @NotBlank
    @Pattern (
        message="Enter Valid Phone Number",
        regexp="^[6-9]\\d{9}$"
    )
    private String phone;

    @Column(name="email_id")
    @Email
    private String email;

    @Column(name="adhar_number")
    @NotBlank 
     @Pattern (
        message="Enter Valid Name",
        regexp="^\\d{12}$"
    )
    private String adhar;

    @CreationTimestamp 
    @Column(name="created_at")
    private LocalDateTime created_at;

    @OneToOne(mappedBy = "studentEntity")
    private FeesDetails feesDetails;

}
