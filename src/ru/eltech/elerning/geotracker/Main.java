package ru.eltech.elerning.geotracker;

import org.json.JSONException;
import org.json.JSONObject;
import ru.eltech.elerning.geotracker.core.model.Channel;
import ru.eltech.elerning.geotracker.core.services.Geo2TagService;

import java.util.List;

import static ru.eltech.elerning.geotracker.core.Geo2TagConstants.LOGIN_STATUS_OK;
import static ru.eltech.elerning.geotracker.core.Geo2TagConstants.Params;

/**
 * Author: Kirill Korgov (korgov@yandex-team.ru)
 * Date: 12/18/11
 */
public class Main {
    public static void main(final String[] args) {
        try {
            final JSONObject loginResponse = Geo2TagService.login("korgov", "korgov");
            final String status = loginResponse.getString(Params.STATUS);
            if (LOGIN_STATUS_OK.equals(status)) {
                final String authToken = loginResponse.getString(Params.AUTH_TOKEN);
                System.out.println("Login successful! Your auth_token is: " + authToken);
                
//                final JSONObject addChannelResult = Geo2TagService.addChannel(authToken, "korgov channel", "korgov test channel", "https://github.com/e-learning/geotracker", 3000);
//                System.out.println(addChannelResult);
                final List<Channel> channels = Geo2TagService.loadAvailableChannelsAsList(authToken, 30, 60, 30);
                System.out.println("available channels: " + channels);


//                final JSONObject subscribeResult = Geo2TagService.subscribeChannel(authToken, "korgov channel");
//                System.out.println("subscribe result: " + subscribeResult);

//                final JSONObject applyMarkResult = Geo2TagService.applyMark(authToken, "korgov channel", "test mark", "unknown", 30, 60, new Date(), "hello world");
//                System.out.println(applyMarkResult);

                final List<Channel> subscribedChannels = Geo2TagService.loadSubscribedChannelsAsList(authToken);
                System.out.println("subscribed channels: " + subscribedChannels);

//                final JSONObject rssSessionResult = Geo2TagService.rssSession(authToken);
//                System.out.println("rssSession: " + rssSessionResult);
                final JSONObject rssFeedResult = Geo2TagService.rssFeed(authToken, 30,60, 3000);
                System.out.println("rssFeed: " + rssFeedResult);

            } else {
                System.out.println("Login error: " + loginResponse.getString(Params.STATUS_DESCRIPTION));
            }
        } catch (JSONException e) {
            System.out.println(e);
        }

//        final JSONObject addUserResult = Geo2TagService.addUser("korgov", "korgov");
//        System.out.println(addUserResult);

    }
}
