package ir.hosseinmoghadam.taskmanager.services;

import ir.hosseinmoghadam.taskmanager.models.User;
import ir.hosseinmoghadam.taskmanager.responses.LoginResponse;
import ir.hosseinmoghadam.taskmanager.responses.RegisterResponse;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.Header;
import retrofit2.http.Headers;
import retrofit2.http.POST;

/**
 * Created by hossein moghadam on 02/03/2018.
 */

public interface RegisterApiService {

    @Headers({
            "Content-Type: application/json",
            "X-Backtory-Authentication-Id: 5a9314fbe4b04e579ee1edbe"
    })
    @POST("/auth/users")
    Call<RegisterResponse> register( @Body User user);
}
