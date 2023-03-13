package com.doc.doc.services.impl;

import com.doc.doc.daos.DocDAO;
import com.doc.doc.dtos.DocDTO;
import com.doc.doc.exceptions.UserNotAllowedException;
import com.doc.doc.models.DocModel;
import com.doc.doc.services.DocService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import javax.print.Doc;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;
import java.util.stream.Collectors;

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

        final List<DocModel> allByUserId = docDAO.findAllByUserIdOrderByDateUpdatedDesc(userId);

        return allByUserId.stream().map(docModel -> modelMapper.map(docModel, DocDTO.class)).collect(Collectors.toList());

    }

    @Override
    public DocDTO getDocument(String docId) {

        final Optional<DocModel> optionalDocModel = docDAO.findById(docId);

        if (optionalDocModel.isPresent()) {
            return modelMapper.map(optionalDocModel, DocDTO.class);
        }
        return null;
    }

    @Override
    public List<DocDTO> getRecentDocuments() {

        final Page<DocModel> docModelsUpdatedAt = docDAO.findAll(PageRequest.of(0, 3, Sort.by(Sort.Direction.DESC, "dateUpdated")));

        return docModelsUpdatedAt.stream().map(docModel -> modelMapper.map(docModel, DocDTO.class)).collect(Collectors.toList());
    }

    @Override
    public void updateDocument(DocDTO docDTO, String userId) throws UserNotAllowedException {

        final Optional<DocModel> optionalDocModel = docDAO.findById(docDTO.getId());

        if (optionalDocModel.isPresent()) {

            final DocModel docModel = optionalDocModel.get();
            if (docModel.getUserId().equals(userId)) {
                modelMapper.map(docDTO, docModel);
                docDAO.save(docModel);
                modelMapper.map(docModel, docDTO);
                return;
            } else {
                throw new UserNotAllowedException("You are not allowed to modify this document");
            }

        }

        throw new NoSuchElementException("No document with id: " + docDTO.getId() + " was found");
    }
}
