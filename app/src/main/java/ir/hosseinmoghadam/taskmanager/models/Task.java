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

    @SerializedName("_id")
    private String id;

    @SerializedName("userID")
    private String userID;

    public Task() {
    }

    public Task(String name, String description, Boolean completed, String userID) {
        this.name = name;
        this.description = description;
        this.completed = completed;
        this.userID = userID;
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

    public String getId() {
        return id;
    }

    public Boolean getCompleted() {
        return completed;

    }

    public void setCompleted(Boolean completed) {
        this.completed = completed;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getUserID() {
        return userID;
    }

    public void setUserID(String userID) {
        this.userID = userID;
    }
}
