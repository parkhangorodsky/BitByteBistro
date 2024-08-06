package app.config;

import use_cases.core_functionality.CoreFunctionalityController;
import use_cases.core_functionality.CoreFunctionalityInteractor;
import use_cases.core_functionality.CoreFunctionalityPresenter;

import static app.config.ViewModelConfig.myGroceryViewModel;
import static app.config.DataAccessConfig.userDAO;

class CoreFunctionalityConfig {

    static final CoreFunctionalityPresenter presenter = new CoreFunctionalityPresenter(
            myGroceryViewModel);

    static final CoreFunctionalityInteractor interactor = new CoreFunctionalityInteractor(
            presenter,
            userDAO);

    static final CoreFunctionalityController controller = new CoreFunctionalityController(
            interactor);

}
