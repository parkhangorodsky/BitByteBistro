package use_cases._common.gui_common.abstractions;

import java.awt.*;

/**
 * Overview: Utility interface for defining and providing theme-specific color and font values.
 * Utility: This interface includes predefined color and font constants that can be used
 * throughout the application to maintain a consistent look and feel.
 */
public interface ThemeColoredObject {

    Color claudeBlack = new Color(60, 56, 41);
    Color claudeBlackEmph = new Color(115, 113, 99);

    Color claudeWhiteEmph = new Color(207, 205, 193);
    Color claudeWhite = new Color(238, 237, 227);
    Color claudewhiteBright = new Color(245, 243, 237);

    Color claudeOrange = new Color(217, 119, 87);
    Color sunflower = new Color(255, 179, 44);


    Color black = new Color(0, 0, 0);
    Color lightBlack = new Color(33, 37, 41);
    Color white = new Color(255, 255, 255);

    Color neonPinkEmph = new Color(242, 0, 137);
    Color neonPink = new Color(229, 0, 164);
    Color neonPinkBright = new Color(209, 0, 209);

    Color neonPurple = new Color(161, 0, 242);
    Color neonPurpleEmph = new Color(106, 0, 244);
    Color lightPurple = new Color(45, 0, 247);
    Color darkPurple = new Color(20, 0, 50);

    Color mint = new Color(128, 255, 219);
    Color orange = new Color(255, 109, 0);
    Color brightOrange = new Color(255, 145, 0);

    String defaultFont = "Hoefler Text";
    String secondaryFont = "Avenir Next";
}
