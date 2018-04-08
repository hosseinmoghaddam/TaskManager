package ir.hosseinmoghadam.taskmanager.services;

import ir.hosseinmoghadam.taskmanager.models.User;
import ir.hosseinmoghadam.taskmanager.responses.LoginResponse;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.Header;
import retrofit2.http.POST;

/**
 * Created by hossein moghadam on 02/03/2018.
 */

public interface RegisterApiService {

    @POST("/auth/users")
    Call<User> register(@Header("X-Backtory-Authentication-Id") String authorization , @Body User user);
}
