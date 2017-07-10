package com.example.vincent.boxobox.api;

import com.example.vincent.boxobox.model.Luminosity;
/**
 * Created by Vincent on 30/06/2017.
 */

public interface BoxoboxService {
    String END_POINT = "https://boxobox-api.herokuapp.com/api/";


    @GET("users/")
    Call<List<User>> usersGet(@QueryMap Map<String, String> options);

    @PUT("users/{userId}")
    Call<User> updateUser(@Path("userId") String userId, @Body User user);

    @GET("users/{id}")
    Call<User> userGet(@Path("id") String username);

    @POST("users/")
    Call<User> usersPost(@Body Object body);

    @Headers("Content-Type: application/json")
    @POST("users/login")
    Call<Token> usersLogin(@Body LoginBody body);


    @GET("luminosities")
    Call<List<Luminosity>> getLuminosities();

    @GET("temperatures")
    Call<List<Temperature>> getTemperatures();

    @GET("noises")
    Call<List<Noise>> getNoises();

}
