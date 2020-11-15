package com.example.clinicaprivata.service;

import com.example.clinicaprivata.model.CabinetModel;
import com.example.clinicaprivata.model.MedicModel;
import com.example.clinicaprivata.model.RezervationModel;
import com.example.clinicaprivata.model.dto.CabinetDto;
import com.example.clinicaprivata.model.dto.MedicDto;
import com.example.clinicaprivata.model.dto.RezervationDto;
import com.example.clinicaprivata.repository.MedicRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class MedicService {
    @Autowired
    private MedicRepository medicRepository;
    @Autowired
    CabinetService cabinetService;

    public List<MedicDto> getDoctors() {
        List<MedicModel> medicModelList = medicRepository.findAll(); //luam lista medicilor din baza de date
        List<MedicDto> medicDtoList = new ArrayList<>();//cream o lista goala de MEDICI DTO// aici este lista cu medici care au cabinet si rezarvari DtO
        for (MedicModel medicModel : medicModelList) {//rulam un for cu medicii din baza de date
            MedicDto medicDto = new MedicDto(); //cream un medic Dto nou ptr fiecare medic model din for
            medicDto.setName(medicModel.getName());//populam fiecare medic Dto cu atributele medicului model din baza de date
            medicDto.setPhone(medicModel.getPhone());
            medicDto.setId(medicModel.getId());
            CabinetDto cabinetDto = new CabinetDto();//cream si un cabinet DTO nou
            cabinetDto.setName(medicModel.getCabinet().getName());//populam cabinetul Dto care merge in frontend cu datele cabinetului pe care il are medicul  din backend
            medicDto.setCabinet(cabinetDto); //setam medicului cabinetul
            medicDto.setSpecialization(medicModel.getSpecializationModel().name());//populam si specializarea
            List<RezervationDto> rezervationDtoList = new ArrayList<>(); //in lista care a fost la inceput goala s au salvat toate rezarvarile din backend in Dto uri
            for (RezervationModel rezervationModel : medicModel.getRezervationModelList()) {

                RezervationDto rezervationDto = new RezervationDto();
                rezervationDto.setStartConsultation(rezervationModel.getStartConsultation());
                rezervationDto.setEndConsultation(rezervationModel.getEndConsultation());
                rezervationDto.setDate(rezervationModel.getDate());
                rezervationDto.setId(rezervationModel.getId());
                rezervationDto.setName(rezervationModel.getName());
                rezervationDtoList.add(rezervationDto);
            }
            medicDto.setRezervation(rezervationDtoList);
            medicDtoList.add(medicDto);    //salvam fiecare medic Dto in lista nostra care a fost goala cu tot cu cabinet
        }
        return medicDtoList; //returnam lista de medici Dto ptr a fi folosita mai departe in frontend
    }

    public MedicDto getMedicById(long id) {
        Optional<MedicModel> medicModelOptional = medicRepository.findById(id);//cautam in baza de date un medic dupa un id
        MedicDto medicDto = new MedicDto();//cream un medic Dto gol
        if (medicModelOptional.isPresent()) { //daca medicul cu id respectiv este prezent spunem sa il arate
            MedicModel medicModel = medicModelOptional.get();
            medicDto.setId(medicModel.getId());//populam medicul Dto cu atributele din backend
            medicDto.setName(medicModel.getName());
            medicDto.setPhone(medicModel.getPhone());
            medicDto.setSpecialization(medicModel.getSpecializationModel().name());
            medicDto.setIdPhoto(medicModel.getPhotoM().getId());
            if (medicModel.getPhotoM() != null) { //daca medicul are poza ii transformam id ul pozei in Dto
                medicDto.setIdPhoto(medicModel.getPhotoM().getId()); //daca nu are poza trece mai departe insa verificarea o face
            }
            CabinetModel cabinetModel = medicModel.getCabinet(); //aici spunem ca,cabinetulModel este cabinetul ce apartine unui medic
            CabinetDto cabinetDto = new CabinetDto();//se creaza un cabinet nou DTO
            cabinetDto.setId(cabinetModel.getId());//se populeaza atributele in Frontend cu cele din backend
            cabinetDto.setName(cabinetModel.getName());
            medicDto.setCabinet(cabinetDto); //medicul seteaza cabinetul


            List<RezervationDto> rezervationDtoListt = new ArrayList<>();
            for (RezervationModel rezervationModel : medicModel.getRezervationModelList()) {
                RezervationDto rezervationDto = new RezervationDto();
                rezervationDto.setStartConsultation(rezervationModel.getStartConsultation());
                rezervationDto.setEndConsultation(rezervationModel.getEndConsultation());
                rezervationDto.setDate(rezervationModel.getDate());
                rezervationDto.setId(rezervationModel.getId());
                rezervationDto.setName(rezervationModel.getName());
                rezervationDtoListt.add(rezervationDto);
            }
            medicDto.setRezervation(rezervationDtoListt);

        }
        return medicDto;
    }

    public List<MedicDto> getDoctorsByCabinet(long id) {
        List<MedicModel> medicModelList = medicRepository.getAllByCabinet_Id(id); //luam lista medicilor din baza de date
        List<MedicDto> medicDtoList = new ArrayList<>();//cream o lista goala de MEDICI DTO// aici este lista cu medici care au cabinet si rezarvari DtO
        for (MedicModel medicModel : medicModelList) {//rulam un for cu medicii din baza de date
            MedicDto medicDto = new MedicDto(); //cream un medic Dto nou ptr fiecare medic model din for
            medicDto.setName(medicModel.getName());//populam fiecare medic Dto cu atributele medicului model din baza de date
            medicDto.setPhone(medicModel.getPhone());
            medicDto.setId(medicModel.getId());
            CabinetDto cabinetDto = new CabinetDto();//cream si un cabinet DTO nou
            cabinetDto.setName(medicModel.getCabinet().getName());//populam cabinetul Dto care merge in frontend cu datele cabinetului pe care il are medicul  din backend
            medicDto.setCabinet(cabinetDto); //setam medicului cabinetul
            medicDto.setSpecialization(medicModel.getSpecializationModel().name());//populam si specializarea
            List<RezervationDto> rezervationDtoList = new ArrayList<>(); //in lista care a fost la inceput goala s au salvat toate rezarvarile din backend in Dto uri
            for (RezervationModel rezervationModel : medicModel.getRezervationModelList()) {

                RezervationDto rezervationDto = new RezervationDto();
                rezervationDto.setStartConsultation(rezervationModel.getStartConsultation());
                rezervationDto.setEndConsultation(rezervationModel.getEndConsultation());
                rezervationDto.setDate(rezervationModel.getDate());
                rezervationDto.setId(rezervationModel.getId());
                rezervationDto.setName(rezervationModel.getName());
                rezervationDtoList.add(rezervationDto);
            }
            medicDto.setRezervation(rezervationDtoList);
            medicDtoList.add(medicDto);    //salvam fiecare medic Dto in lista nostra care a fost goala cu tot cu cabinet
        }
        return medicDtoList; //returnam lista de medici Dto ptr a fi folosita mai departe in frontend
    }

}
