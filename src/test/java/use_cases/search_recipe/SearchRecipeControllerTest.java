//package use_cases;
//
//import org.junit.jupiter.api.BeforeEach;
//import org.junit.jupiter.api.Test;
//import use_cases.search_recipe.interface_adapter.controller.SearchRecipeController;
//import use_cases.search_recipe.use_case.input_data.SearchRecipeInputData;
//import use_cases.search_recipe.use_case.interactor.SearchRecipeInputBoundary;
//
//import java.util.Arrays;
//
//import static org.mockito.Mockito.*;
//
//public class SearchRecipeControllerTest {
//
//    private SearchRecipeInputBoundary interactor;
//    private SearchRecipeController controller;
//
//    @BeforeEach
//    public void setUp() {
//        interactor = mock(SearchRecipeInputBoundary.class);
//        controller = new SearchRecipeController(interactor);
//    }
//
//    @Test
//    public void testExecuteBasicSearch() {
//        String queryString = "Pasta";
//
//        controller.execute(queryString);
//
//        verify(interactor).execute(new SearchRecipeInputData(queryString));
//    }
//
//    @Test
//    public void testExecuteAdvancedSearch() {
//        String queryString = "Pasta";
//        controller.execute(queryString,
//                Arrays.asList("nuts"), Arrays.asList("vegan"), Arrays.asList("low-sugar"),
//                Arrays.asList("italian"), Arrays.asList("main course"), Arrays.asList("dinner"));
//
//        verify(interactor).execute(new SearchRecipeInputData(
//                queryString,
//                Arrays.asList("nuts"), Arrays.asList("vegan"), Arrays.asList("low-sugar"),
//                Arrays.asList("italian"), Arrays.asList("main course"), Arrays.asList("dinner")));
//    }
//}
