package use_cases._common.gui_common.abstractions;

import java.util.Arrays;
import java.util.stream.Collectors;

public interface StringCaseEditor {

    default String capitalizeWords(String input) {
        return Arrays.stream(input.split("\\s+"))
                .map(word -> word.substring(0, 1).toUpperCase() + word.substring(1))
                .collect(Collectors.joining(" "));
    }
}
