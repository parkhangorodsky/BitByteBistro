package use_cases._common.xtra.utility;

import org.json.JSONArray;
import org.json.JSONObject;

/**
 * Overview: JSONNullHandler interface provides utility methods to handle JSON objects
 * that may contain null values.
 * Utility: It includes default methods to handle null values
 * for Strings, floats, and JSONArrays.
 */
public class JSONNullHandler {

    /**
     * Returns the value of the specified key as a String from the given JSONObject.
     * If the key is null, it returns an empty string.
     *
     * @param json the JSONObject to retrieve the value from
     * @param key  the key whose associated value is to be returned
     * @return the value as a String, or an empty string if the key is null
     */
    static String handleNullString(JSONObject json, String key) {
        return json.isNull(key) ? "" : json.getString(key);
    }

    /**
     * Returns the value of the specified key as a float from the given JSONObject.
     * If the key is null, it returns 0.0f.
     *
     * @param json the JSONObject to retrieve the value from
     * @param key  the key whose associated value is to be returned
     * @return the value as a float, or 0.0f if the key is null
     */
    static float handleNullFloat(JSONObject json, String key) {
        return json.isNull(key) ? 0.0f : json.getFloat(key);
    }

    /**
     * Returns the value of the specified key as a JSONArray from the given JSONObject.
     * If the key is null, it returns an empty JSONArray.
     *
     * @param json the JSONObject to retrieve the value from
     * @param key  the key whose associated value is to be returned
     * @return the value as a JSONArray, or an empty JSONArray if the key is null
     */
    static JSONArray handleNullJSONArray(JSONObject json, String key) {
        return json.isNull(key) ? new JSONArray() : json.getJSONArray(key);
    }
}
