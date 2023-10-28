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
//        System.out.println(width);
        int height = img.getHeight();
//        System.out.println(height);
        int[][] binaryArray = new int[width][height];

        for (int x = 0; x < width; x++) {
            for (int y = 0; y < height; y++) {
                int pixel = img.getRGB(x, y);
                int r = (pixel >> 16) & 0xff;  // red channel
                int g = (pixel >> 8) & 0xff;   // green channel
                int b = pixel & 0xff;          // blue channel
//                System.out.println(r + " " + g + " " + b);
                binaryArray[x][y] = (r + g + b) < 200 ? 0 : 1;
            }
        }
        return binaryArray;
    }

    public static void createBlackWhiteImage(int[][] pixelArray, String outputPath) {
        int width = pixelArray.length;
        int height = pixelArray[0].length;

        BufferedImage image = new BufferedImage(width, height, BufferedImage.TYPE_BYTE_GRAY);

        for (int x = 0; x < width; x++) {
            for (int y = 0; y < height; y++) {
                int pixelValue = pixelArray[x][y] == 0 ? 0 : 255;
                int rgb = (pixelValue << 16) | (pixelValue << 8) | pixelValue;
                image.setRGB(x, y, rgb);
            }
        }

        try {
            ImageIO.write(image, "PNG", new File(outputPath));
        } catch (IOException e) {
            System.err.println("Error creating the image: " + e.getMessage());
        }
    }

    public static int countOccurrences(int[][] smallArray, int[][] largeArray) {
        int smallWidth = smallArray.length;
        int smallHeight = smallArray[0].length;
        int largeWidth = largeArray.length;
        int largeHeight = largeArray[0].length;

        if (smallWidth > largeWidth || smallHeight > largeHeight) {
            // If the smaller array is larger in either dimension, it cannot be present in the larger array.
            return 0;
        }

        int count = 0;

        for (int i = 0; i < largeWidth - smallWidth; i++) {
            for (int j = 0; j < largeHeight - smallHeight; j++) {
                boolean found = true;
                for (int x = 0; x < smallWidth; x++) {
                    for (int y = 0; y < smallHeight; y++) {
                        if (smallArray[x][y] == 1) {
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
                }
            }
        }

        return count;
    }



    public static void main(String[] args) {
        Detector detector = new Detector("D:\\AGH\\2\\OOP\\src\\lab4\\ref_image.tif", "D:\\AGH\\2\\OOP\\src\\lab4\\test_image.tif");

        int a = detector.countOccurrences(detector.example_arr, detector.test_image_arr);
        System.out.println(a);




//        int[][] vec_ref = detector.imageToArray(detector.example);
//        createBlackWhiteImage(vec_ref, "D:\\AGH\\2\\OOP\\src\\lab4\\ref_test.tif");
//
//        int[][] vec_test = detector.imageToArray(detector.test_image);
//        createBlackWhiteImage(vec_test, "D:\\AGH\\2\\OOP\\src\\lab4\\test_test.tif");

    }
}
