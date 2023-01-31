package com.google.googleKeep.repository;

import com.google.googleKeep.model.FolderModel;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FolderRepository extends MongoRepository<FolderModel,String> {
}
