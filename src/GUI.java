import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.awt.event.*;
/**
 * This "GUI" class is used to display images within JFrames and JTabbedpanes. This makes
 * the comparison between the orignal image, and the result after OCR methods are applied
 * easier to see.
 *
 * @author Daniel Speiser
 * OCR Project: License Plate Reader
 */
public class GUI extends JFrame {
    //JFrame to display images
    public JFrame frame = new JFrame();
    //JTabbedPane to have tabs for each different image algorithm
    public JTabbedPane pane = new JTabbedPane(JTabbedPane.TOP, JTabbedPane.SCROLL_TAB_LAYOUT);
    /**
     * This method takes two images - the original, and the changed image - and
     * adds them to the JTabbedPane and JFrame for easy viewing.
     *
     * @param image1: Orignal image to be added.
     * @param image2: Second image to be added.
     * @param methodName: Algorithm method name used. This will be appended to the tab.
     *
     */
    public void addImages(BufferedImage image1, BufferedImage image2, String methodName) {
        JPanel panel = new JPanel(); //new pannel to be added to tabbedpane
        //set frame placement, layout and default operations.
        frame.getContentPane().add(panel, BorderLayout.CENTER);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLocationRelativeTo(null);
        frame.setResizable(true);
        //add new images to JPanel
        panel.add(new JLabel(new ImageIcon(image1)));
        panel.add(new JLabel(new ImageIcon(image2)));
        //add panel to a new tab with the given method name
        pane.add(panel, methodName);
        //finalize initialization: Add new tabs to frame, pack it, and set visible
        frame.add(pane);
        frame.pack();
        frame.setVisible(true);
    }//addImages
}//GUI
