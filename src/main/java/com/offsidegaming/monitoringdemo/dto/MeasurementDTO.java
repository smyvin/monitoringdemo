package com.offsidegaming.monitoringdemo.dto;

import com.offsidegaming.monitoringdemo.domain.Measurement;
import lombok.*;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class MeasurementDTO {

    private Measurement.Type type;

    private LocalDateTime createDate;

    private BigDecimal qty;
}
