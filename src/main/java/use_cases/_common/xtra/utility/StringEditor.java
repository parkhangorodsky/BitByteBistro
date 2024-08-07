package use_cases._common.xtra.utility;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Overview: Utility interface for performing operations on strings related to case formatting.
 * Utility: Provides default methods for transforming the case of strings.
 */
public class StringEditor {

    /**
     * Builds a concatenated string of options with the given type as a prefix for each option.
     *
     * @param options the list of options to be included in the string
     * @param type the prefix to be added before each option
     * @return a concatenated string of options with the type as a prefix for each option
     */
    public static String optionStringBuilder(List<String> options, String type) {
        return options.stream().map(choice -> "&" + type + "=" + choice)
                .collect(Collectors.joining());
    }

    /**
     * Capitalizes the first letter of each word in the provided string.
     * Words are assumed to be separated by whitespace.
     *
     * @param input the string to be transformed.
     * @return a new string with each word's first letter capitalized.
     *         If the input is null or empty, it returns the input unchanged.
     * @throws NullPointerException if the input is null.
     */
    public static String capitalizeWords(String input) {
         // If the input is null, throw NullPointerException
        if (input == null) {
            throw new NullPointerException("Input string cannot be null.");
        } else {

            return Arrays.stream(input.split("\\s+")) // split word based on white spaces.
                    .map(word -> word.substring(0, 1).toUpperCase() + word.substring(1)) // Capitalize each word.
                    .collect(Collectors.joining(" ")); // Join capitalized words together.
        }
    }


}
