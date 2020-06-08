package com.example.demo.Service;

import com.google.cloud.storage.*;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Paths;

@Service
public class FileService {
    private String bucket_name = "testimage-bucket";
    private String projectId = "test-firestore-native-project";
    private String destFilePath = System.getProperty("user.dir") + "/uploads";
    Storage storage = StorageOptions.newBuilder().setProjectId(projectId).build().getService();

    //upload
    public String storefile(MultipartFile file) {
        BlobId blobId = BlobId.of(bucket_name, "my_blob_name");
        BlobInfo blobInfo = BlobInfo.newBuilder(blobId).setContentType("application/octet-stream").build();
        try { Blob blob = storage.create(blobInfo, file.getBytes() ); }
        catch (Exception exception){
            System.out.print("exception : No ");
        }
        System.out.println("file store on bucket : " + bucket_name);
        return "FileUpload";
    }

    //download
    public String showfile(String filename) {
        BlobId blobId = BlobId.of(bucket_name, filename);
        Blob blob = storage.get(blobId);
        blob.downloadTo(Paths.get(destFilePath));
        System.out.println("downloaded");
        return "FileUpload";
        //String url = blob.signUrl();
    }

    //list of images
    public ArrayList<URL> listimages(ArrayList<String> listnames){
        ArrayList<URL> listUrl  = new ArrayList<>();
        int durationInMinutes = 5;
        for(String s : listnames){
            BlobId blobId = BlobId.of(bucket_name, s);
            Blob blob = storage.get(blobId);
            //blob.downloadTo(Paths.get(destFilePath));
            listUrl.add( blob.signUrl(durationInMinutes, TimeUnit.MINUTES) );//valid for 5 minutes
            System.out.println("downloaded " + s);
        }
        return  listUrl;
    }

}
