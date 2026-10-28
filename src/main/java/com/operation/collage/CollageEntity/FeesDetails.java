package com.operation.collage.CollageEntity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity 
@Setter 
@Getter 
@NoArgsConstructor 
@AllArgsConstructor 
@Table(name="Fees_Details")
public class FeesDetails {
    
    @Id 
    @Column(name="addmission_no")
    private Long addmission_no;

    @NotBlank
    @Column(name="course")
    private String course;

    @NotBlank
    @Column(name="Year")
    private String year;

    @NotNull
    @Column(name="total_fees")
    private double total_fees;

    @NotNull
    @Column(name="pending_fees")
    private double pending_fees;

    @OneToOne
    @JoinColumn(
            name = "addmission_no",
            referencedColumnName = "addmission_number"
    )
    private StudentEntity studentEntity;
}
