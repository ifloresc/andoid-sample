package com.thoughtworks.pafsilva.androidbasicsworkshop.login.service;

import com.thoughtworks.pafsilva.androidbasicsworkshop.models.UserInfo;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface LoginEndpoints {

    @GET("/login")
    Call<UserInfo> getUser(@Query("username") String email, @Query("password") String password);
}
