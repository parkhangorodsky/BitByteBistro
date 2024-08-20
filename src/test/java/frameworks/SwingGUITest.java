//package frameworks;
//
//import app.config.Config;
//import frameworks.gui.SwingGUI;
//import frameworks.gui.view_factory.SwingViewFactory;
//import use_cases._common.authentication.AuthenticationViewManager;
//import use_cases._common.authentication.AuthenticationViewModel;
//import use_cases._common.interface_adapter_common.view_model.models.ViewManagerModel;
//import use_cases._common.gui_common.view.AppViewManager;
//import use_cases.logout.interface_adapter.controller.LogoutController;
//import use_cases.setting_preference.PreferenceView;
//
//import javax.swing.*;
//import java.awt.*;
//import java.beans.PropertyChangeEvent;
//
//import org.junit.jupiter.api.BeforeEach;
//import org.junit.jupiter.api.Test;
//
//import static org.mockito.Mockito.*;
//
//class SwingGUITest {
//
//    private SwingGUI swingGUI;
//    private Config config;
//    private ViewManagerModel viewManagerModel;
//    private AuthenticationViewModel authenticationViewModel;
//    private SwingViewFactory viewFactory;
//    private AuthenticationViewManager authenticationViewManager;
//    private AppViewManager appViewManager;
//    private JFrame mainFrame;
//
//    @BeforeEach
//    void setUp() {
//        swingGUI = new SwingGUI();
//
//        // Mock dependencies
//        config = mock(Config.class);
//        viewManagerModel = mock(ViewManagerModel.class);
//        authenticationViewModel = mock(AuthenticationViewModel.class);
//        viewFactory = mock(SwingViewFactory.class);
//        authenticationViewManager = mock(AuthenticationViewManager.class);
//        appViewManager = mock(AppViewManager.class);
//        mainFrame = mock(JFrame.class);
//
//        when(config.getViewManagerModel()).thenReturn(viewManagerModel);
//        when(config.getAuthenticationViewModel()).thenReturn(authenticationViewModel);
//        when(config.getLogoutController()).thenReturn((LogoutController) mock(Runnable.class));
//
//        swingGUI.initialize(config);
//        swingGUI.run();
//    }
//
//    @Test
//    void testInitialize() {
//        // Check if components are initialized correctly
//        verify(authenticationViewModel, times(1)).addPropertyChangeListener(any());
//    }
//
//    @Test
//    void testRun() {
//        // Verify if login view is generated and shown
//        verify(authenticationViewManager, times(1)).addView(any());
//        verify(viewManagerModel, times(1)).setActiveView("LoginView");
//        verify(viewManagerModel, times(1)).firePropertyChanged();
//    }
//
//    @Test
//    void testPropertyChangeAuthenticationSuccess() {
//        PropertyChangeEvent evt = new PropertyChangeEvent(this, "authenticationSuccess", null, null);
//        swingGUI.propertyChange(evt);
//
//        // Verify if login view is disposed and app view is generated and shown
//        verify(viewManagerModel, times(1)).setActiveView("Home");
//        verify(viewManagerModel, times(1)).firePropertyChanged();
//    }
//
//    @Test
//    void testPropertyChangeLogoutSuccess() {
//        PropertyChangeEvent evt = new PropertyChangeEvent(this, "logoutSuccess", null, null);
//        swingGUI.propertyChange(evt);
//
//        // Verify if app view is disposed and login view is generated and shown
//        verify(viewManagerModel, times(1)).setActiveView("LoginView");
//        verify(viewManagerModel, times(1)).firePropertyChanged();
//    }
//
//    @Test
//    void testInitializeMainFrame() {
//        swingGUI.initializeMainFrame();
//
//        // Verify if main frame is set up correctly
//        verify(mainFrame, times(1)).setSize(1000, 750);
//        verify(mainFrame, times(1)).setLayout(any(BorderLayout.class));
//        verify(mainFrame, times(1)).setResizable(true);
//        verify(mainFrame, times(1)).setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
//        verify(mainFrame, times(1)).setTitle("");
//        verify(mainFrame, times(1)).setIconImage(any());
//        verify(mainFrame, times(1)).getContentPane().setBackground(new Color(238, 237, 227));
//        verify(mainFrame, times(1)).setLocationRelativeTo(null);
//
//        if (System.getProperty("os.name").equals("Mac OS X")) {
//            verify(mainFrame.getRootPane(), times(1)).putClientProperty("apple.awt.fullWindowContent", true);
//            verify(mainFrame.getRootPane(), times(1)).putClientProperty("apple.awt.transparentTitleBar", true);
//        }
//
//        verify(mainFrame, times(1)).add(any(JPanel.class));
//    }
//
//    @Test
//    void testGenerateLoginView() {
//        swingGUI.generateLoginView();
//
//        // Verify if login and signup views are added to authentication view manager
//        verify(authenticationViewManager, times(2)).addView(any());
//    }
//
//    @Test
//    void testShowLoginView() {
//        swingGUI.showLoginView();
//
//        // Verify if login view is set active and shown
//        verify(viewManagerModel, times(1)).setActiveView("LoginView");
//        verify(viewManagerModel, times(1)).firePropertyChanged();
//        verify(mainFrame, times(1)).setVisible(true);
//    }
//
//    @Test
//    void testInitializeAppViews() {
//        swingGUI.initializeAppViews();
//
//        // Verify if app view manager is initialized
//        verify(appViewManager, times(1)).addView(any());
//    }
//
//    @Test
//    void testGenerateAppViews() {
//        swingGUI.generateAppViews();
//
//        // Verify if app views and sidebar are added to app view manager
//        verify(appViewManager, times(5)).addView(any());
//        verify(appViewManager, times(1)).addPopupView(eq("Preference"), any(PreferenceView.class));
//    }
//
//    @Test
//    void testShowAppView() {
//        swingGUI.showAppView();
//
//        // Verify if app view is set active and shown
//        verify(viewManagerModel, times(1)).setActiveView("Home");
//        verify(viewManagerModel, times(1)).firePropertyChanged();
//        verify(mainFrame, times(1)).setVisible(true);
//    }
////
////    @Test
////    void testDisposeLoginView() {
////        swingGUI.disposeLoginView();
////
////        // Verify if login panel is cleared
////        verify(loginPanel, times(1)).removeAll();
////    }
////
////    @Test
////    void testDisposeAppViews() {
////        swingGUI.disposeAppViews();
////
////        // Verify if app views are cleared
////        verify(appViewManager, times(1)).removePopupViews();
////        verify(appViewPanel, times(1)).removeAll();
////    }
//}
