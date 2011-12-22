package ru.eltech.elerning.geotracker.core.services;

import org.json.JSONException;
import org.json.JSONObject;
import ru.eltech.elerning.geotracker.util.ConnectionUtils;
import ru.eltech.elerning.geotracker.util.StringUtils;

import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

import static ru.eltech.elerning.geotracker.core.Geo2TagConstants.Params;
import static ru.eltech.elerning.geotracker.core.Geo2TagConstants.Services;

/**
 * Author: Kirill Korgov (korgov@yandex-team.ru)
 * Date: 12/18/11
 */
public class BaseGeo2TagService {

    private static final String HOST = "http://tracks.osll.spb.ru:81";

    private static final DateFormat DATE_FORMAT = new SimpleDateFormat("dd MM yyyy HH:mm:ss.SSS");

    public static JSONObject login(final String userLogin, final String userPassword) {
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



    public static JSONObject addUser(final String userLogin, final String userPassword) {
        final JSONObject requestParams = new JSONObject();
        try {
            requestParams.put(Params.LOGIN, userLogin);
            requestParams.put(Params.PASSWORD, userPassword);
            return new JSONObject(ConnectionUtils.sendPostRequest(HOST + Services.ADD_USER, requestParams.toString()));
        } catch (JSONException e) {
            throw new RuntimeException("Incorrect login or password format: login: {" + userLogin + "}, pwd: {" + userPassword + "}", e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public static JSONObject loadAvailableChannels(final String authToken, final double lat, final double lon, final double radius) {
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

    public static JSONObject loadSubscribedChannels(final String authToken) {
        final JSONObject requestParams = new JSONObject();
        try {
            requestParams.put(Params.AUTH_TOKEN, authToken);
            return new JSONObject(ConnectionUtils.sendPostRequest(HOST + Services.SUBSCRIBED, requestParams.toString()));
        } catch (JSONException e) {
            throw new RuntimeException("Incorrect loadSubscribedChannels() params format", e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public static JSONObject subscribeChannel(final String authToken, final String channelName) {
        final JSONObject requestParams = new JSONObject();
        try {
            requestParams.put(Params.AUTH_TOKEN, authToken);
            requestParams.put(Params.CHANNEL, channelName);
            return new JSONObject(ConnectionUtils.sendPostRequest(HOST + Services.SUBSCRIBE, requestParams.toString()));
        } catch (JSONException e) {
            throw new RuntimeException("Incorrect subscribeChannel() params format", e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public static JSONObject unSubscribeChannel(final String authToken, final String channelName) {
        final JSONObject requestParams = new JSONObject();
        try {
            requestParams.put(Params.AUTH_TOKEN, authToken);
            requestParams.put(Params.CHANNEL, channelName);
            return new JSONObject(ConnectionUtils.sendPostRequest(HOST + Services.UNSUBSCRIBE, requestParams.toString()));
        } catch (JSONException e) {
            throw new RuntimeException("Incorrect unSubscribeChannel() params format", e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public static JSONObject applyMark(final String authToken, final String channelName,
                                       final String description, final String link,
                                       final double lat, final double lon,
                                       final Date time, final String title) {
        final JSONObject requestParams = new JSONObject();
        try {
            requestParams.put(Params.AUTH_TOKEN, authToken);
            requestParams.put(Params.CHANNEL, channelName);
            requestParams.put(Params.DESCRIPTION, description);
            requestParams.put(Params.LINK, StringUtils.nvl(link, "unknown"));
            requestParams.put(Params.LATITUDE, lat);
            requestParams.put(Params.LONGITUDE, lon);
            requestParams.put(Params.TIME, DATE_FORMAT.format(time));
            requestParams.put(Params.TITLE, title);
            return new JSONObject(ConnectionUtils.sendPostRequest(HOST + Services.APPLY_MARK, requestParams.toString()));
        } catch (JSONException e) {
            throw new RuntimeException("Incorrect applyMark() params format", e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public static JSONObject addChannel(final String authToken, final String channelName,
                                       final String description, final String url, final double activeRadius) {
        final JSONObject requestParams = new JSONObject();
        try {
            requestParams.put(Params.AUTH_TOKEN, authToken);
            requestParams.put(Params.NAME, channelName);
            requestParams.put(Params.DESCRIPTION, description);
            requestParams.put(Params.URL, url);
            requestParams.put(Params.ACTIVE_RADIUS, activeRadius);
            return new JSONObject(ConnectionUtils.sendPostRequest(HOST + Services.ADD_CHANNEL, requestParams.toString()));
        } catch (JSONException e) {
            throw new RuntimeException("Incorrect addChannel() params format", e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public static JSONObject rssFeed(final String authToken, final double lat, final double lon, final double radius) {
        final JSONObject requestParams = new JSONObject();
        try {
            requestParams.put(Params.AUTH_TOKEN, authToken);
            requestParams.put(Params.LATITUDE, lat);
            requestParams.put(Params.LONGITUDE, lon);
            requestParams.put(Params.RADIUS, radius);
            return new JSONObject(ConnectionUtils.sendPostRequest(HOST + Services.RSS_FEED, requestParams.toString()));
        } catch (JSONException e) {
            throw new RuntimeException("Incorrect rssFeed() params format", e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public static JSONObject getTimeSlotOfChannel(final String authToken, final String channelName) {
        final JSONObject requestParams = new JSONObject();
        try {
            requestParams.put(Params.AUTH_TOKEN, authToken);
            requestParams.put(Params.CHANNEL, channelName);
            return new JSONObject(ConnectionUtils.sendPostRequest(HOST + Services.GET_TIME_SLOT_OF_CHANNEL, requestParams.toString()));
        } catch (JSONException e) {
            throw new RuntimeException("Incorrect getTimeSlotOfChannel() params format", e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public static JSONObject setTimeSlotForChannel(final String authToken, final String channelName, final long timeSlotInMinutes) {
        final JSONObject requestParams = new JSONObject();
        try {
            requestParams.put(Params.AUTH_TOKEN, authToken);
            requestParams.put(Params.CHANNEL, channelName);
            requestParams.put(Params.TIME_SLOT, timeSlotInMinutes);
            return new JSONObject(ConnectionUtils.sendPostRequest(HOST + Services.SET_TIME_SLOT_FOR_CHANNEL, requestParams.toString()));
        } catch (JSONException e) {
            throw new RuntimeException("Incorrect setTimeSlotForChannel() params format", e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public static JSONObject setDefaultTimeSlotForChannel(final String authToken, final String channelName) {
        final JSONObject requestParams = new JSONObject();
        try {
            requestParams.put(Params.AUTH_TOKEN, authToken);
            requestParams.put(Params.CHANNEL, channelName);
            return new JSONObject(ConnectionUtils.sendPostRequest(HOST + Services.SET_DEFAULT_TIME_SLOT_FOR_CHANNEL, requestParams.toString()));
        } catch (JSONException e) {
            throw new RuntimeException("Incorrect setDefaultTimeSlotForChannel() params format", e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public static JSONObject getTimeSlotOfMark(final String authToken, final long markId) {
        final JSONObject requestParams = new JSONObject();
        try {
            requestParams.put(Params.AUTH_TOKEN, authToken);
            requestParams.put(Params.MARK_ID, markId);
            return new JSONObject(ConnectionUtils.sendPostRequest(HOST + Services.GET_TIME_SLOT_OF_MARK, requestParams.toString()));
        } catch (JSONException e) {
            throw new RuntimeException("Incorrect getTimeSlotOfMark() params format", e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public static JSONObject setTimeSlotForMark(final String authToken, final long markId, final long timeSlotInMinutes) {
        final JSONObject requestParams = new JSONObject();
        try {
            requestParams.put(Params.AUTH_TOKEN, authToken);
            requestParams.put(Params.MARK_ID, markId);
            requestParams.put(Params.TIME_SLOT, timeSlotInMinutes);
            return new JSONObject(ConnectionUtils.sendPostRequest(HOST + Services.SET_TIME_SLOT_FOR_MARK, requestParams.toString()));
        } catch (JSONException e) {
            throw new RuntimeException("Incorrect setTimeSlotForMark() params format", e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public static JSONObject setDefaultTimeSlotForMark(final String authToken, final long markId) {
        final JSONObject requestParams = new JSONObject();
        try {
            requestParams.put(Params.AUTH_TOKEN, authToken);
            requestParams.put(Params.MARK_ID, markId);
            return new JSONObject(ConnectionUtils.sendPostRequest(HOST + Services.SET_DEFAULT_TIME_SLOT_FOR_MARK, requestParams.toString()));
        } catch (JSONException e) {
            throw new RuntimeException("Incorrect setDefaultTimeSlotForMark() params format", e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @Deprecated
    public static JSONObject rssSession(final String authToken) {
        final JSONObject requestParams = new JSONObject();
        try {
            requestParams.put(Params.AUTH_TOKEN, authToken);
            return new JSONObject(ConnectionUtils.sendPostRequest(HOST + Services.RSS_SESSION, requestParams.toString()));
        } catch (JSONException e) {
            throw new RuntimeException("Incorrect rssSession() params format", e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @Deprecated
    public static JSONObject logout(final String authToken) {
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
