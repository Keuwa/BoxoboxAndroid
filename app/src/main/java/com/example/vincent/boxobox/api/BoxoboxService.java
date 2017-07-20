package com.example.vincent.boxobox.api;

import com.example.vincent.boxobox.model.Alarm;
import com.example.vincent.boxobox.model.Answer;
import com.example.vincent.boxobox.model.Humidity;
import com.example.vincent.boxobox.model.LoginBody;
import com.example.vincent.boxobox.model.Luminosity;
import com.example.vincent.boxobox.model.Question;
import com.example.vincent.boxobox.model.Temperature;
import com.example.vincent.boxobox.model.Token;
import com.example.vincent.boxobox.model.User;

import java.util.List;
import java.util.Map;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.Headers;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Path;
import retrofit2.http.Query;
import retrofit2.http.QueryMap;

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

    @GET("humidities")
    Call<List<Humidity>> getHumidities();

    @GET("alarms")
    Call<List<Alarm>> getAlarms();

    @GET("questions")
    Call<List<Question>> getQuestions();

    @POST("questions")
    Call<Question> postQuestion(@Body Question task);

    @GET("answers")
    Call<List<Answer>> getAnswerForQuestion(@QueryMap Map<String,String> options);

}
