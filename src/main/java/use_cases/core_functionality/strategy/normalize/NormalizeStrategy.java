package use_cases.core_functionality.strategy.normalize;

/**
 * The `NormalizeStrategy` interface defines a contract for normalizing strings.
 * Implementations of this interface provide various strategies to transform
 * unnormalized strings into a consistent format.
 *
 * Normalization is useful for ensuring that comparisons and operations on strings
 * are consistent and not affected by variations in formatting, capitalization,
 * or other differences. This is particularly important when dealing with data that
 * might have inconsistencies or variations.
 */
public interface NormalizeStrategy {

    /**
     * Normalizes the given unnormalized string according to the specific strategy.
     * The normalization process transforms the input string into a consistent format,
     * making it suitable for comparison and other operations.
     *
     * @param unnormalized The string that needs to be normalized.
     * @return The normalized string in a consistent format.
     */
    String normalize(String unnormalized);
}
