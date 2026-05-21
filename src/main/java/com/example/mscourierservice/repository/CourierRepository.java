package com.example.mscourierservice.repository;

import com.example.mscourierservice.entity.Courier;
import com.example.mscourierservice.enums.CourierStatus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CourierRepository extends JpaRepository<Courier,Long> {
    Optional<Courier> findFirstByStatus(CourierStatus status);
}
