package frameworks.data_access.serialization;

import entity.Ingredient;
import frameworks.data_access.serialization.IngredientSerializer;
import org.bson.Document;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class IngredientSerializerTest {
    private IngredientSerializer serializer;
    private Ingredient testIngredient;
    private Document testDocument;

    @BeforeEach
    void setUp() {
        serializer = new IngredientSerializer();

        testIngredient = new Ingredient(
                "ing-001",
                "Tomato",
                "kg",
                "Vegetable",
                5.0d
        );

        testDocument = new Document()
                .append("ingredientID", "ing-001")
                .append("ingredientName", "Tomato")
                .append("quantity", 5.0d)
                .append("quantityUnit", "kg")
                .append("category", "Vegetable");
    }

    @Test
    void testSerialize() {
        Document serializedDocument = serializer.serialize(testIngredient);

        assertNotNull(serializedDocument, "Serialized document should not be null");

        assertEquals(testIngredient.getIngredientID(), serializedDocument.getString("ingredientID"));
        assertEquals(testIngredient.getIngredientName(), serializedDocument.getString("ingredientName"));
        assertEquals(testIngredient.getQuantity(), serializedDocument.getDouble("quantity").floatValue());
        assertEquals(testIngredient.getQuantityUnit(), serializedDocument.getString("quantityUnit"));
        assertEquals(testIngredient.getCategory(), serializedDocument.getString("category"));
    }

    @Test
    void testDeserialize() {
        Ingredient deserializedIngredient = serializer.deserialize(testDocument);

        assertNotNull(deserializedIngredient, "Deserialized ingredient should not be null");

        assertEquals("ing-001", deserializedIngredient.getIngredientID());
        assertEquals("Tomato", deserializedIngredient.getIngredientName());
        assertEquals(5.0f, deserializedIngredient.getQuantity());
        assertEquals("kg", deserializedIngredient.getQuantityUnit());
        assertEquals("Vegetable", deserializedIngredient.getCategory());
    }

    @Test
    void testSerializeList() {
        List<Ingredient> ingredientList = new ArrayList<>();
        ingredientList.add(testIngredient);
        ingredientList.add(new Ingredient("ing-002", "Cheese", "grams", "Dairy", 200));

        List<Document> serializedList = serializer.serializeList(ingredientList);

        assertNotNull(serializedList, "Serialized list should not be null");
        assertEquals(2, serializedList.size(), "Serialized list size should be 2");

        Document firstDocument = serializedList.get(0);
        Document secondDocument = serializedList.get(1);

        assertEquals("ing-001", firstDocument.getString("ingredientID"));
        assertEquals("Tomato", firstDocument.getString("ingredientName"));
        assertEquals(5.0f,  firstDocument.getDouble("quantity").floatValue());
        assertEquals("kg", firstDocument.getString("quantityUnit"));
        assertEquals("Vegetable", firstDocument.getString("category"));

        assertEquals("ing-002", secondDocument.getString("ingredientID"));
        assertEquals("Cheese", secondDocument.getString("ingredientName"));
        assertEquals(200, secondDocument.getDouble("quantity").floatValue());
        assertEquals("grams", secondDocument.getString("quantityUnit"));
        assertEquals("Dairy", secondDocument.getString("category"));
    }

    @Test
    void testDeserializeList() {
        List<Document> documentList = new ArrayList<>();
        documentList.add(testDocument);
        documentList.add(new Document()
                .append("ingredientID", "ing-002")
                .append("ingredientName", "Cheese")
                .append("quantity", 200.0d)
                .append("quantityUnit", "grams")
                .append("category", "Dairy"));

        List<Ingredient> deserializedList = serializer.deserializeList(documentList);

        assertNotNull(deserializedList, "Deserialized list should not be null");
        assertEquals(2, deserializedList.size(), "Deserialized list size should be 2");

        Ingredient firstIngredient = deserializedList.get(0);
        Ingredient secondIngredient = deserializedList.get(1);

        assertEquals("ing-001", firstIngredient.getIngredientID());
        assertEquals("Tomato", firstIngredient.getIngredientName());
        assertEquals(5.0d, firstIngredient.getQuantity());
        assertEquals("kg", firstIngredient.getQuantityUnit());
        assertEquals("Vegetable", firstIngredient.getCategory());

        assertEquals("ing-002", secondIngredient.getIngredientID());
        assertEquals("Cheese", secondIngredient.getIngredientName());
        assertEquals(200.0d, secondIngredient.getQuantity());
        assertEquals("grams", secondIngredient.getQuantityUnit());
        assertEquals("Dairy", secondIngredient.getCategory());
    }

    @Test
    void testSerializeEmptyList() {
        List<Ingredient> emptyIngredientList = new ArrayList<>();
        List<Document> serializedList = serializer.serializeList(emptyIngredientList);

        assertNotNull(serializedList, "Serialized list should not be null");
        assertTrue(serializedList.isEmpty(), "Serialized list should be empty");
    }

    @Test
    void testDeserializeEmptyList() {
        List<Document> emptyDocumentList = new ArrayList<>();
        List<Ingredient> deserializedList = serializer.deserializeList(emptyDocumentList);

        assertNotNull(deserializedList, "Deserialized list should not be null");
        assertTrue(deserializedList.isEmpty(), "Deserialized list should be empty");
    }
}
