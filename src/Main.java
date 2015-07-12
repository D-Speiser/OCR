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
            public boolean accept(File file) {
                return !file.isHidden() && !file.getName().equals(".DS_Store");
            }
        });
        return files;
    }

    /**
     * "main" class runs any and all methods to initialize and complete our software's calculations.
     */
    public static void main(String[] args) {
        File trainingDirectory = new File(trainingPath);
        File dataDirectory = new File(dataPath);

        File[] trainingFiles = getFiles(trainingDirectory);
        File[] dataFiles = getFiles(trainingDirectory);

        //buildTrainingSet(trainingFiles);

        for (int i = 0; i < dataFiles.length; i++){
            if (dataFiles[i].isFile()){ //this line distinguishes files from directories
                //performOCR(dataFiles[i]);
            }
        }
    }//main
}//Main
