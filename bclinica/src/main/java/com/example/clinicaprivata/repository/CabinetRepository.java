package com.example.clinicaprivata.repository;

import com.example.clinicaprivata.model.CabinetModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CabinetRepository extends JpaRepository<CabinetModel, Long> {
}
