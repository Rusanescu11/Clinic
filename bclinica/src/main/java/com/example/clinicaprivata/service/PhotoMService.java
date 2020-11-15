package com.example.clinicaprivata.service;

import com.example.clinicaprivata.model.CabinetModel;
import com.example.clinicaprivata.model.MedicModel;
import com.example.clinicaprivata.model.PhotoC;
import com.example.clinicaprivata.model.PhotoM;
import com.example.clinicaprivata.repository.MedicRepository;
import com.example.clinicaprivata.repository.PhotoMRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;
import java.util.stream.Stream;

@Service
public class PhotoMService {
    @Autowired
    private MedicRepository medicRepository;
    @Autowired
    private PhotoMRepository photoMRepository;


    public PhotoM store(MultipartFile file) throws IOException, InterruptedException {
        String fileName = StringUtils.cleanPath(file.getOriginalFilename());
        PhotoM photoM = new PhotoM(fileName, file.getContentType(), file.getBytes());
        TimeUnit.SECONDS.sleep(3);
        List<MedicModel> medic = medicRepository.findAll();
        MedicModel medicModel = medic.get(medic.size() - 1);
        photoM.setMedic(medicModel);
        return photoMRepository.save(photoM);
    }

    public PhotoM getPhoto(String id) {
        return photoMRepository.findById(id).get();
    }

    public Stream<PhotoM> getMedicPhoto(long id) {
        MedicModel medicModel = medicRepository.findById(id).orElse(null);
        List<PhotoM> list = new ArrayList<>();
        list.add(medicModel.getPhotoM());
        return list.stream();
    }

    public Stream<PhotoM> getPhotos() {
        return photoMRepository.findAll().stream();
    }
    public void delete(String photo) {
        photoMRepository.deleteById(photo);
    }
}
