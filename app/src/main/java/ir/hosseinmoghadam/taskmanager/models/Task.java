package ir.hosseinmoghadam.taskmanager.models;

import com.google.gson.annotations.SerializedName;

/**
 * Created by hossein moghadam on 09/04/2018.
 */

public class Task {

    @SerializedName("name")
    private String name;
    @SerializedName("description")
    private String description;
    @SerializedName("completed")
    private Boolean completed;

    public Task() {
    }

    public Task(String name, String description, Boolean completed) {
        this.name = name;
        this.description = description;
        this.completed = completed;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Boolean getCompleted() {
        return completed;
    }

    public void setCompleted(Boolean completed) {
        this.completed = completed;
    }
}
