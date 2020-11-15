package com.example.clinicaprivata.repository;

import com.example.clinicaprivata.model.PhotoM;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PhotoMRepository extends JpaRepository<PhotoM, String> {
}
