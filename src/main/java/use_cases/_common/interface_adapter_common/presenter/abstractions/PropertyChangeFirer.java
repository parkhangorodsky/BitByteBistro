package use_cases._common.interface_adapter_common.presenter.abstractions;

import java.beans.PropertyChangeListener;

public interface PropertyChangeFirer {
    void firePropertyChange(String propertyName);
    void addPropertyChangeListener(PropertyChangeListener listener);
}
