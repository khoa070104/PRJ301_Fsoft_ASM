package com.se4f7.prj301.service.impl;

import javax.servlet.http.Part;

import com.se4f7.prj301.constants.ErrorMessage;
import com.se4f7.prj301.model.PaginationModel;
import com.se4f7.prj301.model.request.WebSettingRequest;
import com.se4f7.prj301.model.response.WebSettingResponse;
import com.se4f7.prj301.repository.WebSettingRepository;
import com.se4f7.prj301.service.WebSettingService;
import com.se4f7.prj301.utils.FileUtil;
import com.se4f7.prj301.utils.StringUtil;

public class WebSettingServiceImpl implements WebSettingService {

    private WebSettingRepository webSettingRepository = new WebSettingRepository();

    @Override
    public boolean create(WebSettingRequest request, Part image, String username) {
        WebSettingResponse oldWebSetting = webSettingRepository.getByType(request.getType());
        if (oldWebSetting != null) {
            throw new RuntimeException(ErrorMessage.TYPE_INVALID);
        }
        if (image != null && image.getSubmittedFileName() != null) {
            String fileName = FileUtil.saveFile(image);
            request.setImage(fileName);
        }
        return webSettingRepository.create(request, username);
    }

    @Override
    public boolean update(String id, WebSettingRequest request, Part image, String username) {
        Long idNumber = StringUtil.parseLong("Id", id);
        WebSettingResponse oldWebSetting = webSettingRepository.getById(idNumber);
        if (oldWebSetting == null) {
            throw new RuntimeException(ErrorMessage.RECORD_NOT_FOUND);
        }
        if (!request.getType().equalsIgnoreCase(oldWebSetting.getType())) {
            WebSettingResponse otherWebSetting = webSettingRepository.getByType(request.getType());
            if (otherWebSetting != null) {
                throw new RuntimeException(ErrorMessage.TYPE_INVALID);
            }
        }
        if (image != null && image.getSubmittedFileName() != null) {
            FileUtil.removeFile(oldWebSetting.getImage());
            String fileName = FileUtil.saveFile(image);
            request.setImage(fileName);
        } else {
            request.setImage(oldWebSetting.getImage());
        }
        return webSettingRepository.update(idNumber, request, username);
    }

    @Override
    public boolean deleteById(String id) {
        Long idNumber = StringUtil.parseLong("Id", id);
        WebSettingResponse oldWebSetting = webSettingRepository.getById(idNumber);
        if (oldWebSetting == null) {
            throw new RuntimeException(ErrorMessage.RECORD_NOT_FOUND);
        }
        if (oldWebSetting.getImage() != null) {
            FileUtil.removeFile(oldWebSetting.getImage());
        }
        return webSettingRepository.deleteById(idNumber);
    }

    @Override
    public WebSettingResponse getById(String id) {
        Long idNumber = StringUtil.parseLong("Id", id);
        WebSettingResponse oldWebSetting = webSettingRepository.getById(idNumber);
        if (oldWebSetting == null) {
            throw new RuntimeException(ErrorMessage.RECORD_NOT_FOUND);
        }
        return oldWebSetting;
    }

    @Override
    public PaginationModel filter(String page, String size, String typeFilter) {
        int pageNumber = StringUtil.parseInt("Page", page);
        int sizeNumber = StringUtil.parseInt("Size", size);
        return webSettingRepository.filterByType(pageNumber, sizeNumber, typeFilter);
    }
}
