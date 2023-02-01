package com.google.googleKeep.repository;

import com.google.googleKeep.model.FolderModel;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface FolderRepository extends MongoRepository<FolderModel,String> {

    @Query
    List<FolderModel> findAllByUserId(String userId);
}
