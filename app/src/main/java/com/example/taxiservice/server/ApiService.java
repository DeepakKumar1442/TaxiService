package com.example.taxiservice.server;

import com.example.taxiservice.categories.CategoriesResponse;
import com.example.taxiservice.login.LoginResponse;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.POST;
import retrofit2.http.Query;

public interface ApiService {
    @POST("api/users/login")
    Call<LoginResponse> loginData(@Query("email") String email,
                                  @Query("password") String password,
                                  @Query("role") String role);



    @GET("api/vehicles/categories")
    Call<CategoriesResponse> categoriesData(@Header("Authorization")  String token);


}
