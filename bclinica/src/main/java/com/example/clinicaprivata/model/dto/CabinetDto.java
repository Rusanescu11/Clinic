package com.example.clinicaprivata.model.dto;

import java.util.ArrayList;
import java.util.List;

public class CabinetDto {
    private Long id;
    private String name;
    private String description;
    private int phone;
    private String idPhoto;
    private  AddressDto address;
private List<MedicDto>medicDtoList=new ArrayList<>();
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

    public String getIdPhoto() {
        return idPhoto;
    }

    public void setIdPhoto(String idPhoto) {
        this.idPhoto = idPhoto;
    }

    public AddressDto getAddress() {
        return address;
    }

    public void setAddress(AddressDto address) {
        this.address = address;
    }

    public List<MedicDto> getMedicDtoList() {
        return medicDtoList;
    }

    public void setMedicDtoList(List<MedicDto> medicDtoList) {
        this.medicDtoList = medicDtoList;
    }
}
