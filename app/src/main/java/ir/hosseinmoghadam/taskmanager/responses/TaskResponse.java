package ir.hosseinmoghadam.taskmanager.responses;

import com.google.gson.annotations.SerializedName;

import java.util.List;

import ir.hosseinmoghadam.taskmanager.models.Task;

/**
 * Created by hossein moghadam on 12/04/2018.
 */

public class TaskResponse {

    @SerializedName("results")
    private List<Task> tasks;

    public TaskResponse() {
    }

    public TaskResponse(List<Task> tasks) {
        this.tasks = tasks;
    }

    public List<Task> getTasks() {
        return tasks;
    }

    public void setTasks(List<Task> tasks) {
        this.tasks = tasks;
    }
}
