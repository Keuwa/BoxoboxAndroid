package com.example.vincent.boxobox.api;

import android.content.Context;
import android.content.Intent;

import com.example.vincent.boxobox.views.LoginActivity;

import java.io.IOException;

import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by Vincent on 04/07/2017.
 */

public class Connection {
    static Connection INSTANCE;
    private OkHttpClient.Builder httpClient =  new OkHttpClient.Builder();
    private String authToken;
    private Retrofit.Builder builder ;
    private BoxoboxService apiService;

    public static boolean unAuthorized(Integer code, Context context){
        if(code == 401){
            Intent intent = new Intent(context,LoginActivity.class);
            intent.putExtra(LoginActivity.ERROR401,"Veuillez vous reconnecter");
            context.startActivity(intent);
            return true;
        }
        return false;
    }

    public Connection(final String authToken){
        this.authToken = null;
        if (authToken != null) {
            this.authToken = authToken;
            httpClient.addInterceptor(new Interceptor() {
                @Override
                public okhttp3.Response intercept(Chain chain) throws IOException {
                    Request original = chain.request();
                    // Request customization: add request headers
                    Request.Builder requestBuilder = original.newBuilder()
                            .header("Authorization", authToken)
                            .method(original.method(), original.body());
                    Request request = requestBuilder.build();
                    return chain.proceed(request);
                }
            });
        }
        HttpLoggingInterceptor logging = new HttpLoggingInterceptor();
        // set your desired log level
        logging.setLevel(HttpLoggingInterceptor.Level.BODY);

        httpClient.addInterceptor(logging);


        builder = new Retrofit.Builder()
                .baseUrl(BoxoboxService.END_POINT)
                .addConverterFactory(GsonConverterFactory.create())
                .client(httpClient.build());


        Retrofit retrofit = builder.build();
        apiService = retrofit.create(BoxoboxService.class);
    }

    public static BoxoboxService get() {
        if(INSTANCE == null){
            INSTANCE = new Connection(null);
        }
        return INSTANCE.apiService;
    }


    public static BoxoboxService get(String auth) {
        if(INSTANCE == null || INSTANCE.authToken == null || !INSTANCE.authToken.equals(auth)){
            INSTANCE = new Connection(auth);
        }
        return INSTANCE.apiService;
    }
}
