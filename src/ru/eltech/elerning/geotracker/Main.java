package ru.eltech.elerning.geotracker;

import org.json.JSONException;
import org.json.JSONObject;
import ru.eltech.elerning.geotracker.core.model.Channel;
import ru.eltech.elerning.geotracker.core.services.Geo2TagService;

import java.util.List;

import static ru.eltech.elerning.geotracker.core.Geo2TagConstants.*;

/**
 * Author: Kirill Korgov (korgov@yandex-team.ru)
 * Date: 12/18/11
 */
public class Main {
    public static void main(final String[] args) {
        final JSONObject loginResponse = Geo2TagService.login("Mark", "test");
        try {
            final String status = loginResponse.getString(Params.STATUS);
            if (LOGIN_STATUS_OK.equals(status)) {
                final String authToken = loginResponse.getString(Params.AUTH_TOKEN);
                System.out.println("Login successful! Your auth_token is: " + authToken);
                final List<Channel> channels = Geo2TagService.loadAvailableChannelsAsList(authToken, 30, 60, 30);
                System.out.println(channels);

            } else {
                System.out.println("Login error: " + loginResponse.getString(Params.STATUS_DESCRIPTION));
            }
        } catch (JSONException e) {
            System.out.println(e);
        }
    }
}
