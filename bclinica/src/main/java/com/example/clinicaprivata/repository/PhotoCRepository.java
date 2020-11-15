package com.example.clinicaprivata.repository;

import com.example.clinicaprivata.model.PhotoC;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PhotoCRepository extends JpaRepository<PhotoC, String> {
}
