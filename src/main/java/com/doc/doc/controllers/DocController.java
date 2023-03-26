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
import org.springframework.security.access.prepost.PreAuthorize;
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
    @PreAuthorize("hasAnyRole('USER', 'ADMIN')")
    public DocDTO createDocument(@RequestBody DocDTO docDTO) {

        docService.createDocument(docDTO);
        return docDTO;
    }

    // get all documents for a specific user
    @GetMapping("/all/{userId}")
    @PreAuthorize("hasAnyRole('USER', 'ADMIN')")
    public List<DocDTO> getUserDocuments(@PathVariable String userId, HttpServletRequest httpServletRequest) {

        String jwtToken = getJwtTokenFromHeader(httpServletRequest);
        String userId1 = tokenService.getUserId(jwtToken);

        return docService.getDocumentsForUserId(userId, userId1);

    }

    // get a public document
    @GetMapping("/get/{docId}")
    @PreAuthorize("hasAnyRole('USER', 'ADMIN', 'ANONYMOUS')")
    public DocDTO getDocument(@PathVariable String docId, HttpServletRequest httpServletRequest) {

        String jwtToken = getJwtTokenFromHeader(httpServletRequest);
        String userId = tokenService.getUserId(jwtToken);

        return docService.getDocument(docId, userId);
    }

    @GetMapping("/recent")
    @PreAuthorize("hasAnyRole('USER', 'ADMIN', 'ANONYMOUS')")
    public List<DocDTO> getRecentDocuments() {

        return docService.getRecentDocuments();
    }

    // modify his own doocuments
    @PutMapping("/update")
    @PreAuthorize("hasAnyRole('USER', 'ADMIN')")
    public DocDTO updateDocument(@RequestBody DocDTO docDTO, HttpServletRequest httpServletRequest) throws UserNotAllowedException {

        String jwtToken = getJwtTokenFromHeader(httpServletRequest);

        // TODO: extract userId from token  // --- done
        //String userId = "scnsjcnsjncdjscnjsnjccsdc";  // userId --> issuer on jwtToken

        String userId = tokenService.getUserId(jwtToken);
        docService.updateDocument(docDTO, userId);

        return docDTO;
    }

    private String getJwtTokenFromHeader(HttpServletRequest httpServletRequest) {

        try {
            String tokenHeader = httpServletRequest.getHeader(AUTHORIZATION);
            String jwtToken = StringUtils.removeStart(tokenHeader, "Bearer ").trim();
            return jwtToken;

        } catch (NullPointerException e) {
            e.printStackTrace();
            return StringUtils.EMPTY;
        }

    }

}
