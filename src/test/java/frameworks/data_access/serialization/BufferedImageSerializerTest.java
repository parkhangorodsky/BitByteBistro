package frameworks.data_access.serialization;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.IOException;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

class BufferedImageSerializerTest {

    private final BufferedImageSerializer serializer = new BufferedImageSerializer();

    private static BufferedImage originalImage;

    @BeforeAll
    static void setUp() {
        // Create a simple BufferedImage for testing
        originalImage = new BufferedImage(100, 100, BufferedImage.TYPE_INT_RGB);
        Graphics2D graphics = originalImage.createGraphics();
        graphics.setPaint(Color.BLUE);
        graphics.fillRect(0, 0, originalImage.getWidth(), originalImage.getHeight());
        graphics.dispose();
    }

    @Test
    void testSerializeAndDeserialize() {

        // Serialize the BufferedImage to a byte array
        byte[] serializedBytes = serializer.serialize(originalImage);

        // Assert that the byte array is not null or empty
        assertNotNull(serializedBytes);
        assert(serializedBytes.length > 0);

        // Deserialize the byte array back to a BufferedImage
        BufferedImage deserializedImage = serializer.deserialize(serializedBytes);

        // Assert that the deserialized image is not null
        assertNotNull(deserializedImage);

        // Check that the deserialized image has the same dimensions as the original
        assertEquals(originalImage.getWidth(), deserializedImage.getWidth());
        assertEquals(originalImage.getHeight(), deserializedImage.getHeight());

        // Check that the images are identical by comparing their pixels
        for (int x = 0; x < originalImage.getWidth(); x++) {
            for (int y = 0; y < originalImage.getHeight(); y++) {
                assertEquals(originalImage.getRGB(x, y), deserializedImage.getRGB(x, y));
            }
        }
    }
}

