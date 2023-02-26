package com.maintenecelog.maintenancelog.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import org.springframework.format.annotation.DateTimeFormat;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import java.time.LocalDate;

@Getter
@Setter
@AllArgsConstructor
public class UDTExaminationDto {

    @DateTimeFormat
    private LocalDate examinedAt;
    @NotNull
    private boolean result;
    private String description;
    @NotNull
    @Pattern(regexp = "^[0-9]{10,12}", message = "Licence number must contains at least 10 and maximum 12 digits")
    private Long examinatorId;

}
