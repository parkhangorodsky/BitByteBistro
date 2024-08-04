package frameworks.gui.view_factory.division;

import app.Config;

public interface ViewFactoryDivision<E> {
    public abstract E generate(Config config);
}
