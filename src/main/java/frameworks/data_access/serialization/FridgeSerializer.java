package frameworks.data_access.serialization;

import entity.Fridge;
import entity.Ingredient;
import org.bson.Document;

import java.util.List;

public class FridgeSerializer implements Serializer<Document, Fridge>{
    IngredientSerializer ingredientSerializer = new IngredientSerializer();

    @Override
    public Document serialize(Fridge fridge) {

        Document document = new Document()
                .append("userID", fridge.getUserID())
                .append("ingredients", ingredientSerializer.serializeList(fridge.getIngredients()));

        return document;
    }

    @Override
    public Fridge deserialize(Document bson) {
        String userID = bson.getString("userID");
        List<Ingredient> ingredients =  ingredientSerializer.deserializeList(bson.getList("ingredients", Document.class));

        Fridge fridge = new Fridge(userID);
        fridge.setIngredients(ingredients);
        return fridge;
    }
}
