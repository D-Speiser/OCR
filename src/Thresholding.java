import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.util.Arrays;
/**
 *
 * This "Thresholding" class handles thresholding after an image has been converted to grayscale.
 * This class contains several thresholding methods, including Maximum Entropy, Gaussian Mixture Models,
 * and Mean methods, along with necessary statistical and mathematical methods regarding to thresholding.
 *
 * @author Daniel Speiser
 * OCR Project: License Plate Reader
 *
 */
public class Thresholding extends BaseMethods {
    /**
     * This "grayToMono" method converts a grayscale image to monochrome, using the specified threshold
     * @param image - input grayscale image
     * @param threshold - threshold input
     * @return BufferedImage - Monochrome of original image using the mean method
     */
    public BufferedImage grayToMono(BufferedImage image, int threshold) {
        BufferedImage result = new BufferedImage(image.getWidth(), image.getHeight(), BufferedImage.TYPE_BYTE_BINARY);
        //get image height and width for traversal
        int width = image.getWidth();
        int height = image.getHeight();
        //traverse through image, retrieve a pixel's RGB value
        for (int i = 0; i < width; i++) {
            for (int j = 0; j < height; j++) {
                if (getPixelValue(image, i, j) > threshold)
                    //set to white, alternatively could use -1
                    result.setRGB(i, j, 0xFFFFFFFF);
                else
                    //set to black, alternatively could use 0
                    result.setRGB(i, j, 0xFF000000);
            }//for 2
        }//for 1
        return result;
    }//toGrayMethodMeanMethod
    /**
     * This "GrayToMonoMeanMethod" method converts a grayscale image to monochrome, using the mean method
     * @param image - input grayscale image
     * @return BufferedImage - Monochrome of original image using the mean method
     */
    public BufferedImage GrayToMonoMeanMethod(BufferedImage image, double mean) {
        BufferedImage result = new BufferedImage(image.getWidth(), image.getHeight(), BufferedImage.TYPE_BYTE_BINARY);
        //get image height and width for traversal
        int width = image.getWidth();
        int height = image.getHeight();
        //traverse through image, retrieve a pixel's RGB value
        for (int i = 0; i < width; i++) {
            for (int j = 0; j < height; j++) {
                if (image.getRGB(i, j) > mean)
                    result.setRGB(i, j, -1);
                else
                    result.setRGB(i, j, 0);
            }//for 2
        }//for 1
        return result;
    }//toGrayMethodMeanMethod
    /**
     * This "sum" method calculates and returns the sum of an "image" array
     * @param "image"/array - array of pixels
     * @return double - sum of the array
     */
    public int sum (int [] array) {
        int sum = 0; //initial sum
        for (int i = 0; i < array.length; i++)
            sum += array[i];
        return sum;
    }//sum
    /**
     * This "sum" method calculates and returns the sum of an "image" array
     * @param "image"/array - array of pixels
     * @return double - sum of the array
     */
    public double sum (int [][] array) {
        double sum = 0.0; //initial sum
        for (int i = 0; i < array.length; i++) {
            for (int j = 0; j < array[i].length; j++) {
                sum += array[i][j];
            }
        }
        return sum;
    }//sum
    /**
     * This "sum" method calculates and returns the sum of the pixel values within an image
     * @param image - image of pixels
     * @return double - sum of the array
     */
    public double sum (BufferedImage image) {
        double sum = 0.0; //initial sum
        int width = image.getWidth();
        int height = image.getHeight();
        //given that the image is in grayscale, we know that
        //r = g = b, so we can use the unsigned int representation
        //of the converted grayscale value
        for (int i = 0; i < width; i++) {
            for (int j = 0; j < height; j++) {
                sum += getPixelValue(image, i, j);
            }
        }
        return sum;
    }//sum
    /**
     * This "sumBackground" method calculates and returns the sum of the background of an image
     * @param image - image of pixels
     * @return double - sum of the array
     */
    public double sumBackground (BufferedImage image, int threshold) {
        double sum = 0.0; //initial sum
        int width = image.getWidth();
        int height = image.getHeight();
        int temp;
        //given that the image is in grayscale, we know that
        //r = g = b, so we can use the unsigned int representation
        //of the converted grayscale value
        for (int i = 0; i < width; i++) {
            for (int j = 0; j < height; j++) {
                temp = getPixelValue(image, i, j);
                if (temp <= threshold)
                    sum += temp;
            }
        }
        return sum;
    }//sum
    /**
     * This "sumForeground" method calculates and returns the sum of the foreground of an image
     * @param image - image of pixels
     * @return double - sum of the array
     */
    public double sumForeground (BufferedImage image, int threshold) {
        double sum = 0.0; //initial sum
        int width = image.getWidth();
        int height = image.getHeight();
        int temp;
        //given that the image is in grayscale, we know that
        //r = g = b, so we can use the unsigned int representation
        //of the converted grayscale value
        for (int i = 0; i < width; i++) {
            for (int j = 0; j < height; j++) {
                temp = getPixelValue(image, i, j);
                if (temp > threshold)
                    sum += temp;
            }
        }
        return sum;
    }//sum
    /**
     * This "average" method calculates the "average", or mean value of an array
     * @param array - array of pixel values
     * @return double - average
     */
    public double average (int [][] array) {
        return sum(array)/(numElements(array));
    }//average
    /**
     * This "average" method calculates the "average", or mean value of an array
     * @param image
     * @return double - average
     */
    public double average (BufferedImage image) {
        return sum(image)/(numPixels(image));
    }//average
    /**
     * This "averageBackground" method calculates the "average", or mean value of the background of an image
     * @param image
     * @return double - average
     */
    public double averageBackground (BufferedImage image, int threshold) {
        return sumBackground(image, threshold)/numPixelsInBackground(image, threshold);
    }//average
    /**
     /**
     * This "averageForeground" method calculates the "average", or mean value of the foreground of an image
     * @param image
     * @return double - average
     */
    public double averageForeground (BufferedImage image, int threshold) {
        return sumForeground(image, threshold)/numPixelsInForeground(image, threshold);
    }//average

    public double median(BufferedImage image) {
        int numPixels = numPixels(image);
        int height = image.getHeight();
        int width = image.getWidth();

        int [] pixels = new int[numPixels];

        for (int i = 0; i < height; i++) {
            for (int j = 0; j < width; j++) {
                pixels[i * width + j] = getPixelValue(image, j, i);
            }
        }

        Arrays.sort(pixels);

        int median = numPixels / 2;

        if (numPixels % 2 == 1)
            return pixels[median];

        return (pixels[median-1] + pixels[median]) / 2.0;
    }
    /**
     * This "numElements" method returns the number of elements in an array
     * @param array - array or image of values
     * @return double - number of elements
     */
    public int numElements (int [][] array) {
        int numElements = 0;
        for (int i = 0; i < array.length; i++) {
            for (int j = 0; j < array[i].length; j++) {
                numElements++;
            }//for2
        }//for1
        return numElements;
    }//numElements
    /**
     * This "numElements" method returns the number of elements in an array
     * @param image - image of pixels
     * @return int - number of pixels
     */
    public int numPixels (BufferedImage image) {
        return image.getWidth() * image.getHeight();
    }//numPixels
    /**
     * This "variance" method returns the variance of the values within a given array
     * @param array - array of values
     * @param mean - mean of the values
     * @return double - variance
     */
    public double variance (int [][] array, double mean) {
        double variance = 0;
        //calculate the variance
        for (int i = 0; i < array.length; i++) {
            for (int j = 0; j < array[i].length; j++) {
                variance += Math.pow(array[i][j] - mean ,2);
            }//for2
        }//for1
        return variance / numElements(array);
    }//variance
    /**
     * This "variance" method returns the variance of the pixels within the image
     * @param image - given image
     * @return double - variance
     */
    public double variance (BufferedImage image) {
        double variance = 0, mean = average(image);
        int width = image.getWidth();
        int height = image.getHeight();
        //calculate the variance
        for (int i = 0; i < width; i++) {
            for (int j = 0; j < height; j++) {
                variance += Math.pow(getPixelValue(image, i, j) - mean ,2);
            }//for2
        }//for1
        return variance / numPixels(image);
    }//variance
    /**
     * This "standardDeviation" method returns the standard deviation of a given array
     * @param array - given values
     * @return double - standard deviation of the values
     */
    public double standardDeviation (int [][] array) {
        return Math.sqrt(variance(array, average(array)));
    }//standardDeviation
    /**
     * This "standardDeviation" method returns the standard deviation of a given image
     * @param image - given image
     * @return double - standard deviation of the values
     */
    public double standardDeviation (BufferedImage image) {
        return Math.sqrt(variance(image));
    }//standardDeviation
    /**
     * This "varianceBackground" method returns the variance of the values within the background of an image
     * @param array - array of pixel values
     * @param threshold - the threshold value used
     * @param mean- the average of the pixel values
     * @return double - variance of the background
     */
    public double varianceBackground (int [][] array, int threshold, double mean) {
        double variance = 0;
        //calculates the variance
        for (int i = 0; i < array.length; i++) {
            for (int j = 0; j < array[i].length; j++) {
                if (array[i][j] <= threshold)
                    variance += Math.pow(array[i][j] - mean ,2);
            }
        }
        return variance / numPixelsInBackground(array, threshold);
    }//varianceBackground
    /**
     * This "varianceBackground" method returns the variance of the values within the background of an image
     * @param image - given image
     * @param threshold - the threshold value used
     * @return double - variance of the background
     */
    public double varianceBackground (BufferedImage image, int threshold, double mean) {
        double variance = 0;
        int width = image.getWidth();
        int height = image.getHeight();
        int pixelValue;
        //calculates the variance
        for (int i = 0; i < width; i++) {
            for (int j = 0; j <height; j++) {
                pixelValue = getPixelValue(image, i, j);
                if (pixelValue <= threshold)
                    variance += Math.pow(pixelValue - mean ,2);
            }
        }
        return variance / numPixelsInBackground(image, threshold);
    }//varianceBackground
    /**
     * This "varianceForeground" method returns the variance of the values within the foreground of an image
     * @param array - array of pixel values
     * @param threshold - the threshold value used
     * @param mean- the average of the pixel values
     * @return double - variance of the foreground
     */
    public double varianceForeground (int [][] array, int threshold, double mean) {
        double variance = 0;
        //calculates the variance
        for (int i = 0; i < array.length; i++) {
            for (int j = 0; j < array[i].length; j++) {
                if (array[i][j] > threshold)
                    variance += Math.pow(array[i][j] - mean ,2);
            }
        }
        return variance / numPixelsInForeground(array, threshold);
    }
    /** This "varianceForeground" method returns the variance of the values within the foreground of an image
     * @param image - given image
     * @param threshold - the threshold value used
     * @return double - variance of the background
     */
    public double varianceForeground (BufferedImage image, int threshold, double mean) {
        double variance = 0;
        int width = image.getWidth();
        int height = image.getHeight();
        int pixelValue;
        //calculates the variance
        for (int i = 0; i < width; i++) {
            for (int j = 0; j <height; j++) {
                pixelValue = getPixelValue(image, i, j);
                if (pixelValue > threshold)
                    variance += Math.pow(pixelValue - mean ,2);
            }
        }
        return variance / numPixelsInForeground(image, threshold);
    }//varianceForeground
    /**
     * This "toBoolean" method takes a grayscale image, and creates a "monochrome" boolean array from the given threshold
     * @param array - pixel colors
     * @param threshold - given threshold value to be applied
     * @return boolean[][] - "monochrome" image or array
     */
    public boolean[][] toBooleanArray (int [][] array, double threshold) {
        boolean[][] result = new boolean[array.length][array[0].length];

        for (int i = 0; i < array.length; i++) {
            for (int j = 0; j < array[0].length; j++) {
                if (array[i][j] >= threshold)
                    result[i][j] = true;
                else
                    result[i][j] = false;
            }//for2
        }//for1

        return result;
    }//toBooleanArray
    /**
     * This "histogram" method takes in an array of pixel values, and creates a histogram
     * @param array - array of image pixel values
     * @return double[] - histogram
     */
    public double [] histogram (int [][] array) {
        //creates an array of size 256, using 256 colors
        double [] histogram = new double [256];

        for (int i = 0; i < array.length; i++) {
            for (int j = 0; j < array[i].length; j++) {
                histogram[array[i][j]]++;
            }//for2
        }//for1

        return histogram;
    }//histogram
    /**
     * This "histogram" method takes in an image and creates a histogram of its pixel values
     * @param image
     * @return double[] - histogram
     */
    public int [] histogram (BufferedImage image) {
        //creates an array of size 256, using 256 colors
        int [] histogram = new int [256];
        int width = image.getWidth();
        int height = image.getHeight();
        for (int i = 0; i < width; i++) {
            for (int j = 0; j < height; j++) {
                histogram[getPixelValue(image, i, j)]++;
            }//for2
        }//for1
        return histogram;
    }//histogram
    /**
     * This "getNormalizedHistogram" method takes in a histogram, and returns a normalized histogram,
     * which is the probability of a color occurring, instead of the number of occurrences.
     * Sum of normalized histogram elements = 1.
     * @param histogram - histogram to be made into a normalized histogram
     * @return double -
     */
    public double [] getNormalizedHistogram (int [] histogram) {
        //creates an array of size 256, using 256 colors
        double [] normalizedHistogram = new double [256];
        int size = sum(histogram);

        for (int i = 0; i < histogram.length; i++) {
            normalizedHistogram[i] = histogram[i] / (size * 1.0);
        }//for

        return normalizedHistogram;
    }//getNormalizedHistogram
    /**
     * This "getNormalizedHistogram" method takes in a histogram, and returns a normalized histogram,
     * which is the probability of a color occurring, instead of the number of occurrences.
     * Sum of normalized histogram elements = 1.
     * @param histogram - histogram to be made into a normalized histogram
     * @param size - num elements in the histogram
     * @return double -
     */
    public double [] getNormalizedHistogram (double [] histogram, int size) {
        //creates an array of size 256, using 256 colors
        double [] normalizedHistogram = new double [256];

        for (int i = 0; i < histogram.length; i++) {
            normalizedHistogram[i] = histogram[i] / size;
        }//for

        return normalizedHistogram;
    }//getNormalizedHistogram
    /**
     * This "probability" method takes in a histogram and index (and size for different probability uses)
     * and calculates the probability of an occurrence of a color (index) within the image.
     * @param histogram - histogram of values and occurrences
     * @param index - index/color of what probability we are seeking
     * @param size - size of array
     * @return double - probability of occurrence
     */
    public double probability (double [] histogram, int index, int size) {
        return histogram[index] / size;
    }
    /**
     * This "probability" method takes in a histogram and index (and size for different probability uses)
     * and calculates the probability of an occurrence of a color (index) within the image.
     * @param histogram - histogram of values and occurrences
     * @param index - index/color of what probability we are seeking
     * @return double - probability of occurrence
     */
    public double probabilityB (int [] histogram, int index, int threshold) {
        double sum = 0.0;
        for (int i = 0; i <= threshold; i++) {
            sum += histogram[i];
        }

        return histogram[index] / sum;
    }
    public double probabilityF (int [] histogram, int index, int threshold) {
        double sum = 0.0;
        for (int i = threshold + 1; i < 256; i++) {
            sum += histogram[i];
        }

        return histogram[index] / sum;
    }
    /**
     * This "probability" method takes in a histogram and index (and size for different probability uses)
     * and calculates the probability of an occurrence of a color (index) within the image.
     * @param histogram - histogram of values and occurrences
     * @param index - index/color of what probability we are seeking
     * @return double - probability of occurrence
     */
    public double probability (int [] histogram, int index, int size) {
        return histogram[index] / size;
    }
    /**
     * This "probabilityBackground" method calculates the probability of something occurring within the background pixels
     * based on a given threshold value.
     * @param threshold - to classify what is background, and what is foreground.
     * @param histogram
     * @return double - probability of occurrence
     */
    public double probabilityBackground(int [] histogram, int threshold) {
        double probability = 0.0;
        //calculate probability
        for (int i = 0; i <= threshold; i++) {
            probability += probabilityB(histogram, i, threshold);
        }//for

        return probability;
    }//probabilityBackground
    /**
     * This "probabilityForeground" method calculates the probability of something occurring within the foreground pixels
     * based on a given threshold value.
     * @param histogram
     * @param threshold - to classify what is background, and what is foreground.
     * @return double - probability of occurrence
     */
    public double probabilityForeground(int [] histogram, int threshold) {
        double probability = 0.0;
        //calculate probability
        for (int i = threshold + 1; i < 256; i++) {
            probability += probabilityF(histogram, i, threshold);
        }//for

        return probability;
    }//probabilityForeground
    /**
     * This "numPixelsInBackground" method returns the number of pixels within the background of an
     * image, calculated by a given threshold value
     * @param image - pixel values of the image
     * @param threshold - to classify what is background, and what is foreground.
     * @return int - number of pixels in the background
     */
    public int numPixelsInBackground (int [][] image, int threshold) {
        int numPixels = 0;
        for (int i = 0; i < image.length; i++) {
            for (int j = 0; j < image[i].length; j++) {
                if (image[i][j] <= threshold)
                    numPixels++;
            }//for2
        }//for1
        return numPixels;
    }//numPixelsInBackground
    /**
     * This "numPixelsInBackground" method returns the number of pixels within the background of an
     * image, calculated by a given threshold value
     * @param image - pixel values of the image
     * @param threshold - to classify what is background, and what is foreground.
     * @return int - number of pixels in the background
     */
    public int numPixelsInBackground (BufferedImage image, int threshold) {
        int numPixels = 0;
        int width = image.getWidth();
        int height = image.getHeight();
        for (int i = 0; i < width; i++) {
            for (int j = 0; j < height; j++) {
                if (getPixelValue(image, i, j) <= threshold)
                    numPixels++;
            }//for2
        }//for1
        return numPixels;
    }//numPixelsInBackground
    /**
     * This "numPixelsInForeground" method returns the number of pixels within the foreground of an
     * image, calculated by a given threshold value
     * @param image - pixel values of the image
     * @param threshold - to classify what is background, and what is foreground.
     * @return int - number of pixels in the foreground
     */
    public int numPixelsInForeground (int [][] image, int threshold) {
        int numPixels = 0;
        for (int i = 0; i < image.length; i++) {
            for (int j = 0; j < image[i].length; j++) {
                if (image[i][j] > threshold)
                    numPixels++;
            }//for2
        }//for1
        return numPixels;
    }//numPixelsInForeground
    /**
     * This "numPixelsInForeground" method returns the number of pixels within the foreground of an
     * image, calculated by a given threshold value
     * @param image - pixel values of the image
     * @param threshold - to classify what is background, and what is foreground.
     * @return int - number of pixels in the background
     */
    public int numPixelsInForeground (BufferedImage image, int threshold) {
        int numPixels = 0;
        int width = image.getWidth();
        int height = image.getHeight();
        for (int i = 0; i < width; i++) {
            for (int j = 0; j < height; j++) {
                if (getPixelValue(image, i, j) > threshold)
                    numPixels++;
            }//for2
        }//for1
        return numPixels;
    }//numPixelsInForeground
    /**
     * This "meanBackground" method calculates the mean of the background pixels of an image
     * @param image
     * @param threshold - to classify what is background, and what is foreground.
     * @return double - mean of the background
     */
    public double meanBackground(BufferedImage image, int [] histogram, int threshold) {
        double mean = 0.0;

        for (int i = 0; i <= threshold; i++) {
            mean += i * probability(histogram, i, numPixelsInBackground(image, threshold));
        }//for

        return mean;
    }//meanBackground
    /**
     * This "meanForeground" method calculates the mean of the foreground pixels of an image
     * @param threshold - to classify what is background, and what is foreground.
     * @return double - mean of the foreground
     */
    public double meanForeground(BufferedImage image, int [] histogram, int threshold) {
        double mean = 0.0;

        for (int i = threshold + 1; i < 256; i++) {
            mean += i * probability(histogram, i, numPixelsInForeground(image, threshold));
        }//for

        return mean;
    }//meanForeground
    /**
     * This "gaussianBackground" method calculates the gaussian background value, given from our definition.
     * @param g - grayscale color
     * @param meanBackground - mean color of the background
     * @param varianceBackground - variance of the background colors
     * @return double - gaussian background
     */
    public double gaussianBackground(int g, double meanBackground, double varianceBackground) {
        return (1 / Math.sqrt(2 * Math.PI * varianceBackground)) * Math.pow(Math.E, -(Math.pow(g - meanBackground, 2) / (2 * varianceBackground)));
    }//gaussianBackground
    /**
     * This "gaussianForeground" method calculates the gaussian foreground value, given from our definition.
     * @param g - grayscale color
     * @param meanForeground - mean color of the foreground
     * @param varianceForeground - variance of the foreground colors
     * @return double - gaussian foreground
     */
    public double gaussianForeground(int g, double meanForeground, double varianceForeground) {
        return (1 / Math.sqrt(2 * Math.PI * varianceForeground)) * Math.pow(Math.E, -(Math.pow(g - meanForeground, 2) / (2 * varianceForeground)));
    }//gaussianForeground
    /**
     * This "GMM" method calculated the gaussian mixture model value, based on the given definition.
     * @param g - grayscale color
     * @return double - Gaussian mixture model value
     */
    public double GMM(int g, double probabilityBackground, double meanBackground, double varianceBackground, double probabilityForeground, double meanForeground, double varianceForeground) {
        return probabilityBackground * gaussianBackground(g, meanBackground, varianceBackground) + probabilityForeground * gaussianForeground(g, meanForeground, varianceForeground);
    }//GMM
    /**
     * This "errorFitnessValues" method calculates the error values of a given image (using the histogram of the image)
     * @param histogram - histogram of the image
     * .
     * .
     * .
     * @return double - error fitness values
     */
    public double errorFitnessValues(double [] histogram, double probabilityBackground, double meanBackground, double varianceBackground, double probabilityForeground, double meanForeground, double varianceForeground) {
        double error = 0.0;
        for (int i = 0; i < 256; i++) {
            error += Math.pow(GMM(i, probabilityBackground, meanBackground, varianceBackground, probabilityForeground, meanForeground, varianceForeground) - histogram[i], 2);
        }//for
        return error;
    }//errorFitnessValues
    /**
     * This "minErrorFitnessValue" method calculates the error values of a given image (using the histogram of the image)
     * @param histogram - histogram of the image
     * .
     * .
     * .
     * @return double - error fitness values
     */
    public int minErrorFitnessValue(double [] histogram, double probabilityBackground, double meanBackground, double varianceBackground, double probabilityForeground, double meanForeground, double varianceForeground) {
        double min = Math.pow(GMM(0, probabilityBackground, meanBackground, varianceBackground, probabilityForeground, meanForeground, varianceForeground) - histogram[0], 2), temp;
        int threshold = 0;
        for (int i = 1; i < 256; i++) {
            temp = Math.pow(GMM(i, probabilityBackground, meanBackground, varianceBackground, probabilityForeground, meanForeground, varianceForeground) - histogram[i], 2);
            if (temp < min) {
                min = temp;
                threshold = i;
            }
        }//for
        return threshold;
    }//errorFitnessValues
    /**
     * This "minErrorFitnessValue" method calculates the error values of a given image (using the histogram of the image)
     * @param histogram - histogram of the image
     * .
     * .
     * .
     * @return double - error fitness values
     */
    public int minErrorFitnessValue(BufferedImage image) {
        int [] histogram = histogram(image);
        double temp, probBack, meanBack, varBack, probFor, meanFor, varFor, mean = average(image);
        double min = Math.pow(GMM(0, probabilityBackground(histogram, 0), meanBackground(image, histogram, 0), varianceBackground(image, 0, mean), probabilityForeground(histogram, 0), meanForeground(image, histogram, 0), varianceForeground(image, 0, mean)) - histogram[0], 2);
        int threshold = 0;
        for (int i = 1; i < 256; i++) {
            probBack = probabilityBackground(histogram, i);
            meanBack = meanBackground(image, histogram, i);
            varBack = varianceBackground(image, i, mean);
            probFor = probabilityForeground(histogram, i);
            meanFor = meanForeground(image, histogram, i);
            varFor = varianceForeground(image, i, mean);

            temp = Math.pow(GMM(i, probBack, meanBack, varBack, probFor, meanFor, varFor) - histogram[i], 2);

            if (temp < min) {
                min = temp;
                threshold = i;
            }
        }//for
        return threshold;
    }//errorFitnessValues
    /**
     * This "entropyBackground" method returns the entropy value of the background of an image,
     * calculated by a given threshold value.
     * @param threshold - to classify what is background, and what is foreground.
     * @return double - entropy of the background
     */
    public double entropyBackground (BufferedImage image, int threshold) {
        double entropy = 0.0, normalizedSum = 0.0;
        int [] histogram = histogram(image);
        double [] normalizedHistogram = getNormalizedHistogram(histogram);

        //calculates the normalized sum until the given threshold
        for (int i = 0; i <= threshold; i++) {
            normalizedSum += normalizedHistogram[i];
        }//for

        for (int i = 0; i <= threshold; i++) {
            //otherwise -infinity causes result to be NaN (not a number)
            if (normalizedHistogram[i] > 0.0)
                entropy += (normalizedHistogram[i] / normalizedSum) * Math.log(normalizedHistogram[i] / normalizedSum);
        }//for

        return 0 - entropy;
    }//entropyBackground
    /**
     * This "entropyForeground" method returns the entropy value of the foreground of an image,
     * calculated by a given threshold value.
     * @param threshold - to classify what is background, and what is foreground.
     * @return double - entropy of the foreground
     */
    public double entropyForeground (BufferedImage image, int threshold) {
        double entropy = 0.0, normalizedSum = 0.0;
        double [] normalizedHistogram = getNormalizedHistogram(histogram(image));
        //calculates the normalized sum from the given threshold
        for (int i = threshold + 1; i < 256; i++) {
            normalizedSum += normalizedHistogram[i];
        }//for

        for (int i = threshold + 1; i < 256; i++) {
            //otherwise -infinity causes result to be NaN (not a number)
            if (normalizedHistogram[i] > 0.0)
                entropy += (normalizedHistogram[i] / normalizedSum) * Math.log(normalizedHistogram[i] / normalizedSum);
        }//for

        return 0 - entropy;
    }//entropyForeground
    /**
     * This "maximumEntropyThreshold" method calculates the maximum entropy value from any of the possible threshold values.
     * @return int - threshold value for the maximum entropy
     */
    public int maximumEntropyThreshold(BufferedImage image) {
        int threshold = 0;
        double max = entropyBackground(image, 0) + entropyForeground(image, 0), temp;

        for (int i = 1; i < 256; i++) {
            temp = entropyBackground(image, i) + entropyForeground(image, i);

            if (temp > max) {
                max = temp;
                threshold = i;
            }
        }
        return threshold;
    }//maximumEntropyThreshold
    /**
     * This "otsu" method calculates the variance between classes (intra class variance between foreground and background)
     * @param histogram - image histogram
     * @param threshold - finds variance for a given threshold
     * @return double - variance
     */
    public double otsu (BufferedImage image, int [] histogram, int threshold) {
        //return probabilityBackground(histogram, threshold) * varianceBackground(image, threshold, average(image)) + probabilityForeground(histogram, threshold) * varianceForeground(image, threshold, average(image));
        return probabilityBackground(histogram, threshold) * probabilityForeground(histogram, threshold) * Math.pow(varianceBackground(image, threshold, averageBackground(image, threshold) - varianceForeground(image, threshold, averageForeground(image, threshold))), 2);
    }//otsu
    // Get binary treshold using Otsu's method
    /**
     * This "otsuThreshold" method calculates the best threshold based on the variance between classes (intra class variance between foreground and background)
     * @param image - grayscale image
     * @return int - threshold
     *
     * This is similar to my implementation of otsu, however it is more accurate and more efficient.
     * It was found on: http://zerocool.is-a-geek.net/java-image-binarization/
     */
    public int otsuThreshold(BufferedImage image) {

        int[] histogram = histogram(image);
        double[] hist2 = getNormalizedHistogram(histogram);
        int total = image.getHeight() * image.getWidth();

        float sum = (float)sum(image);

        float sumB = 0;
        int wB = 0;
        int wF = 0;

        float varMax = 0;
        int threshold = 0;

        for(int i=0 ; i<256 ; i++) {
            wB += histogram[i];
            if(wB == 0) continue;
            wF = total - wB;

            if(wF == 0) break;

            sumB += (float) (i * histogram[i]);
            float mB = sumB / wB;
            float mF = (sum - sumB) / wF;

            float varBetween = (float) wB * (float) wF * (mB - mF) * (mB - mF);

            //varian

            if(varBetween > varMax) {
                varMax = varBetween;
                threshold = i;
            }
        }

        return threshold;

    }
    /**
     * This "argMax" method calculates the maximum value of the given arguments
     * @return threshold
     */
    public int argMaxOtsu (BufferedImage image) {
        int [] histogram = histogram(image);
        double max = otsu(image, histogram, 0), temp;
        System.out.println(max);
        int threshold = 0;

        for (int i = 1; i < 256; i++) {
            temp = otsu(image, histogram, i);
            System.out.println(temp);
            if (temp > max) {
                max = temp;
                threshold = i;
            }
        }
        return threshold;
    }//argMax



    public BufferedImage votingSystem(BufferedImage [] images) {
        int numImages = images.length;
        //verify same image dimensions, otherwise this method will fail
        for (int i = 0; i < numImages; i++) {
            for (int j = 0; j < numImages; j++) {
                if (i != j) {
                    if (images[i].getWidth() != images[j].getWidth() || images[i].getHeight() != images[j].getHeight()) {
                        System.out.println("Inconsistent Image Inputs!!!");
                        return null;
                    }
                }
            }
        }
        int imageHeight = images[0].getHeight();
        int imageWidth = images[0].getWidth();

        BufferedImage result = new BufferedImage(imageWidth, imageHeight, BufferedImage.TYPE_BYTE_BINARY);

        int numVotes = 0;

        for (int i = 0; i < imageWidth; i++) {
            for (int j = 0; j < imageHeight; j++) {
                for (int k = 0; k < numImages; k++) {
                    if (getPixelValue(images[k], i, j) == 0)
                        numVotes++;
                }

                if (numVotes > numImages / 2)
                    //set to black, alternatively could use 0
                    result.setRGB(i, j, 0xFF000000);
                else
                    //set to white, alternatively could use -1
                    result.setRGB(i, j, 0xFFFFFFFF);

                numVotes = 0;
            }//for 2
        }//for1

        return result;
    }//votingSystem
}//Thresholding

