package com.operation.collage.CollageEntity;

import java.time.LocalDateTime;

import jakarta.persistence.*;
import org.hibernate.annotations.CreationTimestamp;

import jakarta.validation.constraints.NotNull;
import lombok.*;

@Entity 
@Getter 
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Table(name="Fees_Transaction")
public class FeesEntity {

    @Id 
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    @Column(name="transaction_No")
    private Long transaction_no;

    @NotNull 
    @Column(name="addmission_number")
    private Long addmission_no;

    @NotNull 
    @Column(name="total_fees")
    private double total_fees;
 
    @NotNull 
    @Column(name="deposite_amt")
    private double deposite_fees;

    @NotNull 
    @Column(name="pending_amt")
    private double avl_fees;

    @Column(name="creation_at")
    @CreationTimestamp 
    private LocalDateTime created_at;

}
