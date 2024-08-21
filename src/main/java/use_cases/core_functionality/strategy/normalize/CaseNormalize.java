package use_cases.core_functionality.strategy.normalize;

/**
 * The `CaseNormalize` class implements the `NormalizeStrategy` interface and
 * provides a normalization strategy that converts ingredient names to lowercase.
 * This strategy ensures that ingredient names are compared in a case-insensitive manner,
 * making the comparison more robust to variations in capitalization.
 *
 * This is useful when ingredient names might be listed with different capitalizations,
 * ensuring consistency in how ingredients are handled and compared.
 */
public class CaseNormalize implements NormalizeStrategy {

    /**
     * Normalizes the given unnormalized string by converting it to lowercase.
     * This ensures that the string comparison is case-insensitive.
     *
     * @param unnormalized The string to be normalized.
     * @return The normalized string in lowercase.
     */
    @Override
    public String normalize(String unnormalized) {
        return unnormalized.toLowerCase();
    }
}
