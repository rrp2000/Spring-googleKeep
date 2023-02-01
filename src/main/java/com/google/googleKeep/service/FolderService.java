package com.google.googleKeep.service;


import com.google.googleKeep.model.FolderModel;
import com.google.googleKeep.repository.FolderRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import java.util.Collections;
import java.util.List;
import java.util.Optional;

@Service
public class FolderService {

    Logger logger = LoggerFactory.getLogger(FolderService.class);
    @Autowired
    private FolderRepository folderRepository;

    //Folder creation service
    public FolderModel createFolder(FolderModel folderData){
        logger.info("Creating folder");
        if(folderData.getNotes()==null){
            folderData.setNotes(Collections.emptyList());
        }
        FolderModel newFolder = folderRepository.save(folderData);
        logger.info("Folder created");
        return newFolder;
    }

    //Retrive all folders service
    public List<FolderModel> getAllFolders(){
        logger.info("Getting all folders");
        List<FolderModel> allFolders = folderRepository.findAll();
        logger.info("Retrived all folders");
        return allFolders;
    }

    //Retrive folder by id service
    public FolderModel getFolderById(String id){
        logger.info("Getting folder by id");
        Optional<FolderModel> folder = folderRepository.findById(id);
        if(folder.isPresent()){
            logger.info("Found folder");
            return folder.get();
        }else{
            logger.info("Folder not found");
            return null;
        }
    }

    //retrive folder by Userid service
    public List<FolderModel> getAllFoldersByUserId(String userId){
        List<FolderModel> folders = folderRepository.findAllByUserId(userId);

        return folders;

    }

    //Update folder service
    public FolderModel updateFolder(String id,FolderModel folderUpdates){
        logger.info("Checking if folder exists");
        Optional<FolderModel> folderOptional = folderRepository.findById(id);
        if(folderOptional.isPresent()){
            FolderModel folder = folderOptional.get();
            logger.info("Found folder");
            logger.info("updating folder");
            folder.setName(folderUpdates.getName()==null?folder.getName():folderUpdates.getName());
            if(folderUpdates.getNotes()!=null){
                List<String> oldNotes = folder.getNotes();
                List<String> notes = folderUpdates.getNotes();
                oldNotes.addAll(notes);
                folder.setNotes(oldNotes);
            }
            folderRepository.save(folder);
            logger.info("Folder updated");
            return folder;
        }
        logger.info("Folder not found");
        return null;
    }


    //delete a note by its index service
    public FolderModel deleteNote(int index,String id){

        logger.info("Checking if folder exists");
        Optional<FolderModel> folderOptional = folderRepository.findById(id);
        if(folderOptional.isPresent()) {
            FolderModel folder = folderOptional.get();
            logger.info("Found folder");
            List<String> notes = folder.getNotes();
            notes.remove(index);
            folder.setNotes(notes);
            logger.info("deleting note");
            folderRepository.save(folder);
            logger.info("Note deleted");
            return folder;
        }
        logger.warn("No such folder");
        return null;
    }


    //delete a folder by its id service
    public String deleteFolder(String id){
        logger.info("Checking if folder exists");
        Optional<FolderModel> folderOptional = folderRepository.findById(id);
        if(folderOptional.isPresent()) {
            FolderModel folder = folderOptional.get();
            logger.info("Found folder");
            folderRepository.delete(folder);
            logger.info("Folder deleted");
            return "Deleted";
        }
        logger.warn("No such folder");
        return null;
    }


}
