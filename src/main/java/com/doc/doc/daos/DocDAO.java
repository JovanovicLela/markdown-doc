package com.doc.doc.daos;

import com.doc.doc.models.DocModel;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DocDAO extends MongoRepository<DocModel, String> {
}
