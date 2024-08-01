package use_cases._common.gui_common.abstractions;

import app.LocalAppSetting;
import use_cases._common.interface_adapter_common.presenter.abstractions.PropertyChangeFirer;

import java.beans.PropertyChangeListener;

public interface NightModeObject extends PropertyChangeListener {

    default void observeNight() {
        LocalAppSetting.addPropertyChangeListener(this);
    }

    default void toggleNightMode() {
        if (LocalAppSetting.isNightMode()) {setNightMode();} else {setDayMode();}
    };
    void setNightMode();
    void setDayMode();
}
