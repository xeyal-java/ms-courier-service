package com.example.mscourierservice.repository.specification;

import com.example.mscourierservice.dto.CourierCriteria;
import com.example.mscourierservice.entity.Courier;
import com.example.mscourierservice.enums.CourierStatus;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Predicate;
import jakarta.persistence.criteria.Root;
import org.springframework.data.jpa.domain.Specification;

import java.util.ArrayList;
import java.util.List;

public class CourierSpecification implements Specification<Courier> {

    private final CourierCriteria courierCriteria;

    public CourierSpecification(CourierCriteria courierCriteria) {
        this.courierCriteria = courierCriteria;
    }

    @Override
    public Predicate toPredicate(Root<Courier> root, CriteriaQuery<?> query, CriteriaBuilder cb) {
        List<Predicate> predicates = new ArrayList<>();
        predicates.add(cb.equal(root.get("status"), CourierStatus.FREE));

        if (courierCriteria.getName() != null && !courierCriteria.getName().trim().isEmpty()) {
            predicates.add(cb.like(cb.lower(root.get("name")), "%" + courierCriteria.getName().toLowerCase() + "%"));
        }
        if (courierCriteria.getPhone() != null && !courierCriteria.getPhone().trim().isEmpty()) {
            predicates.add(cb.like(root.get("phone"), "%" + courierCriteria.getPhone() + "%"));
        }

        return cb.and(predicates.toArray(new Predicate[0]));
    }
}
