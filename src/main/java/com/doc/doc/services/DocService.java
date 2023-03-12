package com.doc.doc.services;

import com.doc.doc.dtos.DocDTO;

import java.util.List;

public interface DocService {
    void createDocument(DocDTO docDTO);

    List<DocDTO> getDocumentsForUserId(String userId);

    DocDTO getDocument(String docId);

    List<DocDTO> getRecentDocuments();

    void updateDocument(DocDTO docDTO, String userId);
}
