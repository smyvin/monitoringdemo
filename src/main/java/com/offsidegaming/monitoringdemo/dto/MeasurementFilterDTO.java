package com.offsidegaming.monitoringdemo.dto;

import com.offsidegaming.monitoringdemo.domain.Measurement;
import lombok.Getter;
import lombok.Setter;
import org.springframework.format.annotation.DateTimeFormat;

import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;

@Getter
@Setter
public class MeasurementFilterDTO {

    @NotNull
    private Long userId;

    private Measurement.Type type;

    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME)
    private LocalDateTime startDate;

    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME)
    private LocalDateTime endDate;

}
