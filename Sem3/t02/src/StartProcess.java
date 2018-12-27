import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class StartProcess {

    public static final int HORIZONTAL_MODE = 0;
    public static final int VERTICAL_MODE   = 1;

    private BufferedImage originImage, outImage;
    private int width, height;

    public StartProcess(BufferedImage originImage) {
        this.originImage = originImage;
        height   = originImage.getHeight();
        width    = originImage.getWidth();
        outImage = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
    }

    public void start(int threadsCount, int mode) {
        BlurFilter[] threads = createProcess(threadsCount, mode);
        for (BlurFilter thread : threads) {
            thread.start();
        }

        for (BlurFilter thread : threads) {
            try {
                thread.join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    private BlurFilter[] createProcess(int threadCount, int mode) {
        if (mode == HORIZONTAL_MODE) {
            return blurFilterHorizontal(threadCount);
        } else if (mode == VERTICAL_MODE) {
            return blurFilterVertical(threadCount);
        }

        System.out.println("Error");
        return null;
    }

    private BlurFilter[] blurFilterHorizontal(int threadCount) {
        int gapSize = height / threadCount;
        BlurFilter[] threads = new BlurFilter[threadCount];
        for (int i = 0; i < threadCount; i++) {
            threads[i] = new BlurFilter(originImage, outImage, 0, i * gapSize, width, gapSize);
        }

        return threads;
    }

    private BlurFilter[] blurFilterVertical(int threadCount) {
        int gapSize = width / threadCount;
        BlurFilter[] threads = new BlurFilter[threadCount];
        for (int i = 0; i < threadCount; i++) {
            threads[i] = new BlurFilter(originImage, outImage, i * gapSize, 0, gapSize, height);
        }

        return threads;
    }

    public void result(String name) throws IOException {
        ImageIO.write(outImage, "jpg", new File(name + ".jpg"));
    }
}
