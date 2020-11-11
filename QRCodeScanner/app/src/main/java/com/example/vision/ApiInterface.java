package com.example.vision;

import com.example.vision.Model.User;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Query;

public interface ApiInterface {
    @GET("/api/user")
    Call<List<User>> getUser();

    @GET("api/user/{_id}")
    Call<User> getUser(@Query("_id") String _id);

    @POST("api/user")
    Call<User> createUser(@Body User user);
}
