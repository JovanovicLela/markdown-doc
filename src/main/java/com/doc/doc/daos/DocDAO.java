package com.doc.doc.daos;

import com.doc.doc.models.DocModel;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface DocDAO extends MongoRepository<DocModel, String> {
    List<DocModel> findAllByUserIdOrderByDateUpdatedDesc(String userId);
}
