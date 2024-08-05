package use_cases._common.xtra.utility;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.geom.RoundRectangle2D;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.net.URL;

/**
 * Overview: Utility Interface for loading images.
 * Responsibility: None
 * Utility: Provides default methods to load images from a URL,
 * create rounded corner images, and return them as `ImageIcon` objects.
 */
public class BufferedImageLoader {

    /**
     * Loads an image from a URL and returns it as a `BufferedImage`.
     *
     * @param imageUrl the URL of the image to be loaded.
     * @return a `BufferedImage` containing the image data, or `null` if an error occurs.
     */
    public static BufferedImage loadBufferedRoundImage(String imageUrl) {
        try {
            URL url = new URL(imageUrl);
            BufferedImage bufferedImage = ImageIO.read(url);
            return makeRoundedCorner(bufferedImage, 10);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * Creates a new `BufferedImage` with rounded corners from the given image.
     *
     * @param image the original image to be processed.
     * @param cornerRadius the radius of the corners to be rounded, in pixels.
     * @return a new `BufferedImage` with rounded corners.
     */

    private static BufferedImage makeRoundedCorner(BufferedImage image, int cornerRadius) {
        int w = image.getWidth();
        int h = image.getHeight();
        BufferedImage output = new BufferedImage(w, h, BufferedImage.TYPE_INT_ARGB);

        Graphics2D g2 = output.createGraphics();

        g2.setComposite(AlphaComposite.Src);
        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        g2.setColor(Color.WHITE);
        g2.fill(new RoundRectangle2D.Float(0, 0, w, h, cornerRadius, cornerRadius));

        g2.setComposite(AlphaComposite.SrcAtop);
        g2.drawImage(image, 0, 0, null);

        g2.dispose();

        return output;
    }
}
