package frameworks.data_access.serialization;

import entity.Nutrition;
import org.bson.Document;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Map;
import java.util.TreeMap;

import static org.junit.jupiter.api.Assertions.*;

class NutritionSerializerTest {

    private NutritionSerializer serializer;
    private Nutrition testNutrition;
    private Document testDocument;

    @BeforeEach
    void setUp() {
        serializer = new NutritionSerializer();

        testNutrition = new Nutrition(
                "Calories",
                200.0f,
                "kcal",
                10.0f
        );

        testDocument = new Document()
                .append("label", "Calories")
                .append("unit", "kcal")
                .append("quantity", 200.0d)
                .append("percentage", 10.0d);
    }

    @Test
    void testSerialize() {
        Document serializedDocument = serializer.serialize(testNutrition);

        assertNotNull(serializedDocument, "Serialized document should not be null");

        assertEquals("Calories", serializedDocument.getString("label"));
        assertEquals("kcal", serializedDocument.getString("unit"));
        assertEquals(200.0f, serializedDocument.get("quantity"));
        assertEquals(10.0f, serializedDocument.get("percentage"));
    }

    @Test
    void testDeserialize() {
        Nutrition deserializedNutrition = serializer.deserialize(testDocument);

        assertNotNull(deserializedNutrition, "Deserialized nutrition should not be null");

        assertEquals("Calories", deserializedNutrition.getLabel());
        assertEquals("kcal", deserializedNutrition.getUnit());
        assertEquals(200.0f, deserializedNutrition.getQuantity());
        assertEquals(10.0f, deserializedNutrition.getPercentage());
    }

    @Test
    void testSerializeMap() {
        Map<String, Nutrition> nutritionMap = new TreeMap<>();
        nutritionMap.put("Calories", testNutrition);
        nutritionMap.put("Protein", new Nutrition("Protein", 15.0f, "g"));

        Document serializedDocument = serializer.serializeMap(nutritionMap);

        assertNotNull(serializedDocument, "Serialized document should not be null");
        assertEquals(2, serializedDocument.size(), "Serialized document size should be 2");

        Document caloriesDoc = serializedDocument.get("Calories", Document.class);
        Document proteinDoc = serializedDocument.get("Protein", Document.class);

        assertNotNull(caloriesDoc, "Calories document should not be null");
        assertEquals("Calories", caloriesDoc.getString("label"));
        assertEquals(200.0f, caloriesDoc.get("quantity"));

        assertNotNull(proteinDoc, "Protein document should not be null");
        assertEquals("Protein", proteinDoc.getString("label"));
        assertEquals(15.0f, proteinDoc.get("quantity"));
    }

    @Test
    void testDeserializeMap() {
        Document nutritionDoc = new Document()
                .append("Calories", testDocument)
                .append("Protein", new Document()
                        .append("label", "Protein")
                        .append("unit", "g")
                        .append("quantity", 15.0));

        Map<String, Nutrition> deserializedMap = serializer.deserializeMap(nutritionDoc);

        assertNotNull(deserializedMap, "Deserialized map should not be null");
        assertEquals(2, deserializedMap.size(), "Deserialized map size should be 2");

        Nutrition caloriesNutrition = deserializedMap.get("Calories");
        Nutrition proteinNutrition = deserializedMap.get("Protein");

        assertEquals("Calories", caloriesNutrition.getLabel());
        assertEquals(200.0f, caloriesNutrition.getQuantity());
        assertEquals(10.0f, caloriesNutrition.getPercentage());

        assertEquals("Protein", proteinNutrition.getLabel());
        assertEquals(15.0f, proteinNutrition.getQuantity());
        assertNull(proteinNutrition.getPercentage(), "Protein percentage should be null");
    }

}
