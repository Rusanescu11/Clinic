package com.example.clinicaprivata.controller;

import com.example.clinicaprivata.model.CabinetModel;
import com.example.clinicaprivata.model.PhotoC;
import com.example.clinicaprivata.model.dto.CabinetDto;
import com.example.clinicaprivata.model.files.ResponseFile;
import com.example.clinicaprivata.model.files.ResponseMessage;
import com.example.clinicaprivata.repository.CabinetRepository;
import com.example.clinicaprivata.service.CabinetService;
import com.example.clinicaprivata.service.PhotoCService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RestController
@CrossOrigin

public class CabinetController {
    @Autowired
    private CabinetRepository cabinetRepository;
    @Autowired
    private PhotoCService photoCService;
    @Autowired
    private CabinetService cabinetService;

    @PostMapping("/cabinet")
    public void add(@RequestBody CabinetModel cabinetModel) {
        cabinetRepository.save(cabinetModel);
    }

    @DeleteMapping("/cabinet/{id}")
    public void deleteById(@PathVariable(name = "id") Long id) {
        cabinetRepository.deleteById(id);
    }

    @GetMapping("/cabinet")
    public List<CabinetModel> getAll() {
        return cabinetRepository.findAll();
    }

    @GetMapping("/cabinet/{id}")
    public CabinetDto getBydId(@PathVariable(name = "id") Long id) {
        return cabinetService.getCabinet(id);
    }

    @PutMapping("/cabinet")
    public void update(@RequestBody CabinetModel cabinetModel) {
        cabinetRepository.save(cabinetModel);
    }

    @PostMapping("/photoc")
    public ResponseEntity<ResponseMessage> uploadFile(@RequestParam("photo") MultipartFile photoc) {
        String message;
        try {
            photoCService.store(photoc);

            message = "Uploaded the file successfully: " + photoc.getOriginalFilename();
            return ResponseEntity.status(HttpStatus.OK).body(new ResponseMessage(message));
        } catch (Exception e) {
            message = "Could not upload the file: " + photoc.getOriginalFilename() + "!";
            return ResponseEntity.status(HttpStatus.EXPECTATION_FAILED).body(new ResponseMessage(message));
        }
    }
    @RequestMapping(value = "/photoc", method = RequestMethod.GET)
    public ResponseEntity<List<ResponseFile>> getListFiles() {
        List<ResponseFile> files = photoCService.getAllphotos().map(dbFile -> {
            String fileDownloadUri = ServletUriComponentsBuilder
                    .fromCurrentContextPath()
                    .path("/photoc/")
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

    @GetMapping("/photoc/{id}")
    public ResponseEntity<byte[]> getFile(@PathVariable String id) {
        PhotoC photoC = photoCService.getPhoto(id);
        return ResponseEntity.ok()
                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + photoC.getName() + "\"")
                .body(photoC.getData());
    }

    @GetMapping("/photoC/{id}")
    public ResponseEntity<List<ResponseFile>> getCabinetPhoto(@PathVariable(name = "id") Long id) {
        List<ResponseFile> files = photoCService.getCabinetPhoto(id).map(dbFile -> {
            String fileDownloadUri = ServletUriComponentsBuilder
                    .fromCurrentContextPath()
                    .path("/photoc/")
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

    @DeleteMapping("/cabinet/{photoId}/delete")
    public void deletePhoto(@PathVariable(name = "photoId") String photoId) {
        photoCService.delete(photoId);
    }
}
