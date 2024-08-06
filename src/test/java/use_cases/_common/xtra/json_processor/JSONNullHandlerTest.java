package use_cases._common.xtra.json_processor;

import org.json.JSONArray;
import org.json.JSONObject;
import org.junit.jupiter.api.Test;
import use_cases._common.xtra.utility.JSONNullHandler;

import static org.junit.jupiter.api.Assertions.*;

class JSONNullHandlerTest {

    JSONNullHandler jsonNullHandler = new JSONNullHandlerImpl();

    private static class JSONNullHandlerImpl implements JSONNullHandler {
    }

    @Test
    void testhandleNullString() {
        JSONObject stringJson = new JSONObject();
        stringJson.put("apple", "banana");
        stringJson.put("kiwi", JSONObject.NULL);

        assertEquals("banana", jsonNullHandler.handleNullString(stringJson, "apple"));
        assertEquals("", jsonNullHandler.handleNullString(stringJson, "kiwi"));
        assertEquals("", jsonNullHandler.handleNullString(stringJson, "bread"));
    }

    @Test
    void handleNullFloat() {
        JSONObject floatJSON = new JSONObject();
        floatJSON.put("a", 0.7f);
        floatJSON.put("b", JSONObject.NULL);

        assertEquals(0.7f, jsonNullHandler.handleNullFloat(floatJSON, "a"));
        assertEquals(0.0f, jsonNullHandler.handleNullFloat(floatJSON, "b"));
        assertEquals(0.0f, jsonNullHandler.handleNullFloat(floatJSON, "c"));
    }

    @Test
    void handleNullJSONArray() {
        JSONObject arrayJSON = new JSONObject();
        JSONArray jsonArray = new JSONArray();
        jsonArray.put("a");
        jsonArray.put("b");
        jsonArray.put("c");
        arrayJSON.put("l1", jsonArray);
        arrayJSON.put("l2", JSONObject.NULL);
        assertEquals(jsonArray, jsonNullHandler.handleNullJSONArray(arrayJSON, "l1"));
        assertTrue(jsonNullHandler.handleNullJSONArray(arrayJSON, "l2").isEmpty());
        assertTrue(jsonNullHandler.handleNullJSONArray(arrayJSON, "l3").isEmpty());
    }
}
