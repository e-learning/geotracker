package ru.eltech.elerning.geotracker.util;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Author: Kirill Korgov (korgov@yandex-team.ru)
 * Date: 12/18/11
 */
public class JSONUtils {
    public static List<String> asStringList(final JSONArray jsonArray){
        if(jsonArray != null){
            final int length = jsonArray.length();
            final List<String> out = new ArrayList<String>(length);
            for(int j = 0; j < length; j++){
                CollectionUtils.addIfNotNull(out, jsonArray.optString(j));
            }
            return out;
        }
        return Collections.emptyList();
    }

    public static List<JSONObject> asJSONObjectList(final JSONArray jsonArray){
        if(jsonArray != null){
            final int length = jsonArray.length();
            final List<JSONObject> out = new ArrayList<JSONObject>(length);
            for(int j = 0; j < length; j++){
                CollectionUtils.addIfNotNull(out, jsonArray.optJSONObject(j));
            }
            return out;
        }
        return Collections.emptyList();
    }

    public static List<JSONArray> asJSONArrayList(final JSONArray jsonArray){
        if(jsonArray != null){
            final int length = jsonArray.length();
            final List<JSONArray> out = new ArrayList<JSONArray>(length);
            for(int j = 0; j < length; j++){
                CollectionUtils.addIfNotNull(out, jsonArray.optJSONArray(j));
            }
            return out;
        }
        return Collections.emptyList();
    }
}
