package use_cases._common.xtra.json_processor;

import org.json.JSONArray;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class JSONArrayHandlerTest {

    private JSONArrayHandler jsonArrayHandler = new jsonArrayHandlerIMPL();

    private class jsonArrayHandlerIMPL implements JSONArrayHandler {}

    @Test
    void JSONStringArrayToList() {
        JSONArray jsonArray = new JSONArray();
        jsonArray.put("apple");
        jsonArray.put("banana");
        jsonArray.put("cocoa");
        jsonArray.put("dragonfruit");

        List<String> list = jsonArrayHandler.JSONStringArrayToList(jsonArray);

        assertNotNull(list);
        assertEquals(4, list.size());
        assertEquals("apple", list.get(0));
        assertEquals("banana", list.get(1));
        assertEquals("cocoa", list.get(2));
        assertEquals("dragonfruit", list.get(3));

    }
}
