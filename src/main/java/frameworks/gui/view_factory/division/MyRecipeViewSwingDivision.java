package frameworks.gui.view_factory.division;

import app.Config;
import use_cases.add_to_my_recipe.MyRecipeView;

public class MyRecipeViewSwingDivision implements ViewFactoryDivision<MyRecipeView> {
    @Override
    public MyRecipeView generate(Config config) {
        return new MyRecipeView(config.getMyRecipeViewModel(), config.getFilterRecipeController());
    }
}
