package use_cases._common.xtra.utility;

import org.json.JSONArray;
import org.json.JSONObject;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;
import static use_cases._common.xtra.utility.JSONNullHandler.*;

class JSONNullHandlerTest {


    @Test
    void testHandleNullString() {
        JSONObject stringJson = new JSONObject();
        stringJson.put("apple", "banana");
        stringJson.put("kiwi", JSONObject.NULL);

        assertEquals("banana", handleNullString(stringJson, "apple"));
        assertEquals("", handleNullString(stringJson, "kiwi"));
        assertEquals("", handleNullString(stringJson, "bread"));
    }

    @Test
    void testHandleNullFloat() {
        JSONObject floatJSON = new JSONObject();
        floatJSON.put("a", 0.7f);
        floatJSON.put("b", JSONObject.NULL);

        assertEquals(0.7f, handleNullFloat(floatJSON, "a"));
        assertEquals(0.0f, handleNullFloat(floatJSON, "b"));
        assertEquals(0.0f, handleNullFloat(floatJSON, "c"));
    }

    @Test
    void testHandleNullJSONArray() {
        JSONObject arrayJSON = new JSONObject();
        JSONArray jsonArray = new JSONArray();
        jsonArray.put("a");
        jsonArray.put("b");
        jsonArray.put("c");
        arrayJSON.put("l1", jsonArray);
        arrayJSON.put("l2", JSONObject.NULL);
        assertEquals(jsonArray, handleNullJSONArray(arrayJSON, "l1"));
        assertTrue(handleNullJSONArray(arrayJSON, "l2").isEmpty());
        assertTrue(handleNullJSONArray(arrayJSON, "l3").isEmpty());
    }
}
