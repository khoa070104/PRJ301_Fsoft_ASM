package com.se4f7.prj301.service.impl;

import com.se4f7.prj301.constants.ErrorMessage;
import com.se4f7.prj301.model.PaginationModel;
import com.se4f7.prj301.model.response.MessageModelResponse;
import com.se4f7.prj301.repository.MessageRepository;
import com.se4f7.prj301.service.MessageService;
import com.se4f7.prj301.utils.StringUtil;

public class MessageServiceImpl implements MessageService {

    private MessageRepository messageRepository = new MessageRepository();

    @Override
    public boolean deleteById(String id) {
        Long idNumber = StringUtil.parseLong("Id", id);
        MessageModelResponse oldMessage = messageRepository.getById(idNumber);
        if (oldMessage == null) {
            throw new RuntimeException(ErrorMessage.RECORD_NOT_FOUND);
        }
        return messageRepository.deleteById(idNumber);
    }

    @Override
    public MessageModelResponse getById(String id) {
        Long idNumber = StringUtil.parseLong("Id", id);
        MessageModelResponse oldMessage = messageRepository.getById(idNumber);
        if (oldMessage == null) {
            throw new RuntimeException(ErrorMessage.RECORD_NOT_FOUND);
        }
        return oldMessage;
    }

    @Override
    public PaginationModel filter(String page, String size, String email) {
        int pageNumber = StringUtil.parseInt("Page", page);
        int sizeNumber = StringUtil.parseInt("Size", size);
        return messageRepository.filterByEmail(pageNumber, sizeNumber, email);
    }
}
