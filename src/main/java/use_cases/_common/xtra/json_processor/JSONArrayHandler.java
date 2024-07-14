package use_cases._common.xtra.json_processor;

import org.json.JSONArray;

import java.util.ArrayList;
import java.util.List;

public interface JSONArrayHandler {
    default List<String> JSONStringArrayToList(JSONArray jsonArray) {
        List<String> list = new ArrayList<>();
        for (int i = 0; i < jsonArray.length(); i++) {
            list.add(jsonArray.getString(i));
        }
        return list;
    }
}
