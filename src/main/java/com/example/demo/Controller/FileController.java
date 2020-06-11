package com.example.demo.Controller;

import com.example.demo.Model.ListOfImages;
import com.example.demo.Service.FileService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.thymeleaf.util.StringUtils;

import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;

@Controller
public class FileController {

    @Autowired
    FileService fileService;

    @GetMapping("/")
    public String home(){
        return "fileUpload";
    }

    @PostMapping("/storeFile")
    public String storefile( @RequestParam("files") MultipartFile[] file){
        return fileService.storefile(file);
    }

    @GetMapping("/listofimages")
    public String imagelist(){
        return "viewFile";
    }

    @PostMapping("/listofimages")
    public String imagelist(@RequestParam("n1") String n1, @RequestParam("n2") String n2, @RequestParam("n3") String n3,
                            @RequestParam("n4") String n4,@RequestParam("n5") String n5,Model model){
        //System.out.println("in listofimages")
        ArrayList<String> list = new ArrayList<>();
        if(!StringUtils.isEmpty(n1))
            list.add(n1);
        if(!StringUtils.isEmpty(n2))
            list.add(n2);
        if(!StringUtils.isEmpty(n3))
            list.add(n3);
        if(!StringUtils.isEmpty(n4))
            list.add(n4);
        if(!StringUtils.isEmpty(n5))
            list.add(n5);
        model = fileService.listimages( list , model);
        return "viewFile";
    }
}
