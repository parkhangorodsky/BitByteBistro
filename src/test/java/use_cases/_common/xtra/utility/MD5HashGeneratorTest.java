package use_cases._common.xtra.utility;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class MD5HashGeneratorTest {

    @Test
    void testGenerateMD5() {
        // Test with known input-output pairs
        String input1 = "test";
        String expectedOutput1 = "098f6bcd4621d373cade4e832627b4f6";
        assertEquals(expectedOutput1, MD5HashGenerator.generateMD5(input1));

        String input2 = "CSC207";
        String expectedOutput2 = "d9020a9793c22c5d74f96d97a196720d";
        assertEquals(expectedOutput2, MD5HashGenerator.generateMD5(input2));

        String input3 = "";
        String expectedOutput3 = "d41d8cd98f00b204e9800998ecf8427e"; // MD5 hash for an empty string
        assertEquals(expectedOutput3, MD5HashGenerator.generateMD5(input3));

        // Test for consistency: MD5 should always generate the same hash for the same input
        assertEquals(expectedOutput1, MD5HashGenerator.generateMD5(input1));
        assertEquals(expectedOutput2, MD5HashGenerator.generateMD5(input2));
    }

    @Test
    void testGenerateMD5WithNullInput() {
        // Testing behavior with null input
        assertThrows(NullPointerException.class, () -> MD5HashGenerator.generateMD5(null));
    }
}

