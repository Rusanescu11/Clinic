package com.example.clinicaprivata.service;

import com.example.clinicaprivata.model.CabinetModel;
import com.example.clinicaprivata.model.PhotoC;
import com.example.clinicaprivata.repository.CabinetRepository;
import com.example.clinicaprivata.repository.PhotoCRepository;
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
public class PhotoCService {
    @Autowired
    private PhotoCRepository photoCRepository;

    @Autowired
    private CabinetRepository cabinetRepository;

    public PhotoC store(MultipartFile file) throws IOException, InterruptedException {
        String fileName = StringUtils.cleanPath(file.getOriginalFilename());
        PhotoC photoC = new PhotoC(fileName, file.getContentType(), file.getBytes());
        TimeUnit.SECONDS.sleep(3);
        List<CabinetModel> cabinet = cabinetRepository.findAll();
        CabinetModel cabinetModel = cabinet.get(cabinet.size() - 1);
        photoC.setCabinetModel(cabinetModel);
        return photoCRepository.save(photoC);
    }

    public PhotoC getPhoto(String id) {
        return photoCRepository.findById(id).get();
    }


    public Stream<PhotoC> getCabinetPhoto(long id) {
        CabinetModel cabinetModel = cabinetRepository.findById(id).orElse(null);
        List<PhotoC> list = new ArrayList<>();
        list.add(cabinetModel.getPhotoC());
        return list.stream();
    }

    public void delete(String photo) {
        photoCRepository.deleteById(photo);
    }

    public Stream<PhotoC> getAllphotos() {
        return photoCRepository.findAll().stream();
    }
}
