package com.se4f7.prj301.service.impl;

import java.util.Collection;

import javax.servlet.http.Part;

import com.se4f7.prj301.constants.ErrorMessage;
import com.se4f7.prj301.model.PaginationModel;
import com.se4f7.prj301.model.request.AdsModelRequest;
import com.se4f7.prj301.model.response.AdsModelResponse;
import com.se4f7.prj301.repository.AdsRepository;
import com.se4f7.prj301.service.AdsService;
import com.se4f7.prj301.utils.FileUtil;
import com.se4f7.prj301.utils.StringUtil;

public class AdsServiceImpl implements AdsService {

    private AdsRepository adsRepository = new AdsRepository();

    @Override
    public boolean create(AdsModelRequest request, Collection<Part> images, String username) {
        AdsModelResponse oldAds = adsRepository.getByPosition(request.getPosition());
        if (oldAds != null) {
            throw new RuntimeException(ErrorMessage.POSITION_EXISTS);
        }
        String[] imageNames = FileUtil.saveFiles(images);
        request.setImages(imageNames);
        return adsRepository.create(request, username);
    }

    @Override
    public boolean update(String id, AdsModelRequest request, Collection<Part> images, String username) {
        Long idNumber = StringUtil.parseLong("Id", id);
        AdsModelResponse oldAds = adsRepository.getById(idNumber);
        if (oldAds == null) {
            throw new RuntimeException(ErrorMessage.RECORD_NOT_FOUND);
        }
        if (!request.getPosition().equalsIgnoreCase(oldAds.getPosition())) {
            AdsModelResponse otherAds = adsRepository.getByPosition(request.getPosition());
            if (otherAds != null) {
                throw new RuntimeException(ErrorMessage.POSITION_EXISTS);
            }
        }
        String[] imageNames;
        if (images != null && !images.isEmpty()) {
            imageNames = FileUtil.saveFiles(images);
            FileUtil.removeFiles(oldAds.getImages());
        } else {
            imageNames = oldAds.getImages();
        }
        //String[] imageNames = FileUtil.saveFiles(images, oldAds.getImages()); // new
        request.setImages(imageNames);
        return adsRepository.update(idNumber, request, username);
    }






    @Override
    public boolean deleteById(String id) {
        Long idNumber = StringUtil.parseLong("Id", id);
        AdsModelResponse oldAds = adsRepository.getById(idNumber);
        if (oldAds == null) {
            throw new RuntimeException(ErrorMessage.RECORD_NOT_FOUND);
        }
        if (oldAds.getImages() != null) {
            FileUtil.removeFiles(oldAds.getImages());
        }
        return adsRepository.deleteById(idNumber);
    }

    @Override
    public AdsModelResponse getById(String id) {
        Long idNumber = StringUtil.parseLong("Id", id);
        AdsModelResponse oldAds = adsRepository.getById(idNumber);
        if (oldAds == null) {
            throw new RuntimeException(ErrorMessage.RECORD_NOT_FOUND);
        }
        return oldAds;
    }

    @Override
    public PaginationModel filter(String page, String size, String position) {
        int pageNumber = StringUtil.parseInt("Page", page);
        int sizeNumber = StringUtil.parseInt("Size", size);
        return adsRepository.filterByPosition(pageNumber, sizeNumber, position);
    }
}
