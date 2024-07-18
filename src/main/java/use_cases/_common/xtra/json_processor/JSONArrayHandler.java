package use_cases._common.xtra.json_processor;

import org.json.JSONArray;

import java.util.ArrayList;
import java.util.List;

/**
 * Overview: JSONArrayHandler is a utility interface that provide methods to handle JSON arrays.
 * Utility: It provides a default method to convert a JSONArray to a List of Strings.
 */
public interface JSONArrayHandler {

    /**
     * Converts a JSONArray to a List of Strings.
     *
     * @param jsonArray the JSONArray to be converted
     * @return a List of Strings containing the elements of the JSONArray
     */
    default List<String> JSONStringArrayToList(JSONArray jsonArray) {
        List<String> list = new ArrayList<>();
        for (int i = 0; i < jsonArray.length(); i++) {
            list.add(jsonArray.getString(i));
        }
        return list;
    }
}
