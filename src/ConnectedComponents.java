import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.ArrayList;

/**
 * The "ConnectedComponents" class performs different connected components algorithms on
 * an image, or on an image array. This allows us to distinguish between "components" or "blobs"
 * within the foreground and within the background. These "blobs" (which are actually the characters
 * we need to distinguish and verify) will eventually be used to take several different measurements on
 * (such as Hu moments,foreground to background ratios within convex hulls, etc.), and used to compare
 * against our training set in order to obtain an OCR result.
 *
 * @author Daniel Speiser
 * OCR Project: License Plate Reader
 *
 */
public class ConnectedComponents extends BaseMethods {
    /**
     * This getDefaultComponentValues maps a 2d array/image into a 1d array of values.
     * These values are the "components", initially each set as default to its own pixel value.
     * These will be changed into connected components once the main cc algorithm is called.
     *
     * @param imageWidth the image width
     * @param imageHeight the image height
     * @return int [] containing the default components, such as [1,2,3,4,5,6,...,imageWidth*imageHeight]
     */
    public int [] getDefaultComponentValues(int imageWidth, int imageHeight) {
        int [] cc = new int [imageWidth * imageHeight];

        for (int i = 0; i < imageWidth; i++) {
            for (int j = 0; j < imageHeight; j++) {
                //set default values to the index of the element itself
                //this will change during cc algorithm
                cc[i * imageHeight + j] = i * imageHeight + j;
            }//for 2
        }//for 1
        return cc;
    }
    /**
     * This "getConnectedComponents" method is an 8 connectivity connected components algorithm which returns a
     * 1d array containing the connected components, based on their pixel values. This distinguishes components
     * that are a part of the foreground from the ones in the background (these will be separated later).
     * EX: The 'A' represented below will have all foreground '1' pixels as the foreground, and 4 separate background
     * components, consisting of the upper left triangle of '0's, the upper right, the middle portion, and the bottom.
     *
     * 00000100000
     * 00011111000
     * 00110001100
     * 01111111110
     * 11000000011
     *
     * @param image Image used to get connected components for.
     * @return int [] of connected components
     */
    public int [] getConnectedComponents(BufferedImage image) {
        //get image height and width for traversal
        int width = image.getWidth();
        int height = image.getHeight();

        //This is a TWO-PATH algorithm. First pass assigns default component values. The second calculates the equality of neighboring pixels.
        int [] cc = getDefaultComponentValues(width, height);

        boolean up, upRight, upLeft, left;
        for (int y = 0; y < height; y++) {
            for (int x = 0; x < width; x++) {
                up = y > 0;
                upRight = (x < width - 1) && (y > 0);
                upLeft = (y > 0) && (x > 0);
                left = (x > 0);

                if (up) {
                    if (getPixelValue(image, x, y) == getPixelValue(image, x, y - 1)) {
                        cc[y * width + x] = cc[(y - 1) * width + x];
                    }
                }
                if (upRight) {
                    if (getPixelValue(image, x, y) == getPixelValue(image, x + 1, y - 1)) {
                        cc[y * width + x] = cc[(y - 1) * width + x + 1];
                    }
                }
                if (upLeft) {
                    if (getPixelValue(image, x, y) == getPixelValue(image, x - 1, y - 1)) {
                        cc[y * width + x] = cc[(y - 1) * width + x - 1];
                    }
                }
                if (left) {
                    if (getPixelValue(image, x, y) == getPixelValue(image, x - 1, y)) {
                        cc [y * width + x] = cc[y * width + x - 1];
                    }
                }
            }
        }
        return cc;
    }

    static int getNumComponents(int [] cc) {
        int numComponents = 0;
        boolean [] visited = new boolean[cc.length];
        for (int i = 0; i < cc.length; i++) {
            if (!visited[cc[i]]) {
                visited[cc[i]] = true;
                numComponents++;
            }
        }
        return numComponents;
    }

    static int [] renameComponents(int [] cc) {
        int numComponents = getNumComponents(cc);

        if (numComponents == 1)
            return cc;

        int [] currentLabels = new int[numComponents];
        currentLabels[0] = cc[0];
        int count = 1;
        int falseCount = 0;

        for (int i = 1; i < cc.length; i++) {
            for (int j = 0; j < numComponents; j++) {
                if (cc[i] != currentLabels[j])
                    falseCount++;
            }
            if (falseCount == numComponents) {
                if (count >= numComponents)
                    break;
                currentLabels[count] = i;
                count++;
            }

            falseCount = 0;
        }

        for (int i = 1; i < cc.length; i++) {
            for (int j = 0; j < numComponents; j++) {
                if (cc[i] == currentLabels[j])
                    cc[i] = j;
            }
        }
        return cc;
    }

    static ArrayList<Point>[] getComponentPoints(int [] cc, int width, int numComponents) {
        ArrayList<Point>[] componentPoints = new ArrayList[numComponents];
        //System.out.println("(" + i / width + ", " + i % width +")");
        int i = 0;
        while (i < numComponents) {
            componentPoints[i] = new ArrayList<Point>();
            for (int j = 0; j < cc.length; j++) {
                if (cc[j] == i) {
                    componentPoints[i].add(new Point(j % width, j / width));
                }
            }
            i++;
        }

        return componentPoints;
    }

    static int getNumForegroundComponents(int [] cc, int [][] image) {//BufferedImage image) {
        int numComponents = 0;
        int width = image[0].length; //image.getWidth();
        boolean [] visited = new boolean[cc.length];

        for (int i = 0; i < cc.length; i++) {

            if (!visited[cc[i]] && image[i / width][i % width] == 1) {//getPixelValue(image, i % width, i / width) == 1) {
                visited[cc[i]] = true;
                numComponents++;
            }

            else if (!visited[cc[i]])
                visited[cc[i]] = true;

        }
        return numComponents;
    }

    static ArrayList<ArrayList<Point>> getForegroundComponentPoints(int [] cc, int [][] image) {//BufferedImage image) {
        int numComponents = 0;
        int width = image[0].length; //image.getWidth();
        boolean [] visited = new boolean[cc.length];
        ArrayList<ArrayList<Point>> componentPoints = new ArrayList();

        for (int i = 0; i < cc.length; i++) {

            if (!visited[cc[i]] && image[i / width][i % width] == 1) {//getPixelValue(image, i % width, i / width) == 1) {
                visited[cc[i]] = true;
                componentPoints.add(new ArrayList<Point>());
                for (int j = i; j < cc.length; j++) {
                    if (visited[cc[i]] && image[j / width][j % width] == 1)
                        componentPoints.get(numComponents).add(new Point(j / width, j % width));
                }
                numComponents++;
            }

            else if (!visited[cc[i]])
                visited[cc[i]] = true;

        }
        return componentPoints;
    }
}