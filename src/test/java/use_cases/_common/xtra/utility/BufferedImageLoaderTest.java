package use_cases._common.xtra.utility;

import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.net.URL;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

public class BufferedImageLoaderTest {

    @Test
    public void testLoadBufferedRoundImage_Success() throws IOException {
        // Arrange
        String imageUrl = "https://thechive.com/wp-content/uploads/2019/12/person-hilariously-photoshops-animals-onto-random-things-xx-photos-25.jpg?attachment_cache_bust=3136487&quality=85&strip=info&w=600";
        URL url = new URL(imageUrl);

        // Mock ImageIO to return a sample image
        BufferedImage sampleImage = ImageIO.read(url);

        // Act
        BufferedImage result = BufferedImageLoader.loadBufferedRoundImage(imageUrl);

        // Assert
        assertNotNull(result, "The image should have been loaded successfully.");
        assertEquals(sampleImage.getWidth(), result.getWidth(), "The width of the rounded image should be 150 pixels.");
        assertEquals(sampleImage.getHeight(), result.getHeight(), "The height of the rounded image should be 150 pixels.");
    }

    @Test
    public void testLoadBufferedRoundImage_InvalidUrl() {
        // Arrange
        String invalidImageUrl = "https://invalid-url.com/image.jpg";

        // Act
        BufferedImage result = BufferedImageLoader.loadBufferedRoundImage(invalidImageUrl);

        // Assert
        assertNull(result, "The image loading should fail and return null.");
    }
}
