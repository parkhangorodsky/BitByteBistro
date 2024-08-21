package use_cases.core_functionality.strategy.normalize;

/**
 * The `StringNormalize` class implements the `NormalizeStrategy` interface and
 * provides a normalization strategy that converts strings to lowercase, replaces
 * hyphens with spaces, and then removes all spaces. This strategy is designed to
 * ensure that strings are compared in a consistent format by eliminating variations
 * caused by capitalization, hyphens, and spaces.
 *
 * This approach is useful for standardizing ingredient names or other strings where
 * such variations might occur, ensuring uniformity in comparisons and processing.
 */
public class StringNormalize implements NormalizeStrategy {

    /**
     * Normalizes the given unnormalized string by applying the following transformations:
     * <ol>
     *     <li>Converts the string to lowercase.</li>
     *     <li>Replaces hyphens ("-") with spaces.</li>
     *     <li>Removes all spaces from the string.</li>
     * </ol>
     * The resulting string is returned in a consistent format, suitable for comparison and
     * other operations.
     *
     * @param unnormalized The string that needs to be normalized.
     * @return The normalized string with lowercase characters and no spaces or hyphens.
     */
    @Override
    public String normalize(String unnormalized) {
        return unnormalized.toLowerCase().replace("-", " ").replace(" ", "");
    }
}
