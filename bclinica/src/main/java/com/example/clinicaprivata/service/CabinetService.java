package com.example.clinicaprivata.service;

import com.example.clinicaprivata.model.AddressModel;
import com.example.clinicaprivata.model.CabinetModel;
import com.example.clinicaprivata.model.MedicModel;
import com.example.clinicaprivata.model.dto.AddressDto;
import com.example.clinicaprivata.model.dto.CabinetDto;
import com.example.clinicaprivata.model.dto.MedicDto;
import com.example.clinicaprivata.repository.CabinetRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class CabinetService {
    @Autowired
    private CabinetRepository cabinetRepository;

    public CabinetDto getCabinet(long id) {
        Optional<CabinetModel> cabinetModelOptional = cabinetRepository.findById(id);
        CabinetDto cabinetDto = new CabinetDto();
        if (cabinetModelOptional.isPresent()) {
            CabinetModel cabinetModel = cabinetModelOptional.get();
            cabinetDto.setId(cabinetModel.getId());
            cabinetDto.setName(cabinetModel.getName());
            cabinetDto.setDescription(cabinetModel.getDescription());
            cabinetDto.setPhone(cabinetModel.getPhone());
            cabinetDto.setIdPhoto(cabinetModel.getPhotoC().getId());
            if (cabinetModel.getPhotoC() != null) {
                cabinetDto.setIdPhoto(cabinetModel.getPhotoC().getId());
            }
            AddressModel addressModel =cabinetModel.getAddress();
            AddressDto addressDto= new AddressDto();
            addressDto.setId(addressModel.getId());
            addressDto.setCountry(addressModel.getCountry());
            addressDto.setCity(addressModel.getCity());
            addressDto.setStreet(addressModel.getStreet());
            addressDto.setNumber(addressModel.getNumber());
            cabinetDto.setAddress(addressDto);
        }
        List<MedicDto>medicDtoList=new ArrayList<>();
        for(MedicModel medicModel: cabinetModelOptional.get().getMedic()){
          MedicDto medicDto=new MedicDto();
          medicDto.setId(medicModel.getId());
          medicDto.setName(medicModel.getName());
          medicDto.setSpecialization(medicModel.getSpecializationModel().name());
          medicDto.setPhone(medicModel.getPhone());
          medicDto.setIdPhoto(medicModel.getPhotoM().getId());
          medicDtoList.add(medicDto);

        }
            cabinetDto.setMedicDtoList(medicDtoList);
        return cabinetDto;
    }

}
