package com.offsidegaming.monitoringdemo.controller;

import com.offsidegaming.monitoringdemo.domain.Measurement;
import com.offsidegaming.monitoringdemo.dto.MeasurementDTO;
import com.offsidegaming.monitoringdemo.dto.MeasurementFilterDTO;
import com.offsidegaming.monitoringdemo.repository.MeasurementRepository;
import com.offsidegaming.monitoringdemo.repository.specification.MeasurementSpecifications;
import com.offsidegaming.monitoringdemo.util.MeasurementMapper;
import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/measurement")
@RequiredArgsConstructor
public class MeasurmentController {

    private final MeasurementMapper measurementMapper;
    private final MeasurementRepository measurementRepository;

    @Operation(summary = "search measurements")
    @GetMapping("/search")
    public ResponseEntity<Page<MeasurementDTO>> search(@Valid MeasurementFilterDTO filter, Pageable pageable){
        Page<MeasurementDTO> result = measurementRepository.findAll(MeasurementSpecifications.getFilteredMeasurements(filter), pageable)
                .map(measurementMapper::measurementToDto);
        return ResponseEntity.ok().body(result);
    }

    @Operation(summary = "create measurement")
    @PostMapping
    public ResponseEntity<Void> create(@Valid @RequestBody Measurement measurement){
        measurementRepository.save(measurement);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }


}
