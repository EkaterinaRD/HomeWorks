import java.awt.*;
import java.awt.image.BufferedImage;

public class BlurFilter extends Thread{

    private int[][] filter16 = {
            {1, 2, 1},
            {2, 4, 2},
            {1, 2, 1}
    };
    private BufferedImage originImage, outImage;
    private int endX, endY;

    public BlurFilter(BufferedImage originImage, BufferedImage outImage, int begX, int begY, int width, int height) {
        this.originImage = originImage;
        this.outImage = outImage;
        endX = begX + width;
        endY = begY + height;
    }

    @Override
    public void run() {

        int count = 0;
        //int height = givenImage.getHeight();
        //int width = givenImage.getWidth();

        while (count < 8) {

            for (int y = 1; y + 1 < endY; y++) {
                for (int x = 1; x + 1 < endX; x++) {
                    Color tempColor = getFilteredValue(originImage, y, x);
                    outImage.setRGB(x, y, tempColor.getRGB());
                }
            }

            count++;
        }
    }

    private Color getFilteredValue(BufferedImage givenImage, int y, int x) {

        int red = 0, green = 0, blue = 0;

        for (int j = -1; j <= 1; j++) {
            for(int k = -1; k <= 1; k++) {
                Color pixel = new Color(givenImage.getRGB(x + k, y + j));
                int a = filter16[1 + j][1 + k];

                red = red + a * pixel.getRed();
                green = green + a * pixel.getGreen();
                blue = blue + a * pixel.getBlue();
            }
        }

        red = red / sum();
        green = green / sum();
        blue = blue / sum();

        return new Color(red, green, blue);
    }

    private int sum() {

        int sum = 0;

        for (int y = 0; y < filter16.length; y++) {
            for (int x = 0; x < filter16[y].length; x++) {
                sum = sum + filter16[y][x];
            }
        }

        return sum;
    }
}
