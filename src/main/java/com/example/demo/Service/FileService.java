package com.example.demo.Service;

import com.google.cloud.storage.*;
import javafx.util.Pair;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;
import org.springframework.web.multipart.MultipartFile;

import java.net.URL;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.concurrent.TimeUnit;

@Service
public class FileService {
    private String bucket_name = "testimage-bucket";
    private String projectId = "test-firestore-native-project";
    private String destFilePath = System.getProperty("user.dir") + "/uploads";
    Storage storage = StorageOptions.newBuilder().setProjectId(projectId).build().getService();

    public String storefile(MultipartFile[] file) {
        for(MultipartFile eachfile : file){
            try {
                BlobId blobId = BlobId.of(bucket_name, eachfile.getOriginalFilename());
                BlobInfo blobInfo = BlobInfo.newBuilder(blobId).setContentType("image").build();
                Blob blob = storage.create(blobInfo, eachfile.getBytes());
            } catch (Exception exception) {
                System.out.println("exception : Unsuccesfull " + exception.getMessage());
            }
            System.out.println("file " + eachfile.getOriginalFilename() + " store on bucket : " + bucket_name  );
        }
        return "FileUpload";
    }

    public Model listimages(ArrayList<String> listnames, Model model){
        HashMap<String,String> fileNameURL = new HashMap<>();
        for (String filename : listnames) {
            BlobId blobId = BlobId.of(bucket_name, filename);
            Blob blob = storage.get(blobId);
            try {
                if (blob.exists()) {
                    fileNameURL.put(filename, "https://storage.googleapis.com/" + bucket_name + "/" + filename);
                    System.out.println(filename + " : " + "https://storage.googleapis.com/" + bucket_name + "/" + filename);
                } else
                    System.out.println("URL not found for " + filename);
            }catch (Exception exception){
                System.out.println("Exception : " + exception.getMessage());
            }
        }
        model.addAllAttributes(fileNameURL);
        return model;
    }



}
