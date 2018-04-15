package ir.hosseinmoghadam.taskmanager.services;


import java.util.List;
import java.util.Map;

import ir.hosseinmoghadam.taskmanager.models.Task;
import ir.hosseinmoghadam.taskmanager.responses.TaskResponse;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.DELETE;
import retrofit2.http.Header;
import retrofit2.http.Headers;
import retrofit2.http.POST;
import retrofit2.http.Path;

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

    @Headers({
            "X-Backtory-Object-Storage-Id: 5a9314fce4b092a32b632af9",
            "Content-Type: application/json"
    })
    @POST("/object-storage/classes/query/jobs/")
    Call<TaskResponse> all(
            @Header("Authorization") String token);

    @Headers({
            "X-Backtory-Object-Storage-Id: 5a9314fce4b092a32b632af9",
            "Content-Type: application/json"
    })
    @DELETE("/object-storage/classes/jobs/{ObjectId}")
    Call<String> delete(
            @Header("Authorization") String token, @Path("ObjectId") String ObjectId);
}
