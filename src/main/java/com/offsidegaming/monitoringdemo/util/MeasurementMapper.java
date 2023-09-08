package com.offsidegaming.monitoringdemo.util;

import com.offsidegaming.monitoringdemo.domain.Measurement;
import com.offsidegaming.monitoringdemo.dto.MeasurementDTO;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface MeasurementMapper {
    MeasurementDTO measurementToDto(Measurement measurement);
}
