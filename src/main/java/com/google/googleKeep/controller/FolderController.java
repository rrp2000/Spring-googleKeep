package com.google.googleKeep.controller;

import com.google.googleKeep.model.FolderModel;
import com.google.googleKeep.service.FolderService;
import jakarta.validation.Valid;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class FolderController {

    Logger logger = LoggerFactory.getLogger(FolderController.class);

    @Autowired
    private FolderService folderService;


    //creating folder
    @PostMapping("/folder")
    public ResponseEntity<FolderModel> createFolder(@RequestBody @Valid FolderModel folderModel) {
        logger.info("Started folder creation Process");
        FolderModel response = folderService.createFolder(folderModel);
        logger.info("Finished folder creation Process");
        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }

    //getting all folders
    @GetMapping("/folders")
    public ResponseEntity<?> getAllFolders() {
        logger.info("FolderController:getAllFolders: Started all folder retrieval Process");
        List<FolderModel> response = folderService.getAllFolders();
        if (response.size() == 0) {
            logger.warn("No folders found");
            return new ResponseEntity<>("No folders found", HttpStatus.NOT_FOUND);
        }
        logger.info("Finished all folder retrieval Process");
        return new ResponseEntity<List<FolderModel>>(response, HttpStatus.OK);
    }

    //get folder by id
    @GetMapping("/folders/{id}")
    public ResponseEntity<?> getFolderById(@PathVariable("id") String id) {
        logger.info("FolderController:getFolderById: Started folder retrieval Process");
        FolderModel response = folderService.getFolderById(id);
        if (response != null) {
            logger.info("Finished folder retrieval Process");
            return new ResponseEntity<>(response, HttpStatus.OK);
        }
        logger.warn("Folder not found");
        return new ResponseEntity<>("Folder not found", HttpStatus.NOT_FOUND);
    }

    //update folder
    @PutMapping("/update/{id}")
    public ResponseEntity<?> updateFolder(@PathVariable("id") String id, @RequestBody FolderModel folderUpdates) {
        logger.info("FolderController:updateFolder: Started folder name update Process");
        FolderModel response = folderService.updateFolder(id, folderUpdates);
        if (response == null) {
            return new ResponseEntity<>("Folder not found", HttpStatus.NOT_FOUND);
        }
        logger.info("Finished folder name update Process");
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    //delete a note by its index
    @DeleteMapping("/deleteNote/{id}/{index}")
    public ResponseEntity<?> deleteNote(@PathVariable int index,@PathVariable String id) {
        logger.info("FolderController:deleteNote: Started note deletion Process");
        FolderModel response = folderService.deleteNote(index, id);
        if (response == null) {
            logger.warn("Note not found");
            return new ResponseEntity<>("Note not found", HttpStatus.NOT_FOUND);
        }
        logger.info("Finished note deletion Process");
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    //delete the folder
    @DeleteMapping("/deleteFolder/{id}")
    public ResponseEntity<?> deleteFolder(@PathVariable String id) {
        logger.info("FolderController:deleteFolder: Started folder deletion Process");
        String response = folderService.deleteFolder(id);
        if (response == null) {
            logger.warn("Folder not found");
            return new ResponseEntity<>("Folder not found", HttpStatus.NOT_FOUND);
        }
        logger.info("Finished folder deletion Process");
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

}
