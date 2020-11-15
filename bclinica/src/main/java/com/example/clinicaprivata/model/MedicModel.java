package com.example.clinicaprivata.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "medic")

public class MedicModel {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String name;
    private int phone;

    @OneToOne(fetch = FetchType.EAGER, mappedBy = "medic", cascade = CascadeType.ALL)
    @JsonIgnoreProperties("medic")
    private PhotoM photoM;



    @ManyToOne(fetch = FetchType.EAGER)
    @JsonIgnoreProperties("medic")
    private CabinetModel cabinet;

    @Enumerated(EnumType.STRING)
    private SpecializationModel specializationModel;

    @OneToMany(fetch = FetchType.LAZY)
    @JsonIgnoreProperties("medic")
    @JoinColumn(name = "medic_id")
    private List<RezervationModel> rezervationModelList = new ArrayList<>();

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

    public CabinetModel getCabinet() {
        return cabinet;
    }

    public void setCabinet(CabinetModel cabinet) {
        this.cabinet = cabinet;
    }

    public SpecializationModel getSpecializationModel() {
        return specializationModel;
    }

    public void setSpecializationModel(SpecializationModel specializationModel) {
        this.specializationModel = specializationModel;
    }

    public List<RezervationModel> getRezervationModelList() {
        return rezervationModelList;
    }

    public void setRezervationModelList(List<RezervationModel> rezervationModelList) {
        this.rezervationModelList = rezervationModelList;
    }

    public PhotoM getPhotoM() {
        return photoM;
    }

    public void setPhotoM(PhotoM photoM) {
        this.photoM = photoM;
    }

}
