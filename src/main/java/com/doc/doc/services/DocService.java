package com.doc.doc.services;

import com.doc.doc.dtos.DocDTO;
import com.doc.doc.exceptions.UserNotAllowedException;

import java.util.List;

public interface DocService {
    void createDocument(DocDTO docDTO);

    List<DocDTO> getDocumentsForUserId(String userId, String userId1);

    DocDTO getDocument(String docId, String userId);

    List<DocDTO> getRecentDocuments();

    void updateDocument(DocDTO docDTO, String userId) throws UserNotAllowedException;
}
