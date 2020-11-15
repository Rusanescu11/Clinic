package com.example.clinicaprivata.repository;

import com.example.clinicaprivata.model.RezervationModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ReservationRepository extends JpaRepository<RezervationModel,Long> {
}
