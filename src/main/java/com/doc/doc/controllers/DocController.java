package com.doc.doc.controllers;

import com.doc.doc.daos.DocDAO;
import com.doc.doc.dtos.DocDTO;
import com.doc.doc.exceptions.UserNotAllowedException;
import com.doc.doc.models.DocModel;
import com.doc.doc.services.DocService;
import com.doc.doc.services.TokenService;
import org.apache.commons.lang3.StringUtils;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.stream.Collectors;

import static org.springframework.http.HttpHeaders.AUTHORIZATION;

@RestController
@RequestMapping("/doc")
public class DocController {

    @Autowired
    DocService docService;

    @Autowired
    TokenService tokenService;

    @PostMapping("/create")
    public DocDTO createDocument(@RequestBody DocDTO docDTO) {

        docService.createDocument(docDTO);
        return docDTO;
    }

    // get all documents for a specific user
    @GetMapping("/{userId}/all")
    public List<DocDTO> getUserDocuments(@PathVariable String userId) {

        return docService.getDocumentsForUserId(userId);

    }

    // get a public document
    @GetMapping("/{docId}")
    public DocDTO getDocument(@PathVariable String docId) {

        return docService.getDocument(docId);
    }

    @GetMapping("/recent")
    public List<DocDTO> getRecentDocuments() {

        return docService.getRecentDocuments();

    }

    // modify his own doocuments
    @PutMapping("/update")
    public DocDTO updateDocument(@RequestBody DocDTO docDTO, HttpServletRequest httpServletRequest) throws UserNotAllowedException {

        String tokenHeader = httpServletRequest.getHeader(AUTHORIZATION);

        String jwtToken = StringUtils.removeStart(tokenHeader, "Bearer ").trim();

        // TODO: extract userId from token
        //String userId = "scnsjcnsjncdjscnjsnjccsdc";  // userId --> issuer on jwtToken

        String userId = tokenService.getUserId(jwtToken);

        docService.updateDocument(docDTO, userId);
        return docDTO;
    }

}
