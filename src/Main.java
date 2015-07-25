import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileFilter;
/**
 *
 * This "Main" class is ran, which makes the initial method calls. This begins
 * the software and allows for calling the "main" functionality of the program.
 *
 * @author Daniel Speiser
 * OCR Project: License Plate Reader
 *
 */
public class Main {

    private static final String trainingPath = "./training";
    private static final String dataPath = "./data";

    private static File[] getFiles(File fileDirectory) {
        File[] files = fileDirectory.listFiles(new FileFilter() {
            @Override
            // hard coded file reader for Mac. TODO: implement file filter for all platforms
            public boolean accept(File file) {
                return !file.isHidden() && !file.getName().contains("DS_Store");
            }
        });
        return files;
    }

    public static void performOCR(File file) {
        GUI gui = new GUI();
        GrayscaleConverter gc = new GrayscaleConverter();
        Thresholding th = new Thresholding();

        try {
            BufferedImage image = ImageIO.read(file);
            //Min Decomposition grayscale conversion is best for our purposes. Use Min Decomp.
            /*
            * Instantiate and initialize new GUI
            * Each grayscale image generated by the different grayscale algorithms
            * is next used for thresholding (using several methods), eventually to
            * be turned into monochrome images.
            */
            BufferedImage grayscale = gc.toGrayMethod4b(image); //MIN Decomposition Grayscale
            int maxEntropy = th.maximumEntropyThreshold(grayscale);
            int mean = (int) th.average(grayscale);
            int otsu = th.otsuThreshold(grayscale);
            int median = (int) th.median(grayscale);
            System.out.println("Threshold values for " + file.getName() + ":\nMean: " + mean + "\nMedian: " + median + "\nMax Entropy: " + maxEntropy + "\nOtsu: " + otsu + "\nFixed: " + 127);
            BufferedImage [] monochromeImages = new BufferedImage[5];
            monochromeImages[0] = th.grayToMono(grayscale, maxEntropy);
            monochromeImages[1] = th.grayToMono(grayscale, mean);
            monochromeImages[2] = th.grayToMono(grayscale, otsu);
            monochromeImages[3] = th.grayToMono(grayscale, median);
            monochromeImages[4] = th.grayToMono(grayscale, 127);

            BufferedImage votedMonochrome = th.votingSystem(monochromeImages);

//            TODO: Add to GUI, perform measurements, get training sets, and get/analyze results.
            gui.frame.setTitle("");
            //add panels and images to GUI
            gui.addImages(image, "Original");
            gui.addImages(grayscale, "Grayscale");
            gui.addImages(votedMonochrome, "Monochrome");


        } catch(Exception e) {
            e.printStackTrace();
        }

    }

    /**
     * "main" class runs any and all methods to initialize and complete our software's calculations.
     */
    public static void main(String[] args) {
        File trainingDirectory = new File(trainingPath);
        File dataDirectory = new File(dataPath);

        File[] trainingFiles = getFiles(trainingDirectory);
        File[] dataFiles = getFiles(dataDirectory);

        //buildTrainingSet(trainingFiles);

        for (int i = 0; i < dataFiles.length; i++){
            if (dataFiles[i].isFile()){ //this line distinguishes files from directories
                performOCR(dataFiles[i]);
            }
        }
    }//main
}//Main
