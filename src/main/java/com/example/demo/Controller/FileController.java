package com.example.demo.Controller;

import com.example.demo.Service.FileService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

@RestController
public class FileController {

    @Autowired
    FileService fileService;

    @GetMapping("/")
    public String home(){
        return "FileUpload";
    }

    @GetMapping("file/{filename}")
    public String showfile(@PathVariable String filename){
        return fileService.showfile(filename);
    }
    
    @PostMapping("/listofimages")
    public ArrayList<URL> imagelist(@RequestBody ListOfImages imagelist){
        //System.out.println("in listofimages");
        ArrayList<String> list = imagelist.getNameList();
        return fileService.listimages(list);
    }

    @PostMapping("/getFile")
    public String getfile( @RequestParam("files") MultipartFile file){
        return fileService.storefile(file);
    }
}
