package ru.eltech.elerning.geotracker.core.services;

import org.json.JSONException;
import org.json.JSONObject;
import ru.eltech.elerning.geotracker.core.model.Channel;
import ru.eltech.elerning.geotracker.util.ConnectionUtils;

import java.io.IOException;
import java.util.List;

import static ru.eltech.elerning.geotracker.core.Geo2TagConstants.Params;
import static ru.eltech.elerning.geotracker.core.Geo2TagConstants.Services;

/**
 * Author: Kirill Korgov (korgov@yandex-team.ru)
 * Date: 12/18/11
 */
public class Geo2TagService {

    private static final String HOST = "http://tracks.osll.spb.ru:81";

    public static JSONObject login(final String userLogin, final String userPassword){
        final JSONObject requestParams = new JSONObject();
        try {
            requestParams.put(Params.LOGIN, userLogin);
            requestParams.put(Params.PASSWORD, userPassword);
            return new JSONObject(ConnectionUtils.sendPostRequest(HOST + Services.LOGIN, requestParams.toString()));
        } catch (JSONException e) {
            throw new RuntimeException("Incorrect login or password format: login: {" + userLogin + "}, pwd: {" + userPassword + "}", e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public static JSONObject loadAvailableChannels(final String authToken, final double lat, final double lon, final double radius){
        final JSONObject requestParams = new JSONObject();
        try {
            requestParams.put(Params.AUTH_TOKEN, authToken);
            requestParams.put(Params.LATITUDE, lat);
            requestParams.put(Params.LONGITUDE, lon);
            requestParams.put(Params.RADIUS, radius);
            return new JSONObject(ConnectionUtils.sendPostRequest(HOST + Services.CHANNELS, requestParams.toString()));
        } catch (JSONException e) {
            throw new RuntimeException("Incorrect loadAvailableChannels() params format", e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public static List<Channel> loadAvailableChannelsAsList(final String authToken, final double lat, final double lon, final double radius){
        return Geo2TagChannelHelper.wrapChannels(loadAvailableChannels(authToken, lat, lon, radius).optJSONArray(Params.CHANNELS));
    }

    @Deprecated
    public static JSONObject logout(final String authToken){
        final JSONObject requestParams = new JSONObject();
        try {
            requestParams.put(Params.AUTH_TOKEN, authToken);
            return new JSONObject(ConnectionUtils.sendPostRequest(HOST + Services.QUIT, requestParams.toString()));
        } catch (JSONException e) {
            throw new RuntimeException("Incorrect auth_token format: {" + authToken + "}", e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }


}
