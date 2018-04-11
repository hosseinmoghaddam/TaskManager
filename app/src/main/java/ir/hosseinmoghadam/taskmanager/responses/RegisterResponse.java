package ir.hosseinmoghadam.taskmanager.responses;

/**
 * Created by hossein moghadam on 02/03/2018.
 */
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class RegisterResponse {

    @SerializedName("firstName")
    private String firstName;

    @SerializedName("lastName")
    private String lastName;

    @SerializedName("instanceId")
    private String instanceId;

    @SerializedName("active")
    private String active;

    @SerializedName("guest")
    private String guest;

    @SerializedName("email")
    private String email;

    @SerializedName("phonNumber")
    private String phonNumber;

    @SerializedName("creationDate")
    private String creationDate;

    @SerializedName("userId")
    private String userId;

    @SerializedName("avatar")
    private String avatar;

    @SerializedName("username")
    private String username;

    public RegisterResponse() {
    }

    public RegisterResponse(String firstName, String lastName, String instanceId, String active, String guest, String email, String phonNumber, String creationDate, String userId, String avatar, String username) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.instanceId = instanceId;
        this.active = active;
        this.guest = guest;
        this.email = email;
        this.phonNumber = phonNumber;
        this.creationDate = creationDate;
        this.userId = userId;
        this.avatar = avatar;
        this.username = username;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getInstanceId() {
        return instanceId;
    }

    public void setInstanceId(String instanceId) {
        this.instanceId = instanceId;
    }

    public String getActive() {
        return active;
    }

    public void setActive(String active) {
        this.active = active;
    }

    public String getGuest() {
        return guest;
    }

    public void setGuest(String guest) {
        this.guest = guest;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhonNumber() {
        return phonNumber;
    }

    public void setPhonNumber(String phonNumber) {
        this.phonNumber = phonNumber;
    }

    public String getCreationDate() {
        return creationDate;
    }

    public void setCreationDate(String creationDate) {
        this.creationDate = creationDate;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getAvatar() {
        return avatar;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }
}
