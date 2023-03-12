package com.doc.doc.services.impl;

import com.doc.doc.daos.DocDAO;
import com.doc.doc.dtos.DocDTO;
import com.doc.doc.services.DocService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DocServiceImpl implements DocService {

    @Autowired
    DocDAO docDAO;

    @Override
    public void createDocument(DocDTO docDTO) {

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
