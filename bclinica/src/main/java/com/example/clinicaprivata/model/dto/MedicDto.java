package com.example.clinicaprivata.model.dto;

import java.util.ArrayList;
import java.util.List;

public class MedicDto {
    private Long id;
    private String name;
    private int phone;
    private String idPhoto;
    private CabinetDto cabinet;
    private String specialization;
    private List<RezervationDto> rezervation =new ArrayList<>();


    public String getSpecialization() {
        return specialization;
    }

    public void setSpecialization(String specialization) {
        this.specialization = specialization;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getPhone() {
        return phone;
    }

    public void setPhone(int phone) {
        this.phone = phone;
    }

    public String getIdPhoto() {
        return idPhoto;
    }

    public void setIdPhoto(String idPhoto) {
        this.idPhoto = idPhoto;
    }

    public CabinetDto getCabinet() {
        return cabinet;
    }

    public void setCabinet(CabinetDto cabinet) {
        this.cabinet = cabinet;
    }

    public List<RezervationDto> getRezervation() {
        return rezervation;
    }

    public void setRezervation(List<RezervationDto> rezervation) {
        this.rezervation = rezervation;
    }
}
