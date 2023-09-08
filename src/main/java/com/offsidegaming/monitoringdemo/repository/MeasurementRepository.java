package com.offsidegaming.monitoringdemo.repository;

import com.offsidegaming.monitoringdemo.domain.Measurement;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface MeasurementRepository extends JpaRepository<Measurement, Long>, JpaSpecificationExecutor<Measurement> {

}
