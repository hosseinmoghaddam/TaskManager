package ir.hosseinmoghadam.taskmanager.services;

import ir.hosseinmoghadam.taskmanager.models.User;
import ir.hosseinmoghadam.taskmanager.responses.LoginResponse;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.Header;
import retrofit2.http.POST;
import retrofit2.http.Part;
import retrofit2.http.Path;

/**
 * Created by hossein moghadam on 02/03/2018.
 */

public interface LoginApiService {
    @FormUrlEncoded
    @POST("/auth/login")
    Call<LoginResponse> login(@Header("X-Backtory-Authentication-Id") String authorization, @Header("X-Backtory-Authentication-Key") String authorization2,
                              @Field("username") String username, @Field("password") String pass);
}
