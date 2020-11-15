package com.example.clinicaprivata.controller;

import com.example.clinicaprivata.model.RezervationModel;
import com.example.clinicaprivata.model.dto.RezervationDto;
import com.example.clinicaprivata.repository.ReservationRepository;
import com.example.clinicaprivata.service.RezervationService;
import com.sun.scenario.effect.impl.sw.sse.SSEBlend_SRC_OUTPeer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin
@RestController
public class RezervationController {
    @Autowired
    private ReservationRepository reservationRepository;
    @Autowired
    private RezervationService rezervationService;

    @PostMapping("/reservation/{id}")
    public void add(@RequestBody RezervationDto rezervationDto, @PathVariable (name = "id")Long id) {

        rezervationService.save(rezervationDto,id);
    }
    @DeleteMapping("/reservation/{id}")
    public void deleteById(@PathVariable(name = "id") Long id) {
        reservationRepository.deleteById(id);
    }

    @GetMapping("/reservation")
    public List<RezervationModel> getAll() {
        return reservationRepository.findAll();
    }

    @GetMapping("/reservation/{id}")
    public RezervationModel getBydId(@PathVariable(name = "id") Long id) {
        return reservationRepository.findById(id).orElse(null);
    }

    @PutMapping("/reservation")
    public void update(@RequestBody RezervationModel rezervationModel) {
        RezervationModel update = reservationRepository.findById(rezervationModel.getId()).orElse(null);
        update.setDate(rezervationModel.getDate());
        update.setStartConsultation(rezervationModel.getStartConsultation());
        update.setEndConsultation(rezervationModel.getEndConsultation());
        update.setMedic(rezervationModel.getMedic());
        reservationRepository.save(rezervationModel);
    }
}
