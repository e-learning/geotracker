package ru.eltech.elerning.geotracker.core.model;

import org.json.JSONArray;
import org.json.JSONObject;
import ru.eltech.elerning.geotracker.util.JSONUtils;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static ru.eltech.elerning.geotracker.util.CollectionUtils.addIfNotNull;

/**
 * Author: Kirill Korgov (korgov@yandex-team.ru)
 * Date: 12/22/11
 */
public class RssResult {

    private Map<String, List<JSONObject>> channelToTags = new HashMap<String, List<JSONObject>>();

    public RssResult(final JSONObject rssResult) {
        final JSONObject rss = rssResult.optJSONObject("rss");
        if(rss != null){
            final JSONObject channelsObj = rss.optJSONObject("channels");
            if(channelsObj != null){
                final List<JSONObject> items = JSONUtils.asJSONObjectList(channelsObj.optJSONArray("items"));
                for (final JSONObject item : items) {
                    final JSONArray subItemsAsArray = item.optJSONArray("items");
                    final String channelName = item.optString("name");
                    final List<JSONObject> tags = JSONUtils.asJSONObjectList(subItemsAsArray);
                    addIfNotNull(channelToTags, channelName, tags);
                }
            }
        }
    }

    public Map<String, List<JSONObject>> getChannelToTags() {
        return channelToTags;
    }
}
