package com.example.user.myapplication;

import java.util.ArrayList;
import java.util.List;

public class value_search {

    //value
    //value
    private int resId;
    private String breed;
    private String nameEng;

    private String descriptionA1;
//    private String descriptionA2;
    private String description1;
//    private String description2;
    List<value_search> dogs = new ArrayList<>();


    value_search() {}

    value_search(int resId,
                 String breed,
                 String nameEng,
                 String descriptionA1,
//                 String descriptionA2,
                 String description1
//                 String description2

    ) {
        this.resId = resId;
        this.breed = breed;
        this.nameEng = nameEng;
        this.descriptionA1 = descriptionA1;
//        this.descriptionA2 = descriptionA2;
        this.description1 = description1;
//        this.description2 = description2;
    }


    value_search(int resId, String breed) {
        this.resId = resId;
        this.breed = breed;
    }

    public int getResId() {
        return resId;
    }

    public void setResId(int resId) {
        this.resId = resId;
    }


    public String getBreed() {
        return breed;
    }

    public void setBreed(String breed) {
        this.breed = breed;
    }


    public String getNameEng() {
        return nameEng;
    }

    public void setNameEng(String nameEng) {
        this.nameEng = nameEng;
    }


    public String getDescriptionA1() {
        return descriptionA1;
    }

    public void setDescriptionA1(String descriptionA1) {
        this.descriptionA1 = descriptionA1;
    }

//    public String getDescriptionA2() {
//        return descriptionA2;
//    }
//
//    public void setDescriptionA2(String descriptionA2) {
//        this.descriptionA2 = descriptionA2;
//    }

    public String getDescription1() {
        return description1;
    }

    public void setDescription1(String description1) {
        this.description1 = description1;
    }

//    public String getDescription2() {
//        return description2;
//    }
//
//    public void setDescription2(String description2) {
//        this.description2 = description2;
//    }


    public List<value_search> getDogs() {
        return dogs;
    }

    public void setDogs(List<value_search> dogs) {
        this.dogs = dogs;
    }
}
