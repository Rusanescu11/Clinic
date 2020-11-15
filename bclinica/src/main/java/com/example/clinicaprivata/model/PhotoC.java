package com.example.clinicaprivata.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
@Entity
@Table(name = "photoC")
public class PhotoC {
    @Id
    @GeneratedValue(generator = "uuid")
    @GenericGenerator(name = "uuid", strategy = "uuid2")
    private String id;

    private String name;

    private String type;
    @Lob
    private byte[] data;

    @OneToOne(fetch = FetchType.LAZY)
    @JsonIgnoreProperties("photoC")
    private CabinetModel cabinetModel;

    public PhotoC(String name, String type, byte[] data) {
        this.name = name;
        this.type = type;
        this.data = data;
    }

    public PhotoC() {

    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public byte[] getData() {
        return data;
    }

    public void setData(byte[] data) {
        this.data = data;
    }

    public CabinetModel getCabinetModel() {
        return cabinetModel;
    }

    public void setCabinetModel(CabinetModel cabinetModel) {
        this.cabinetModel = cabinetModel;
    }

}
