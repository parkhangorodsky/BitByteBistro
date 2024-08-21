package use_cases._common.xtra.utility;

import org.junit.jupiter.api.Test;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class StringEditorTest {

    @Test
    void testOptionStringBuilder() {
        List<String> options = Arrays.asList("option1", "option2", "option3");
        String type = "type";
        String expected = "&type=option1&type=option2&type=option3";
        assertEquals(expected, StringEditor.optionStringBuilder(options, type));

        // Test with an empty list of options
        List<String> emptyOptions = Arrays.asList();
        String expectedEmpty = "";
        assertEquals(expectedEmpty, StringEditor.optionStringBuilder(emptyOptions, type));

        // Test with a single option
        List<String> singleOption = Arrays.asList("singleOption");
        String expectedSingle = "&type=singleOption";
        assertEquals(expectedSingle, StringEditor.optionStringBuilder(singleOption, type));
    }

    @Test
    void testCapitalizeWords() {
        String input = "hello world";
        String expected = "Hello World";
        assertEquals(expected, StringEditor.capitalizeWords(input));

        // Test with a single word
        String inputSingleWord = "java";
        String expectedSingleWord = "Java";
        assertEquals(expectedSingleWord, StringEditor.capitalizeWords(inputSingleWord));

        // Test with null input
        assertThrows(NullPointerException.class, () -> StringEditor.capitalizeWords(null));
    }

    @Test
    void testEscapeWords() {
        String input = "This is a test to escape words based on a certain length.";
        String expected = "<html>This is a test to escape words <br>basedon a certain length. </html>";
        assertEquals(expected, StringEditor.escapeWords(input, 20));

        // Test with shorter length
        String expectedShorterLength = "<html>This is a test to escape <br>wordsbased on a certain <br>length.</html>";
        assertEquals(expectedShorterLength, StringEditor.escapeWords(input, 15));

        // Test with longer length (no line breaks)
        String expectedLongerLength = "<html>This is a test to escape words based on a certain length. </html>";
        assertEquals(expectedLongerLength, StringEditor.escapeWords(input, 50));
    }

    @Test
    void testShortenString() {
        String input = "This is a test to shorten the string.";
        String expected = "This is a test to the ...";
        assertEquals(expected, StringEditor.shortenString(input, 25));

        // Test with exact length (no ellipsis)
        String expectedExactLength = "This is a test to shorten the ";
        assertEquals(expectedExactLength, StringEditor.shortenString(input, input.length()));

        // Test with shorter string (no ellipsis)
        String expectedShorterString = "This is a ...";
        assertEquals(expectedShorterString, StringEditor.shortenString(input, 10));

        // Test with length longer than input string
        assertEquals("This is a test to shorten the string. ", StringEditor.shortenString(input, 100));
    }
}

