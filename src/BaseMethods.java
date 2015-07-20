import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.util.Vector;

/**
 * The "BaseMethods" class contains methods and operations that may
 * be applied throughout all classes within the project's software.
 * This class can be called to help calculate and perform necessary
 * methods.
 *
 * @author Daniel Speiser
 * CS 381, OCR Project: License Plate Reader
 *
 */
public class BaseMethods {
    /**
     * The "max" method takes in three integers, and calculates
     * the maximum value of them.
     *
     * @param int integer1
     * @param int integer2
     * @param int integer3
     * @return integer max - maximum of the three values
     */
    public static int max(int integer1, int integer2, int integer3) {
        int max; //max value to be returned
        //check if integer 1 is greater than integer 2
        if (integer1 > integer2)
            max = integer1;
        else
            max = integer2;
        //check if the greater of the first two integers is greater than the current max
        if (integer3 > max)
            max = integer3;

        return max;
    }//max

    /**
     * The "min" method takes in three integers, and calculates
     * the minimum value of them.
     *
     * @param int integer1
     * @param int integer2
     * @param int integer3
     * @return integer min - minimum of the three values
     */
    public static int min(int integer1, int integer2, int integer3) {
        int min; //min value to be returned
        //check if integer 1 is less than integer 2
        if (integer1 < integer2)
            min = integer1;
        else
            min = integer2;
        //check if the lesser of the first two integers is less than the current min
        if (integer3 < min)
            min = integer3;

        return min;
    }//min

    /**
     * The "max" method takes in three douinteger2les, and calculates
     * the maximum value of them.
     *
     * @param double double1
     * @param double double2
     * @param double double3
     * @return double max - maximum of the three values
     */
    public static double max(double double1, double double2, double double3) {
        double max; //max value to be returned
        //check if integer 1 is greater than integer 2
        if (double1 > double2)
            max = double1;
        else
            max = double2;
        //check if the greater of the first two integers is greater than the current max
        if (double3 > max)
            max = double3;

        return max;
    }//max

    /**
     * The "min" method takes in three doubles, and calculates
     * the minimum value of them.
     *
     * @param double double1
     * @param double double2
     * @param double double3
     * @return double min - minimum of the three values
     */
    public static double min(double double1, double double2, double double3) {
        double min; //min value to be returned
        //check if integer 1 is less than integer 2
        if (double1 < double2)
            min = double1;
        else
            min = double2;
        //check if the lesser of the first two integers is less than the current min
        if (double3 < min)
            min = double3;

        return min;
    }//min

    /**
     * This "findMean" method attains and stores the mean color value within an image
     *
     * @param image - input image
     * @return void - stores the mean value for
     */
    public static double getMean(BufferedImage image) {
        double mean = 0.0;
        //get image height and width for traversal
        int width = image.getWidth();
        int height = image.getHeight();
        //traverse through image, retrieve a pixel's RGB value
        for (int i = 0; i < width; i++) {
            for (int j = 0; j < height; j++) {
                mean += image.getRGB(i, j);
            }//for 2
        }//for 1
        mean /= (width * height);
        return mean;
    }//findMean

    /**
     * This "getPixelValue" method returns the 8 bit RGB grayscale representation in integer form (i.e. from 0-255)
     *
     * @param image
     * @param x     - x coordinate
     * @param y     - y coordinate
     * @return integer value of pixel
     */
    static int getPixelValue(BufferedImage image, int x, int y) {
        return image.getRGB(x, y) & 0xFF;
    }

    static int[][] imageToArray(BufferedImage image) {
        int height = image.getHeight();
        int width = image.getWidth();

        int[][] imageArray = new int[height][width];

        for (int i = 0; i < height; i++) {
            for (int j = 0; j < width; j++) {
                int pixel = getPixelValue(image, j, i);

                if (pixel == 255) {
                    imageArray[i][j] = 0;
                } else
                    imageArray[i][j] = 1;
            }
        }
        return imageArray;
    }


    /**
     * This "print" method prints the values of an array for visualization
     *
     * @param array - values to be printed
     *              prints the values to console
     */
    static void print(int[] array) {
        for (int i = 0; i < array.length; i++)
            System.out.print(array[i] + " ");
        System.out.println();
    }//print

    /**
     * This "print" method prints the values of an array for visualization
     *
     * @param array - values to be printed
     *              prints the values to console
     */
    static void print(double[] array) {
        for (int i = 0; i < array.length; i++)
            System.out.print(array[i] + " ");
        System.out.println();
    }//print

    /**
     * This "print" method prints the values of an array for visualization
     *
     * @param array - values to be printed
     *              prints the values to console
     */
    static void print(int[][] array) {
        for (int i = 0; i < array.length; i++) {
            for (int j = 0; j < array[0].length; j++) {
                System.out.print(array[i][j]);
                //Just for neat printing
                for (int k = String.valueOf(array[i][j]).length(); k < 4; k++)
                    System.out.print(" ");
            }//for2
            System.out.println();
        }//for1
        System.out.println();
    }//print

    /**
     * This "print" method prints the grayscale rgb value of an image for easy visualization
     *
     * @param image - pixel values printed
     *              prints the pixel values to console
     */
    static void print(BufferedImage image) {
        int width = image.getWidth();
        int height = image.getHeight();
        int pixelValue;
        for (int i = 0; i < width; i++) {
            for (int j = 0; j < height; j++) {
                pixelValue = getPixelValue(image, i, j);
                System.out.print(pixelValue);
                //Just for neat printing
                for (int k = String.valueOf(pixelValue).length(); k < 4; k++)
                    System.out.print(" ");
            }//for2
            System.out.println();
        }//for1
        System.out.println();
    }//print

    /**
     * This "print" method prints the values of an array for visualization
     *
     * @param array - values to be printed
     *              prints the values to console
     */
    static void print(boolean[][] array) {
        for (int i = 0; i < array.length; i++) {
            for (int j = 0; j < array[0].length; j++) {
                if (array[i][j] == true)
                    System.out.print("1 ");
                else
                    System.out.print("0 ");
            }//for2
            System.out.println();
        }//for1
        System.out.println();
    }//print

    public static int[] reverse(int [] array) {
        int length = array.length;
        for (int i = 0; i < length / 2; i++) {
            int temp = array[i];
            array[i] = array[length - i - 1];
            array[length - i - 1] = temp;
        }
        return array;
    }//reverse

    //take in filepath not including extension
    public static void saveToFile(BufferedImage image, String filepath) {
        try {
            ImageIO.write(image, "JPG", new File(filepath + ".jpg"));
        } catch(Exception e) {
            System.err.println("Cannot save image to: " + filepath + ". Make sure the image file is valid.");
            e.printStackTrace();
        }
    }//saveToFile
}//BaseMethods

