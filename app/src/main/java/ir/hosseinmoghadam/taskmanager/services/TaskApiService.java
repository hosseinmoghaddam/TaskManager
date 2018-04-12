package ir.hosseinmoghadam.taskmanager.services;

import java.util.ArrayList;
import java.util.Map;

import ir.hosseinmoghadam.taskmanager.models.Task;
import ir.hosseinmoghadam.taskmanager.models.User;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.Header;
import retrofit2.http.Headers;
import retrofit2.http.POST;

/**
 * Created by hossein moghadam on 02/03/2018.
 */

public interface TaskApiService {
    @Headers({
            "X-Backtory-Object-Storage-Id: 5a9314fce4b092a32b632af9",
            "Content-Type: application/json"
    })
    @POST("/object-storage/classes/jobs/")
    Call<Map<String, String>> add(
            @Header("Authorization") String token, @Body Task task);


}
