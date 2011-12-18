package ru.eltech.elerning.geotracker.core;

import org.json.JSONException;
import org.json.JSONObject;
import ru.eltech.elerning.geotracker.util.ConnectionUtils;

import java.io.IOException;

import static ru.eltech.elerning.geotracker.core.Geo2TagParamNames.LOGIN;
import static ru.eltech.elerning.geotracker.core.Geo2TagParamNames.PASSWORD;

/**
 * Author: Kirill Korgov (korgov@yandex-team.ru)
 * Date: 12/18/11
 */
public class Geo2TagService {

    private static final String HOST = "http://tracks.osll.spb.ru:81";
    private static final String LOGIN_PATH = "/service/login";

    public static String login(final String userLogin, final String userPassword){
        final JSONObject requestParams = new JSONObject();
        try {
            requestParams.put(LOGIN, userLogin);
            requestParams.put(PASSWORD, userPassword);
            return ConnectionUtils.sendPostRequest(HOST+LOGIN_PATH, requestParams.toString());
        } catch (JSONException e) {
            throw new RuntimeException("Incorrect login or password format: login: {" + userLogin + "}, pwd: {" + userPassword + "}", e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

}
