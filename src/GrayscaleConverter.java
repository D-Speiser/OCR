import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;

/**
 * This "GrayscaleConverter" class will be called after a set of images is read in.
 * There are several differnt methods that take an image and convert it to grayscale.
 *
 * @author Daniel Speiser
 * OCR Project: License Plate Reader
 *
 */
public class GrayscaleConverter {
    /**
     * This "toGray" method uses built in methods to convert an image to proper 8-bit gray scale.
     * @param original - input image
     * @return gray - grayscale image
     *
     * Found on: http://stackoverflow.com/questions/9131678/convert-a-rgb-image-to-grayscale-image-reducing-the-memory-in-java
     */
    public BufferedImage toGray(BufferedImage original) {
        //new BufferedImage for grayscale of original
        BufferedImage gray = new BufferedImage(original.getWidth(), original.getHeight(), BufferedImage.TYPE_BYTE_GRAY);
        Graphics g = gray.getGraphics();
        g.drawImage(original, 0, 0, null);
        //If we want to save the image
        //ImageIO.write(gray,"JPG",new File("Gray.jpg"));
        return gray;
    }
    /**
     * Average Method
     *
     * This "toGrayMethod1" method takes in a BufferedImage, and uses the "Average" conversion
     * method to convert the original image to grayscale.
     * @param original - input image
     * @return gray - grayscale image
     *
     * Found on: http://www.tannerhelland.com/3643/
     * Seven grayscale conversion algorithms (with pseudocode and VB6 source code)
     */
    public BufferedImage toGrayMethod1(BufferedImage original) {
        //new BufferedImage for grayscale of original
        BufferedImage grayImage = new BufferedImage(original.getWidth(), original.getHeight(), BufferedImage.TYPE_INT_RGB);
        //declaration of four colors to be used for getting and setting RGB values
        int red, green, blue, gray;
        //get image height and width for traversal
        int width = original.getWidth();
        int height = original.getHeight();
        //traverse through image, retrieve a pixel's RGB value
        //convert the pixel RGB value to gray based on specified method
        for (int i = 0; i < width; i++) {
            for (int j = 0; j < height; j++) {
                Color c = new Color(original.getRGB(i, j));
                red = c.getRed();
                green = c.getGreen();
                blue = c.getBlue();
                gray = (red + blue + green) / 3;
                //averaging method
                Color grayColor = new Color(gray, gray, gray);
                grayImage.setRGB(i, j, grayColor.getRGB());
            }//for 2
        }//for 1
        return grayImage;
    }//toGrayMethod1
    /**
     * Luminosity Method
     *
     * This "toGrayMethod2" method takes in a BufferedImage, and uses the "Lumosity" conversion
     * method to convert the original image to grayscale.
     * @param original - input image
     * @return gray - grayscale image
     *
     *Found on: http://www.tannerhelland.com/3643/
     * Seven grayscale conversion algorithms (with pseudocode and VB6 source code)
     */
    public BufferedImage toGrayMethod2(BufferedImage original) {
        //new BufferedImage for grayscale of original
        BufferedImage grayImage = new BufferedImage(original.getWidth(), original.getHeight(), BufferedImage.TYPE_INT_RGB);
        //declaration of four colors to be used for getting and setting RGB values
        int red, green, blue, gray;
        //get image height and width for traversal
        int width = original.getWidth();
        int height = original.getHeight();
        //traverse through image, retrieve a pixel's RGB value
        //convert the pixel RGB value to gray based on specified method
        for (int i = 0; i < width; i++) {
            for (int j = 0; j < height; j++) {
                Color c = new Color(original.getRGB(i, j));
                red = c.getRed();
                green = c.getGreen();
                blue = c.getBlue();
                gray = (int)(red * 0.299 + green * 0.587 + blue * 0.114);
                Color grayColor = new Color(gray, gray, gray);
                grayImage.setRGB(i, j, grayColor.getRGB());
            }//for 2
        }//for 1
        return grayImage;
    }//toGrayMethod2
    /**
     * Desaturation Method
     *
     * This "toGrayMethod3" method takes in a BufferedImage, and uses the "Desaturation"
     * conversion method to convert the original image to grayscale.
     * @param original - input image
     * @return gray - grayscale image
     *
     * Found on: http://www.tannerhelland.com/3643/
     * Seven grayscale conversion algorithms (with pseudocode and VB6 source code)
     */
    public BufferedImage toGrayMethod3(BufferedImage original) {
        //new BufferedImage for grayscale of original
        BufferedImage grayImage = new BufferedImage(original.getWidth(), original.getHeight(), BufferedImage.TYPE_INT_RGB);
        //declaration of four colors to be used for getting and setting RGB values
        int red, green, blue, gray;
        //get image height and width for traversal
        int width = original.getWidth();
        int height = original.getHeight();
        //traverse through image, retrieve a pixel's RGB value
        //convert the pixel RGB value to gray based on specified method
        for (int i = 0; i < width; i++) {
            for (int j = 0; j < height; j++) {
                Color c = new Color(original.getRGB(i, j));
                red = c.getRed();
                green = c.getGreen();
                blue = c.getBlue();
                gray = (BaseMethods.max(red, green, blue) + BaseMethods.min(red, green, blue) ) / 2;
                //Desaturation method
                Color grayColor = new Color(gray, gray, gray);
                grayImage.setRGB(i, j, grayColor.getRGB());
            }//for 2
        }//for 1
        return grayImage;
    }//toGrayMethod3
    /**
     * MAX Decomposition Method
     *
     * This "toGrayMethod4a" method takes in a BufferedImage, and uses the MAX "Decomposition"
     * conversion method to convert the original image to grayscale.
     * @param original - input image
     * @return gray - grayscale image
     *
     * Found on: http://www.tannerhelland.com/3643/
     * Seven grayscale conversion algorithms (with pseudocode and VB6 source code)
     */
    public BufferedImage toGrayMethod4a(BufferedImage original) {
        //new BufferedImage for grayscale of original
        BufferedImage grayImage = new BufferedImage(original.getWidth(), original.getHeight(), BufferedImage.TYPE_INT_RGB);
        //declaration of four colors to be used for getting and setting RGB values
        int red, green, blue, gray;
        //get image height and width for traversal
        int width = original.getWidth();
        int height = original.getHeight();
        //traverse through image, retrieve a pixel's RGB value
        //convert the pixel RGB value to gray based on specified method
        for (int i = 0; i < width; i++) {
            for (int j = 0; j < height; j++) {
                Color c = new Color(original.getRGB(i, j));
                red = c.getRed();
                green = c.getGreen();
                blue = c.getBlue();
                gray = BaseMethods.max(red, green, blue);
                //MAX Decomposition Method
                Color grayColor = new Color(gray, gray, gray);
                grayImage.setRGB(i, j, grayColor.getRGB());
            }//for 2
        }//for 1
        return grayImage;
    }//toGrayMethod4a
    /**
     * MIN Decomposition Method
     *
     * This "toGrayMethod4b" method takes in a BufferedImage, and uses the MIN "Decomposition"
     * conversion method to convert the original image to grayscale.
     * @param original - input image
     * @return gray - grayscale image
     *
     * Found on: http://www.tannerhelland.com/3643/
     * Seven grayscale conversion algorithms (with pseudocode and VB6 source code)
     */
    public BufferedImage toGrayMethod4b(BufferedImage original) {
        //new BufferedImage for grayscale of original
        BufferedImage grayImage = new BufferedImage(original.getWidth(), original.getHeight(), BufferedImage.TYPE_INT_RGB);
        //declaration of four colors to be used for getting and setting RGB values
        int red, green, blue, gray;
        //get image height and width for traversal
        int width = original.getWidth();
        int height = original.getHeight();
        //traverse through image, retrieve a pixel's RGB value
        //convert the pixel RGB value to gray based on specified method
        for (int i = 0; i < width; i++) {
            for (int j = 0; j < height; j++) {
                Color c = new Color(original.getRGB(i, j));
                red = c.getRed();
                green = c.getGreen();
                blue = c.getBlue();
                gray = BaseMethods.min(red, green, blue);
                //MIN Decomposition Method
                Color grayColor = new Color(gray, gray, gray);
                grayImage.setRGB(i, j, grayColor.getRGB());
            }//for 2
        }//for 1
        return grayImage;
    }//toGrayMethod4b
    /**
     * Single Color Channel RED
     *
     * This "toGrayMethod5a" method takes in a BufferedImage, and uses the RED "Single Color Channel"
     * conversion method to convert the original image to grayscale.
     * @param original - input image
     * @return gray - grayscale image
     *
     * Found on: http://www.tannerhelland.com/3643/
     * Seven grayscale conversion algorithms (with pseudocode and VB6 source code)
     */
    public BufferedImage toGrayMethod5a(BufferedImage original) {
        //new BufferedImage for grayscale of original
        BufferedImage grayImage = new BufferedImage(original.getWidth(), original.getHeight(), BufferedImage.TYPE_INT_RGB);
        //declaration of four colors to be used for getting and setting RGB values
        int red, green, blue, gray;
        //get image height and width for traversal
        int width = original.getWidth();
        int height = original.getHeight();
        //traverse through image, retrieve a pixel's RGB value
        //convert the pixel RGB value to gray based on specified method
        for (int i = 0; i < width; i++) {
            for (int j = 0; j < height; j++) {
                Color c = new Color(original.getRGB(i, j));
                red = c.getRed();
                gray = red;
                //Single Color Channel RED
                Color grayColor = new Color(gray, gray, gray);
                grayImage.setRGB(i, j, grayColor.getRGB());
            }//for 2
        }//for 1
        return grayImage;
    }//toGrayMethod5a
    /**
     * Single Color Channel GREEN
     *
     * This "toGrayMethod5b" method takes in a BufferedImage, and uses the GREEN "Single Color Channel"
     * conversion method to convert the original image to grayscale.
     * @param original - input image
     * @return gray - grayscale image
     *
     * Found on: http://www.tannerhelland.com/3643/
     * Seven grayscale conversion algorithms (with pseudocode and VB6 source code)
     */
    public BufferedImage toGrayMethod5b(BufferedImage original) {
        //new BufferedImage for grayscale of original
        BufferedImage grayImage = new BufferedImage(original.getWidth(), original.getHeight(), BufferedImage.TYPE_INT_RGB);
        //declaration of four colors to be used for getting and setting RGB values
        int red, green, blue, gray;
        //get image height and width for traversal
        int width = original.getWidth();
        int height = original.getHeight();
        //traverse through image, retrieve a pixel's RGB value
        //convert the pixel RGB value to gray based on specified method
        for (int i = 0; i < width; i++) {
            for (int j = 0; j < height; j++) {
                Color c = new Color(original.getRGB(i, j));
                green = c.getGreen();
                gray = green;
                //Single Color Channel GREEN
                Color grayColor = new Color(gray, gray, gray);
                grayImage.setRGB(i, j, grayColor.getRGB());
            }//for 2
        }//for 1
        return grayImage;
    }//toGrayMethod5b
    /**
     * Single Color Channel BLUE
     *
     * This "toGrayMethod5c" method takes in a BufferedImage, and uses the BLUE "Single Color Channel"
     * conversion method to convert the original image to grayscale.
     * @param original - input image
     * @return gray - grayscale image
     *
     * Found on: http://www.tannerhelland.com/3643/
     * Seven grayscale conversion algorithms (with pseudocode and VB6 source code)
     */
    public BufferedImage toGrayMethod5c(BufferedImage original) {
        //new BufferedImage for grayscale of original
        BufferedImage grayImage = new BufferedImage(original.getWidth(), original.getHeight(), BufferedImage.TYPE_INT_RGB);
        //declaration of four colors to be used for getting and setting RGB values
        int red, green, blue, gray;
        //get image height and width for traversal
        int width = original.getWidth();
        int height = original.getHeight();
        //traverse through image, retrieve a pixel's RGB value
        //convert the pixel RGB value to gray based on specified method
        for (int i = 0; i < width; i++) {
            for (int j = 0; j < height; j++) {
                Color c = new Color(original.getRGB(i, j));
                blue = c.getBlue();
                gray = blue;
                //Single Color Channel BLUE
                Color grayColor = new Color(gray, gray, gray);
                grayImage.setRGB(i, j, grayColor.getRGB());
            }//for 2
        }//for 1
        return grayImage;
    }//toGrayMethod5c
    /**
     * Custom # of gray shades
     *
     * This "toGrayMethod6" method takes in a BufferedImage, and uses the "Custom # of gray shades"
     * conversion method to convert the original image to grayscale.
     * @param original - input image
     * @return gray - grayscale image
     *
     * Found on: http://www.tannerhelland.com/3643/
     * Seven grayscale conversion algorithms (with pseudocode and VB6 source code)
     */
    public BufferedImage toGrayMethod6(BufferedImage original) {
        //new BufferedImage for grayscale of original
        BufferedImage grayImage = new BufferedImage(original.getWidth(), original.getHeight(), BufferedImage.TYPE_INT_RGB);
        //declaration of four colors to be used for getting and setting RGB values
        int red, green, blue, gray;
        //get image height and width for traversal
        int width = original.getWidth();
        int height = original.getHeight();
        //traverse through image, retrieve a pixel's RGB value
        //convert the pixel RGB value to gray based on specified method
        for (int i = 0; i < width; i++) {
            for (int j = 0; j < height; j++) {
                Color c = new Color(original.getRGB(i, j));
                red = c.getRed();
                green = c.getGreen();
                blue = c.getBlue();

                int conversionFactor = 255 / (16 - 1);
                int average = (red + green + blue) / 3;
                gray = (int)((average / conversionFactor) + 0.5) * conversionFactor;
                //Single Color Channel BLUE
                Color grayColor = new Color(gray, gray, gray);
                grayImage.setRGB(i, j, grayColor.getRGB());
            }//for 2
        }//for 1
        return grayImage;
    }//toGrayMethod6
    /**
     * Custom # of gray shades
     *
     * This "toGrayMethod6" method takes in a BufferedImage, and uses the "Custom # of gray shades"
     * conversion method to convert the original image to grayscale.
     * @param original - input image
     * @return gray - grayscale image
     *
     * Found on: http://www.tannerhelland.com/3643/
     * Seven grayscale conversion algorithms (with pseudocode and VB6 source code)
     */
    public BufferedImage toGrayMethodTEST(BufferedImage original) {
        //new BufferedImage for grayscale of original
        BufferedImage grayImage = new BufferedImage(original.getWidth(), original.getHeight(), BufferedImage.TYPE_INT_RGB);
        //declaration of four colors to be used for getting and setting RGB values
        int red, green, blue, gray;
        //get image height and width for traversal
        int width = original.getWidth();
        int height = original.getHeight();
        //traverse through image, retrieve a pixel's RGB value
        //convert the pixel RGB value to gray based on specified method
        for (int i = 0; i < width; i++) {
            for (int j = 0; j < height; j++) {
                Color c = new Color(original.getRGB(i, j));
                red = c.getRed();
                green = c.getGreen();
                blue = c.getBlue();

                int conversionFactor = 255 / (16 - 1);
                int average = (red + green + blue) / 3;
                gray = (int)((average / conversionFactor) + 0.5) * conversionFactor;
                //Single Color Channel BLUE
                Color grayColor = new Color(gray, gray, gray);
                grayImage.setRGB(i, j, grayColor.getRGB());
            }//for 2
        }//for 1
        return grayImage;
    }//toGrayMethod6
}//GrayscaleConverter
