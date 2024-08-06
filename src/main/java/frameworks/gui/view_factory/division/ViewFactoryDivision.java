package frameworks.gui.view_factory.division;

import app.config.Config;

public interface ViewFactoryDivision<E> {
    public abstract E generate(Config config);
}
