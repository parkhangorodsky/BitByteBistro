package use_case.interactor;

import org.json.JSONArray;
import org.json.JSONObject;

public interface JsonNullHandler {
    public default String handleNullString(JSONObject json, String key) {
        return json.isNull(key) ? "" : json.getString(key);
    }
    public default float handleNullFloat(JSONObject json, String key) {
        return json.isNull(key) ? 0.0f : json.getFloat(key);
    }
    public default JSONArray handleNullJSONArray(JSONObject json, String key) {
        return json.isNull(key) ? new JSONArray() : json.getJSONArray(key);
    }
}
