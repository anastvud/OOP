package lab4;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

public class Detector {
    private BufferedImage example = null;
    private BufferedImage test_image = null;
    private int[][] example_arr;
    private int[][] test_image_arr;
    private ArrayList<ArrayList<Point>> vectorOfMagpies = new ArrayList<>();



    Detector(String fileName1, String fileName2) {
        LoadImage(fileName1, fileName2);
        example_arr = imageToArray(example);
        test_image_arr = imageToArray(test_image);
    }

    final public void LoadImage(String fileName1, String fileName2) {
        try {
            this.example = ImageIO.read(new File(fileName1));
            this.test_image = ImageIO.read(new File(fileName2));
        } catch (IOException e) {
            System.err.println("Error loading the image: " + e.getMessage());
        }
    }

    public int[][] imageToArray(BufferedImage img) {
        int width = img.getWidth();
        int height = img.getHeight();
        int[][] binaryArray = new int[width][height];

        for (int x = 0; x < width; x++) {
            for (int y = 0; y < height; y++) {
                int pixel = img.getRGB(x, y);
                int r = (pixel >> 16) & 0xff;  // red channel
                int g = (pixel >> 8) & 0xff;   // green channel
                int b = pixel & 0xff;          // blue channel
                binaryArray[x][y] = (r + g + b) < 200 ? 0 : 1;
            }
        }
        return binaryArray;
    }

    public void createBlackWhiteImage() {
        int width = test_image_arr.length;
        int height = test_image_arr[0].length;

        BufferedImage image = new BufferedImage(width, height, BufferedImage.TYPE_BYTE_GRAY);

        for ( ArrayList<Point> i : vectorOfMagpies) {
                    for (Point j : i) {
                        int rgb = (255 << 16) | (255 << 8) | 255;
                        image.setRGB(j.x, j.y, rgb);
                    }
                }

        try {
            ImageIO.write(image, "PNG", new File("D:\\AGH\\2\\OOP\\src\\lab4\\cleared_image.png"));
        } catch (IOException e) {
            System.err.println("Error creating the image: " + e.getMessage());
        }
    }

    public int countOccurrences(int[][] smallArray, int[][] largeArray) {
        int smallWidth = smallArray.length;
        int smallHeight = smallArray[0].length;
        int largeWidth = largeArray.length;
        int largeHeight = largeArray[0].length;

        int count = 0;

        for (int i = 0; i < largeWidth - smallWidth; i++) {
            for (int j = 0; j < largeHeight - smallHeight; j++) {
                boolean found = true;
                ArrayList<Point> magpie = new ArrayList<>();
                for (int x = 0; x < smallWidth; x++) {
                    for (int y = 0; y < smallHeight; y++) {
                        if (smallArray[x][y] == 1) {
                            Point coordinates = new Point(i + x, j + y);
                            magpie.add(coordinates);
                            if (smallArray[x][y] != largeArray[i + x][j + y]) {
                                found = false;
                                break;
                            }
                        }
                    }
                    if (!found) {
                        break;
                    }
                }
                if (found) {
                    count++;
                    vectorOfMagpies.add(magpie);
                }
            }
        }

        return count;
    }



    public static void main(String[] args) {
        Detector detector = new Detector("D:\\AGH\\2\\OOP\\src\\lab4\\ref_image.tif", "D:\\AGH\\2\\OOP\\src\\lab4\\test_image.tif");

        int a = detector.countOccurrences(detector.example_arr, detector.test_image_arr);
        System.out.println(a);
        detector.createBlackWhiteImage();

    }
}
