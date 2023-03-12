package com.doc.doc.services.impl;

import com.doc.doc.daos.DocDAO;
import com.doc.doc.dtos.DocDTO;
import com.doc.doc.models.DocModel;
import com.doc.doc.services.DocService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

import static com.google.common.base.Preconditions.checkNotNull;
import static java.util.Objects.isNull;

@Service
public class DocServiceImpl implements DocService {

    @Autowired
    DocDAO docDAO;

    @Autowired
    ModelMapper modelMapper;

    @Override
    public void createDocument(DocDTO docDTO) {

        checkNotNull(docDTO.getContent());
        checkNotNull(docDTO.getTitle());
        checkNotNull(docDTO.getUserId());

        DocModel docModel = modelMapper.map(docDTO, DocModel.class);

        if (isNull(docModel.getAvailable())) {
            docModel.setAvailable(false);
        }

        docDAO.save(docModel);
        modelMapper.map(docModel, docDTO);
    }

    @Override
    public List<DocDTO> getDocumentsForUserId(String userId) {
        return null;
    }

    @Override
    public DocDTO getDocument(String docId) {
        return null;
    }

    @Override
    public List<DocDTO> getRecentDocuments() {
        return null;
    }

    @Override
    public void updateDocument(DocDTO docDTO, String userId) {

    }
}
