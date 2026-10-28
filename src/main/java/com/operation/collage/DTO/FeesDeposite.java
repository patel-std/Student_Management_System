package com.operation.collage.DTO;

import jakarta.validation.constraints.Pattern;
import lombok.Data;

@Data
public class FeesDeposite {
    @Pattern(
            message = "Enter valid Amount",
            regexp = "^[1-9]\\d{1,7}(\\.\\d+)?$"
    )
    private String amount;
}
