package frameworks.data_access.serialization;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;

public class BufferedImageSerializer implements Serializer<byte[], BufferedImage> {

    @Override
    public byte[] serialize(BufferedImage bufferedImage){
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        try {
            ImageIO.write(bufferedImage, "png", baos);
        } catch (IOException e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
        return baos.toByteArray();
    }

    @Override
    public BufferedImage deserialize(byte[] bytes){
        ByteArrayInputStream bais = new ByteArrayInputStream(bytes);
        try {
            BufferedImage bufferedImage = ImageIO.read(bais);
            return bufferedImage;
        } catch (IOException e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
    }
}
