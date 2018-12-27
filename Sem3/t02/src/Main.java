import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class Main {

    public final static int COUNT_THREAD = 3;
    public static final int HORIZONTAL_MODE = 0;
    public static final int VERTICAL_MODE   = 1;

    public static void main(String[] args) throws IOException{

        File file = new File("flower.jpg");
        BufferedImage image = ImageIO.read(file);
        StartProcess process = new StartProcess(image);
        process.start(COUNT_THREAD, HORIZONTAL_MODE);
        process.result("newFlower");
    }
}
