package use_cases._common.xtra.json_processor;

import org.json.JSONArray;
import org.json.JSONObject;

public interface JSONNullHandler {
    default String handleNullString(JSONObject json, String key) {
        return json.isNull(key) ? "" : json.getString(key);
    }
    default float handleNullFloat(JSONObject json, String key) {
        return json.isNull(key) ? 0.0f : json.getFloat(key);
    }
    default JSONArray handleNullJSONArray(JSONObject json, String key) {
        return json.isNull(key) ? new JSONArray() : json.getJSONArray(key);
    }
}
