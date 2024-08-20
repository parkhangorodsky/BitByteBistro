package use_cases.core_functionality.strategy.normalize;

public class CaseNormalize implements NormalizeStrategy {
    @Override
    public String normalize(String unnormalized) {
        return unnormalized.toLowerCase();
    }
}
