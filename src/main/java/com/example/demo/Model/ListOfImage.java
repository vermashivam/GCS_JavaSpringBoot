package com.example.demo.Model;

import org.springframework.stereotype.Component;

import java.util.ArrayList;

@Component
public class ListOfImages {
    ArrayList<String> nameList;

    public ListOfImages() {
    }

    public ListOfImages(ArrayList<String> nameList) {
        this.nameList = nameList;
    }

    public ArrayList<String> getNameList() {
        return nameList;
    }

    public void setName(ArrayList<String> nameList) {
        this.nameList = nameList;
    }
}
