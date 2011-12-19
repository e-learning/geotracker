package ru.eltech.elerning.geotracker.core;

/**
 * Author: Kirill Korgov (korgov@yandex-team.ru)
 * Date: 12/18/11
 */
public class Geo2TagConstants {
    public static class Params {
        public static final String LOGIN = "login";
        public static final String PASSWORD = "password";
        public static final String AUTH_TOKEN = "auth_token";
        public static final String DESCRIPTION = "description";
        public static final String ACTIVE_RADIUS = "activeRadius";
        public static final String RADIUS = "radius";
        public static final String LONGITUDE = "longitude";
        public static final String LATITUDE = "latitude";
        public static final String TIME = "time";
        public static final String TITLE = "title";
        public static final String URL = "url";
        public static final String LINK = "link";
        public static final String TIME_SLOT = "timeSlot";
        public static final String MARK_ID = "mark_id";

        public static final String STATUS = "status";
        public static final String STATUS_DESCRIPTION = "status_description";
        public static final String CHANNELS = "channels";
        public static final String CHANNEL = "channel";

        public static final String NAME = "name";
        public static final String TAGS = "tags";
    }

    public static class Services {
        public static final String SERVICE = "/service";
        public static final String LOGIN = SERVICE + "/login";
        public static final String QUIT = SERVICE + "/quit";
        public static final String CHANNELS = SERVICE + "/channels";
        public static final String SUBSCRIBED = SERVICE + "/subscribed";
        public static final String SUBSCRIBE = SERVICE + "/subscribe";
        public static final String UNSUBSCRIBE = SERVICE + "/unsubscribe";
        public static final String APPLY_MARK = SERVICE + "/apply";
        public static final String ADD_CHANNEL = SERVICE + "/addChannel";
        public static final String RSS_FEED = SERVICE + "/rss";
        public static final String RSS_SESSION = SERVICE + "/rssSession";
        public static final String ADD_USER = SERVICE + "/addUser";
        public static final String GET_TIME_SLOT_OF_CHANNEL = SERVICE + "/getTimeSlot";
        public static final String SET_TIME_SLOT_FOR_CHANNEL = SERVICE + "/setTimeSlot";
        public static final String GET_TIME_SLOT_OF_MARK = SERVICE + "/getTimeSlotMark";
        public static final String SET_TIME_SLOT_FOR_MARK = SERVICE + "/setTimeSlotMark";
        public static final String SET_DEFAULT_TIME_SLOT_FOR_CHANNEL = SERVICE + "/setDefaultTimeSlot";
        public static final String SET_DEFAULT_TIME_SLOT_FOR_MARK = SERVICE + "/setDefaultTimeSlotMark";
        public static final String SET_DATA_FOR_SESSION = SERVICE + "/setSessionPoint";
        public static final String GET_DATA_OF_SESSION = SERVICE + "/getSessionPoint";
        public static final String SET_DEFAULT_DATA_FOR_SESSION = SERVICE + "/setDefaultSessionPoint";
    }


    public static final String LOGIN_STATUS_OK = "Ok";
    public static final String LOGIN_STATUS_ERROR = "Error";
}
