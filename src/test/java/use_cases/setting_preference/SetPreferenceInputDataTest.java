package use_cases.setting_preference;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class SetPreferenceInputDataTest {

    @Test
    void testConstructorAndGetter() {
        // Given
        boolean testIsNightMode = true;

        // When
        SetPreferenceInputData inputData = new SetPreferenceInputData(testIsNightMode);

        // Then
        assertEquals(testIsNightMode, inputData.getNightMode(), "The isNightMode value should be correctly initialized and retrieved");
    }
}
