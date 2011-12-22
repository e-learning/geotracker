package ru.eltech.elerning.geotracker.core.model;

import org.json.JSONObject;

import static ru.eltech.elerning.geotracker.core.Geo2TagConstants.*;

/**
 * Author: Kirill Korgov (korgov@yandex-team.ru)
 * Date: 12/22/11
 */
public class LoginResult {
    private Status status = Status.ERROR;
    private String authToken = "";
    private String message = "";

    public LoginResult(final JSONObject loginResponse) {
            status = Status.byCode(loginResponse.optString(Params.STATUS, LOGIN_STATUS_ERROR));
            switch (status){
                case OK:
                    authToken = loginResponse.optString(Params.AUTH_TOKEN);
                case ERROR:
                    message = loginResponse.optString(Params.STATUS_DESCRIPTION, "Error: Status description not found in request.");
            }
    }

    public Status getStatus() {
        return status;
    }

    public String getAuthToken() {
        return authToken;
    }

    public String getMessage() {
        return message;
    }

    public static enum Status {
        OK(LOGIN_STATUS_OK), ERROR(LOGIN_STATUS_ERROR);

        private final String code;

        private Status(final String code) {
            this.code = code;
        }

        public static Status byCode(final String code){
            for(final Status s : values()){
                if(s.code.equals(code)){
                    return s;
                }
            }
            throw new RuntimeException("Incorrect status code!");
        }
    }
}
