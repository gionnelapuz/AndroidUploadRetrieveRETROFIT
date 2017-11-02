package com.example.gin.androiduploadretrieveretrofit.OBJECT;

import com.google.gson.annotations.SerializedName;

public class Person {

    @SerializedName("name")
    private String name;
    @SerializedName("age")
    private String age;
    @SerializedName("image")
    private String image;

    private String message;

    public Person(String name, String age, String image) {
        this.name = name;
        this.age = age;
        this.image = image;
    }

    public String getName() {
        return name;
    }

    public String getAge() {
        return age;
    }

    public String getImage() {
        return image;
    }

    public String getMessage() {
        return message;
    }

}