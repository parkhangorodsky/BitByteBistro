package use_cases.search_recipe.interface_adapter.view_model;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import use_cases._common.interface_adapter_common.view_model.abstractions.ViewModel;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;

class AdvancedSearchRecipeViewModelTest {
    @Test
    void testConstructor() {
        AdvancedSearchRecipeViewModel viewModel = new AdvancedSearchRecipeViewModel();
        assertNotNull(viewModel);
    }
}
