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
        public static final String TIME_SLOT = "timeSlot";
        public static final String MARK_ID = "mark_id";

        public static final String STATUS = "status";
        public static final String STATUS_DESCRIPTION = "status_description";
        public static final String CHANNELS = "channels";

        public static final String NAME = "name";
        public static final String TAGS = "tags";
    }

    public static class Services {
        public static final String SERVICE = "/service";
        public static final String LOGIN = SERVICE + "/login";
        public static final String QUIT = SERVICE + "/quit";
        public static final String CHANNELS = SERVICE + "/channels";
    }


    public static final String LOGIN_STATUS_OK = "Ok";
    public static final String LOGIN_STATUS_ERROR = "Error";
}
