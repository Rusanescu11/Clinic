package com.example.clinicaprivata.repository;

import com.example.clinicaprivata.model.MedicModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MedicRepository extends JpaRepository<MedicModel,Long> {
    List<MedicModel>getAllByCabinet_Id(long id);
}
