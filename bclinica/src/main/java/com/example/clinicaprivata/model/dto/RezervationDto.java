package com.example.clinicaprivata.model.dto;

import java.sql.Date;
import java.sql.Time;

public class RezervationDto {
    private Long id;
    private String name;
    private Date date;
    private Time startConsultation;
    private Time endConsultation;
    private MedicDto medic;

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

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public Time getStartConsultation() {
        return startConsultation;
    }

    public void setStartConsultation(Time startConsultation) {
        this.startConsultation = startConsultation;
    }

    public Time getEndConsultation() {
        return endConsultation;
    }

    public void setEndConsultation(Time endConsultation) {
        this.endConsultation = endConsultation;
    }

    public MedicDto getMedic() {
        return medic;
    }

    public void setMedic(MedicDto medic) {
        this.medic = medic;
    }
}
