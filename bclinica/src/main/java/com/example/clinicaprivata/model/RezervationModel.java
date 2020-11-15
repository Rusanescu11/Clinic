package com.example.clinicaprivata.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.*;
import java.sql.Date;
import java.sql.Time;

@Entity
@Table(name = "rezervation")
public class RezervationModel {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)

    private Long id;
    private String name;
    private Date date;
    private Time startConsultation;
    private Time endConsultation;


    @ManyToOne(fetch = FetchType.EAGER)
    @JsonIgnoreProperties("rezervationModelList")
    private MedicModel medic;


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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

    public MedicModel getMedic() {
        return medic;
    }

    public void setMedic(MedicModel medic) {
        this.medic = medic;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
