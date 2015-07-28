import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.util.HashMap;
import java.util.Vector;

public class Training extends BaseMethods {

    public static HashMap<String, double[]> buildTrainingSet(File[] files) {
        GrayscaleConverter gc = new GrayscaleConverter();
        Thresholding th = new Thresholding();
        ConnectedComponents cc = new ConnectedComponents();

        try {
            for (int i = 0; i < files.length; i++) {
                BufferedImage image = ImageIO.read(files[i]); //read in original image
                BufferedImage grayscale = gc.toGrayMethod2(image);

                int maxEntropy = th.maximumEntropyThreshold(grayscale);
                int mean = (int) th.average(grayscale);
                int otsu = th.otsuThreshold(grayscale);
                int median = (int) th.median(grayscale);

                System.out.println("Threshold values for " + files[i].getName() + ":\nMean: " + mean + "\nMedian: " + median + "\nMax Entropy: " + maxEntropy + "\nOtsu: " + otsu + "\nFixed: " + 127);

                BufferedImage[] monochromeImages = new BufferedImage[5];
                monochromeImages[0] = th.grayToMono(grayscale, maxEntropy);
                monochromeImages[1] = th.grayToMono(grayscale, mean);
                monochromeImages[2] = th.grayToMono(grayscale, otsu);
                monochromeImages[3] = th.grayToMono(grayscale, median);
                monochromeImages[4] = th.grayToMono(grayscale, 127);

                BufferedImage votedMonochrome = th.votingSystem(monochromeImages);

                int[] connComps = cc.getConnectedComponents(votedMonochrome);

                connComps = cc.renameComponents(connComps);


            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        //temporary
        return null;
    }
}