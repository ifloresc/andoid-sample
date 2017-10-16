package com.thoughtworks.pafsilva.androidbasicsworkshop.login.service;

import com.thoughtworks.pafsilva.androidbasicsworkshop.models.UserInfo;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface LoginEndpoints {

    @GET("/users")
    Call<UserInfo> getUser(@Query("email") String email, @Query("password") String password);
}
