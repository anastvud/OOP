package lab4;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

public class Detector {
    private BufferedImage example = null;

    final public void LoadImage(String fileName) {
        try {
            this.example = ImageIO.read(new File(fileName));
        } catch (IOException e) {
            System.err.println("Error loading the image: " + e.getMessage());
        }
    }

    public int[][] ImageToArray(BufferedImage img) {
        int width = img.getWidth();
        System.out.println(width);
        int height = img.getHeight();
        System.out.println(height);
        int[][] binaryArray = new int[width][height];

        for (int x = 0; x < width; x++) {
            for (int y = 0; y < height; y++) {
                int pixel = img.getRGB(x, y);
                int r = (pixel >> 16) & 0xff;  // red channel
                int g = (pixel >> 8) & 0xff;   // green channel
                int b = pixel & 0xff;          // blue channel

                // Check if the pixel is black (you may need to adjust this based on your image)
                binaryArray[x][y] = (r + g + b) < 100 ? 0 : 1;
            }
        }

        return binaryArray;
    }


    public int[][] analyzeAlphaChannel(String imagePath) {
        try {
            BufferedImage image = ImageIO.read(new File(imagePath));
            int width = image.getWidth();
            int height = image.getHeight();
            int[][] alphaArray = new int[width][height];

            for (int x = 0; x < width; x++) {
                for (int y = 0; y < height; y++) {
                    int pixel = image.getRGB(x, y);
                    int alpha = (pixel >> 24) & 0xFF;
                    System.out.print(alpha);
                    // Check if the alpha value is 0 (fully transparent), which indicates a black pixel
                    if (alpha == 255) {
                        alphaArray[x][y] = 0;
                    } else {
                        alphaArray[x][y] = 1;
                    }
                }
            }

            return alphaArray;
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }

    public static void createBlackWhiteImage(int[][] pixelArray, String outputPath) {
        int width = pixelArray.length;
        int height = pixelArray[0].length;

        BufferedImage image = new BufferedImage(width, height, BufferedImage.TYPE_BYTE_GRAY);

        for (int x = 0; x < width; x++) {
            for (int y = 0; y < height; y++) {
                int pixelValue = pixelArray[x][y] == 0 ? 0 : 255; // 0 for black, 255 for white
                int rgb = (pixelValue << 16) | (pixelValue << 8) | pixelValue;
                image.setRGB(x, y, rgb);
            }
        }

        try {
            ImageIO.write(image, "PNG", new File(outputPath));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    public static void main(String[] args) {
        Detector detector = new Detector();
        detector.LoadImage("D:\\AGH\\2\\OOP\\src\\lab4\\magpie.tiff");
        int[][] vec = detector.analyzeAlphaChannel("D:\\AGH\\2\\OOP\\src\\lab4\\magpie.tiff");

//        detector.createBlackWhiteImage(vec, "D:\\AGH\\2\\OOP\\src\\lab4\\test.tiff");
//        System.out.print("hello");
//        System.out.print("hello");
//        System.out.print("hello");
//        for (var i : vec) {
//            for (var j : i) {
//                System.out.print(j);
//            }
//            System.out.println("\n");
//        }
    }
}
