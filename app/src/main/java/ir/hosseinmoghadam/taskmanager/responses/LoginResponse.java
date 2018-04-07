package ir.hosseinmoghadam.taskmanager.responses;

/**
 * Created by hossein moghadam on 02/03/2018.
 */
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class LoginResponse implements Serializable {

    @SerializedName("access_token")
    private String accessToken;

    @SerializedName("token_type")
    private String tokenType;

    @SerializedName("expires_in")
    private Integer expiresTn;

    @SerializedName("refresh_token")
    private String refreshToken;

    @SerializedName("scope")
    private String scope;

    @SerializedName("jti")
    private String jti;

    public LoginResponse() {
    }

    public LoginResponse(String accessToken, String tokenType, Integer expiresTn, String refreshToken, String scope, String jti) {
        this.accessToken = accessToken;
        this.tokenType = tokenType;
        this.expiresTn = expiresTn;
        this.refreshToken = refreshToken;
        this.scope = scope;
        this.jti = jti;
    }

    public String getAccessToken() {
        return accessToken;
    }

    public String getTokenType() {
        return tokenType;
    }

    public Integer getExpiresTn() {
        return expiresTn;
    }

    public String getRefreshToken() {
        return refreshToken;
    }

    public String getScope() {
        return scope;
    }

    public String getJti() {
        return jti;
    }
}
