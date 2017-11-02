package com.example.gin.androiduploadretrieveretrofit.API;

import com.example.gin.androiduploadretrieveretrofit.OBJECT.Person;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;

public interface API {

    @FormUrlEncoded
    @POST("/insertTextRetroFit.php")
    Call<Person> insertText(
            @Field("name") String name,
            @Field("age") String age);

    @FormUrlEncoded
    @POST("/insertImageRetroFit.php")
    Call<Person> insertImage(
            @Field("image") String image);


    @GET("/retrieveText.php")
    Call<List<Person>> getUsers();

    @GET("/retrieveImage.php")
    Call<List<Person>> getImage();

}
