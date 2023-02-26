package com.maintenecelog.maintenancelog.dto;

import com.maintenecelog.maintenancelog.model.Machine;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.format.annotation.DateTimeFormat;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class MaintenenceDto {

    @NotNull(message = "Machine id is required")
    private Long machineId;
    @NotBlank(message = "Description is required")
    private String description;
    @DateTimeFormat
    private LocalDate exDate;
    @NotNull
    private boolean exResult;

}
