package use_cases.setting_preference;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class SetPreferenceInputDataTest {

    @Test
    void testConstructorAndGetterNightMode() {
        // Given
        boolean testIsNightMode = true;

        // When
        SetPreferenceInputData inputData = new SetPreferenceInputData(testIsNightMode, false);

        // Then
        assertEquals(testIsNightMode, inputData.getNightMode(), "The isNightMode value should be correctly initialized and retrieved");
    }

    @Test
    void testConstructorAndGetterFridge() {
        // Given
        boolean testFridgeSubtract = true;

        // When
        SetPreferenceInputData inputData = new SetPreferenceInputData(false, testFridgeSubtract);

        // Then
        assertEquals(testFridgeSubtract, inputData.getSubtractFridgeFromGrocery(), "The isNightMode value should be correctly initialized and retrieved");
    }
}
