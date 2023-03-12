package com.doc.doc.dtos;

import lombok.Data;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.LastModifiedDate;

import java.util.Date;

@Data
public class DocDTO {

    private String id;
    private String content;
    private String userId;
    private String title;
    private Boolean available;
    private Date dateCreated;
    private Date dateUpdated;
}
