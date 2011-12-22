package ru.eltech.elerning.geotracker.core.services;

import org.json.JSONObject;
import ru.eltech.elerning.geotracker.core.Geo2TagConstants;
import ru.eltech.elerning.geotracker.core.model.Channel;
import ru.eltech.elerning.geotracker.core.model.LoginResult;import ru.eltech.elerning.geotracker.core.model.RssResult;

import java.util.List;

/**
 * Author: Kirill Korgov (korgov@yandex-team.ru)
 * Date: 12/22/11
 */
public class Geo2TagService extends BaseGeo2TagService{
    public static LoginResult loginAndWrapResult(final String userLogin, final String userPassword){
        return new LoginResult(login(userLogin, userPassword));
    }

    public static LoginResult addUserAndWrapResult(final String userLogin, final String userPassword){
        return new LoginResult(addUser(userLogin, userPassword));
    }

    public static List<Channel> loadAvailableChannelsAsList(final String authToken, final double lat, final double lon, final double radius) {
        return Geo2TagChannelHelper.wrapChannels(loadAvailableChannels(authToken, lat, lon, radius).optJSONArray(Geo2TagConstants.Params.CHANNELS));
    }

    public static List<Channel> loadSubscribedChannelsAsList(final String authToken) {
        return Geo2TagChannelHelper.wrapChannels(loadSubscribedChannels(authToken).optJSONArray(Geo2TagConstants.Params.CHANNELS));
    }

    public static RssResult wrapedRssFeed(final String authToken, final double lat, final double lon, final double radius) {
        return new RssResult(rssFeed(authToken, lat, lon, radius));
    }

}
