package com.doc.doc.controllers;

import com.doc.doc.dtos.DocDTO;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/doc")
public class DocController {

    @PostMapping
    public DocDTO createDocument(@RequestBody DocDTO docDTO) {
        return null;
    }

    @GetMapping("/{userId}/all")
    public List<DocDTO> getUserDocuments(@PathVariable String userId) {
        return null;
    }

    @GetMapping("/{docId}")
    public DocDTO getDocument(@PathVariable String docId) {
        return null;
    }

    @GetMapping("/recent")
    public List<DocDTO> getRecentDocuments(@PathVariable String userId) {
        return null;
    }

    // modify his own doocuments
    @PutMapping("/update")
    public List<DocDTO> updateDocuments(@RequestBody DocDTO docDTO) {
        return null;
    }

}
