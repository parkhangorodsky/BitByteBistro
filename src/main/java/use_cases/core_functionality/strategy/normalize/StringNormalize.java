package use_cases.core_functionality.strategy.normalize;

public class StringNormalize implements NormalizeStrategy {
    @Override
    public String normalize(String unnormalized) {
        return unnormalized.toLowerCase().replace("-", " ").replace(" ", "");
    }
}
