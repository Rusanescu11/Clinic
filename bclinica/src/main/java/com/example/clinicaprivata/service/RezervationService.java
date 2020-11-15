package com.example.clinicaprivata.service;

import com.example.clinicaprivata.model.MedicModel;
import com.example.clinicaprivata.model.RezervationModel;
import com.example.clinicaprivata.model.dto.MedicDto;
import com.example.clinicaprivata.model.dto.RezervationDto;
import com.example.clinicaprivata.repository.MedicRepository;
import com.example.clinicaprivata.repository.ReservationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class RezervationService {
    @Autowired
    private ReservationRepository reservationRepository;
    @Autowired
    private MedicRepository medicRepository;
    @Autowired
    private MedicService medicService;

    public boolean checkRezervation(RezervationDto rezervationDto, RezervationDto old) { //rezwervare old este rezervare deja existenta a fiecarui pacient

        if ((rezervationDto.getDate().toLocalDate().isEqual(old.getDate().toLocalDate()))) {//rezervationDto.getDate().toLocalDate este rezervarea pe care doresc sa o fac(cea noua)
            System.out.println("data este egala");

            if ((rezervationDto.getStartConsultation().toString().equals(old.getStartConsultation().toString()))) {//se verifica daca se suprapun sau nu rezervarile
                System.out.println("test2");
                return true;
            }
            if ((rezervationDto.getStartConsultation().before(old.getStartConsultation())) &&
                    rezervationDto.getEndConsultation().after(old.getStartConsultation())) {
                System.out.println("test3");
                return true;
            }
            if (rezervationDto.getStartConsultation().after(old.getStartConsultation()) &&
                    rezervationDto.getEndConsultation().before(old.getEndConsultation())) {
                System.out.println("test4");
                return true;
            }
            if (rezervationDto.getStartConsultation().before(old.getStartConsultation()) &&
                    rezervationDto.getEndConsultation().after(old.getEndConsultation())){
                System.out.println("test6");
                return true;
            }
        }


        return false;
    }

    private boolean checkMedicReservation(MedicDto medicDto, RezervationDto noua) {
        for (RezervationDto old : medicDto.getRezervation()) {//forul ruleaza in toate rezervarile pe care deja le are medicul
            if (checkRezervation(noua, old)) { //verifica daca se suprapun
                return true;
            }
        }
        return false;
    }

    public void save(RezervationDto rezervationDto, Long id) {//primeste parametru o rezervare din frontend si id ul unui medic
        MedicDto medicDto = medicService.getMedicById(id);//se creaza un medic Dto nou si se pune in el medicul gasit dupa id ul dorit(acesta metoda este in clasa medicService)
        RezervationModel rezervationModel = new RezervationModel();//se creaza o rezervareModel noua
        rezervationModel.setId(rezervationDto.getId());//se populeaza revervarea cu atributele rezervarii din frontend
        rezervationModel.setName(rezervationDto.getName());
        rezervationModel.setDate(rezervationDto.getDate());
        rezervationModel.setStartConsultation(rezervationDto.getStartConsultation());
        rezervationModel.setEndConsultation(rezervationDto.getEndConsultation());
        Optional<MedicModel> medicModelOptional = medicRepository.findById(id);//
        if (medicModelOptional.isPresent()) {
            MedicModel medicModel = medicModelOptional.get();
            rezervationModel.setMedic(medicModel);
        }
        if (medicDto.getRezervation().size() > 0) {
            System.out.println(medicDto.getRezervation().size());
            if (!checkMedicReservation(medicDto, rezervationDto)) {
                reservationRepository.save(rezervationModel);
            }
        } else {

            reservationRepository.save(rezervationModel);
        }
    }
}
