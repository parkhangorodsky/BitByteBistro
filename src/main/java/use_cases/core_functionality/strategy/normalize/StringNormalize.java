package use_cases.core_functionality.strategy.normalize;

public class StringNormalize implements NormalizeStrategy {

    private String normalizeIngredientName(String name) {
        return name.toLowerCase().replace("-", " ").replace(" ", "");
    }

    @Override
    public String normalize(String unnormalized) {
        return normalizeIngredientName(unnormalized);
    }
}
