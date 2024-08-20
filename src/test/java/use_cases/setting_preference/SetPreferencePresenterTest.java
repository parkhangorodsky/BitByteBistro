package use_cases.setting_preference;

import app.local.LocalAppSetting;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.*;

class SetPreferencePresenterTest {

    private SetPreferencePresenter presenter;

    @BeforeEach
    void setUp() {
        presenter = new SetPreferencePresenter();
    }

    @Test
    void testUpdateLocalAppSetting() {
        presenter.updateLocalAppSetting(true);
        assertTrue(LocalAppSetting.isNightMode());

        presenter.updateLocalAppSetting(false);
        assertFalse(LocalAppSetting.isNightMode());


    }
}
