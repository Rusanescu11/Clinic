package com.example.clinicaprivata.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "cabinet")
public class CabinetModel {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String name;
    private String description;
    private int phone;

    @OneToOne(cascade = CascadeType.ALL,fetch = FetchType.EAGER, orphanRemoval = true)
    @JsonIgnoreProperties("cabinetModel")
    private AddressModel address;

    @OneToMany(fetch = FetchType.LAZY)
    @JoinColumn(name = "cabinet_id")
    @JsonIgnoreProperties("cabinet")
    private List<MedicModel> medic =new ArrayList<>();

    @OneToOne(fetch = FetchType.EAGER, mappedBy = "cabinetModel", cascade = CascadeType.ALL)
    @JsonIgnoreProperties("cabinetModel")
    private PhotoC photoC;

    public PhotoC getPhotoC() {
        return photoC;
    }

    public void setPhotoC(PhotoC photoC) {
        this.photoC = photoC;
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

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getPhone() {
        return phone;
    }

    public void setPhone(int phone) {
        this.phone = phone;

    }


    public List<MedicModel> getMedic() {
        return medic;
    }

    public void setMedic(List<MedicModel> medic) {
        this.medic = medic;
    }

    public AddressModel getAddress() {
        return address;
    }

    public void setAddress(AddressModel address) {
        this.address = address;
    }

}


