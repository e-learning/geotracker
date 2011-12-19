package ru.eltech.elerning.geotracker.core.services;

import org.json.JSONArray;
import org.json.JSONObject;
import ru.eltech.elerning.geotracker.core.Geo2TagConstants;
import ru.eltech.elerning.geotracker.core.model.Channel;
import ru.eltech.elerning.geotracker.util.CollectionUtils;
import ru.eltech.elerning.geotracker.util.JSONUtils;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Author: Kirill Korgov (korgov@yandex-team.ru)
 * Date: 12/19/11
 */
public class Geo2TagChannelHelper {
    public static List<Channel> wrapChannels(final JSONArray channels) {
        if (channels != null) {
            final int length = channels.length();
            final List<Channel> out = new ArrayList<Channel>(length);
            for (int i = 0; i < length; i++) {
                CollectionUtils.addIfNotNull(out, wrapChannel(channels.optJSONObject(i)));
            }
            return out;
        }
        return Collections.emptyList();
    }

    public static Channel wrapChannel(final JSONObject channel) {
        if (channel != null) {
            return new Channel(
                    JSONUtils.asStringList(channel.optJSONArray(Geo2TagConstants.Params.TAGS)),
                    channel.optString(Geo2TagConstants.Params.NAME, ""),
                    channel.optString(Geo2TagConstants.Params.DESCRIPTION, "")
            );
        }
        return null;
    }
}
