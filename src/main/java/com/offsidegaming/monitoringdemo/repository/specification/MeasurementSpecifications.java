package com.offsidegaming.monitoringdemo.repository.specification;

import com.offsidegaming.monitoringdemo.domain.Measurement;
import com.offsidegaming.monitoringdemo.dto.MeasurementFilterDTO;
import org.springframework.data.jpa.domain.Specification;

import javax.persistence.criteria.Predicate;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class MeasurementSpecifications {

    public static Specification<Measurement> getFilteredMeasurements(MeasurementFilterDTO params) {

        return (root, criteriaQuery, criteriaBuilder) -> {
            List<Predicate> predicates = new ArrayList<>();

            predicates.add(criteriaBuilder.equal(root.get("user").<Long> get("id"), params.getUserId()));

            if (Objects.nonNull(params.getType())) {
                predicates.add(criteriaBuilder.equal(root.get("type"), params.getType()));
            }

            if (Objects.nonNull(params.getStartDate())) {
                predicates.add(criteriaBuilder.greaterThan(root.get("createDate"), params.getStartDate()));
            }

            if (Objects.nonNull(params.getEndDate())) {
                predicates.add(criteriaBuilder.lessThan(root.get("createDate"), params.getEndDate()));
            }

            return criteriaBuilder.and(predicates.toArray(new Predicate[0]));
        };
    }
}
