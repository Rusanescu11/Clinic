package com.example.clinicaprivata.controller;

import com.example.clinicaprivata.model.CabinetModel;
import com.example.clinicaprivata.model.MedicModel;
import com.example.clinicaprivata.model.PhotoC;
import com.example.clinicaprivata.model.PhotoM;
import com.example.clinicaprivata.model.dto.MedicDto;
import com.example.clinicaprivata.model.files.ResponseFile;
import com.example.clinicaprivata.model.files.ResponseMessage;
import com.example.clinicaprivata.repository.CabinetRepository;
import com.example.clinicaprivata.repository.MedicRepository;
import com.example.clinicaprivata.service.MedicService;
import com.example.clinicaprivata.service.PhotoMService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.util.List;
import java.util.stream.Collectors;

@CrossOrigin
@RestController
public class MedicController {
    @Autowired
    private MedicRepository medicRepository;
    @Autowired
    private MedicService medicService;
    @Autowired
    private PhotoMService photoMService;

    @PostMapping("/doctor")
    public void add(@RequestBody MedicModel medicModel) {
        medicRepository.save(medicModel);
    }

    @DeleteMapping("/doctor/{id}")
    public void deleteById(@PathVariable(name = "id") Long id) {
        medicRepository.deleteById(id);
    }

    @GetMapping("/doctor")
    public List<MedicDto> getAll() {
        return medicService.getDoctors();
    }
    @GetMapping("/doctor/cabinet/{id}")
    public List<MedicDto> geDoctorsByCabinet(@PathVariable(name = "id") Long id) {
        return  medicService.getDoctorsByCabinet(id);
    }

    @GetMapping("/doctor/{id}")
    public MedicDto getBydId(@PathVariable(name = "id") Long id) {
        return medicService.getMedicById(id);
    }

    @PutMapping("/doctor")
    public void update(@RequestBody MedicModel medicModel) {
        medicRepository.save(medicModel);
    }

    @PostMapping("/photom")
    public ResponseEntity<ResponseMessage> uploadFile(@RequestParam("photo") MultipartFile photom) {
        String message;
        try {
            photoMService.store(photom);

            message = "Uploaded the file successfully: " + photom.getOriginalFilename();
            return ResponseEntity.status(HttpStatus.OK).body(new ResponseMessage(message));
        } catch (Exception e) {
            message = "Could not upload the file: " + photom.getOriginalFilename() + "!";
            return ResponseEntity.status(HttpStatus.EXPECTATION_FAILED).body(new ResponseMessage(message));
        }
    }

    @RequestMapping(value = "/photom", method = RequestMethod.GET)
    public ResponseEntity<List<ResponseFile>> getListFiles() {
        List<ResponseFile> files = photoMService.getPhotos().map(dbFile -> {
            String fileDownloadUri = ServletUriComponentsBuilder
                    .fromCurrentContextPath()
                    .path("/photom/")
                    .path(dbFile.getId())
                    .toUriString();
            return new ResponseFile(
                    dbFile.getName(),
                    fileDownloadUri,
                    dbFile.getType(),
                    dbFile.getData().length);
        }).collect(Collectors.toList());
        return ResponseEntity.status(HttpStatus.OK).body(files);
    }

    @GetMapping("/photom/{id}")
    public ResponseEntity<byte[]> getFile(@PathVariable String id) {
        PhotoM photoM = photoMService.getPhoto(id);
        return ResponseEntity.ok()
                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + photoM.getName() + "\"")
                .body(photoM.getData());
    }

    @GetMapping("/photoM/{id}")
    public ResponseEntity<List<ResponseFile>> getMedicPhoto(@PathVariable(name = "id") Long id) {
        List<ResponseFile> files = photoMService.getMedicPhoto(id).map(dbFile -> {
            String fileDownloadUri = ServletUriComponentsBuilder
                    .fromCurrentContextPath()
                    .path("/photom/")
                    .path(dbFile.getId())
                    .toUriString();
            return new ResponseFile(
                    dbFile.getName(),
                    fileDownloadUri,
                    dbFile.getType(),
                    dbFile.getData().length);
        }).collect(Collectors.toList());
        return ResponseEntity.status(HttpStatus.OK).body(files);
    }

    @DeleteMapping("/doctor/{photoId}/delete")
    public void deletePhoto(@PathVariable(name = "photoId") String photoId) {
        photoMService.delete(photoId);
    }
}
