package com.example.clinicaprivata.controller;

import com.example.clinicaprivata.model.AddressModel;
import com.example.clinicaprivata.repository.AddressRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin
public class AdressController {
    @Autowired
    private AddressRepository addressRepository;

    @PostMapping("/address")
    public void add(@RequestBody AddressModel addressModel) {
        addressRepository.save(addressModel);
    }

    @DeleteMapping("/address/{id}")
    public void deletebyId(@PathVariable(name = "id") Long id) {
        addressRepository.deleteById(id);
    }

    @GetMapping("/address")
    public List<AddressModel> getAll() {
        return addressRepository.findAll();
    }

    @GetMapping("/address/{id}")
    public AddressModel findById(@PathVariable(name = "id") Long id) {
        return addressRepository.findById(id).orElse(null);
    }

    @PutMapping("/address")
    public void update(@RequestBody AddressModel addressModel) {
        AddressModel edit = addressRepository.findById(addressModel.getId()).orElse(null);
        edit.setCountry(addressModel.getCountry());
        edit.setCity(addressModel.getCity());
        edit.setStreet(addressModel.getStreet());
        edit.setNumber(addressModel.getNumber());
        addressRepository.save(addressModel);
    }
}
