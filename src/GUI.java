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
     * @param image: Orignal image to be added.
     * @param tabName: Algorithm method name used. This will be appended to the tab.
     *
     */
    public void addImages(BufferedImage image, String tabName) {
        JPanel panel = new JPanel(); //new pannel to be added to tabbedpane
        //set frame placement, layout and default operations.
        frame.getContentPane().add(panel, BorderLayout.CENTER);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLocationRelativeTo(null);
        frame.setResizable(true);
        //add new images to JPanel
        panel.add(new JLabel(new ImageIcon(image.getScaledInstance(800, 400, 0))));
        //add panel to a new tab with the given method name
        pane.add(panel, tabName);
        //finalize initialization: Add new tabs to frame, pack it, and set visible
        frame.add(pane);
        frame.setSize(800, 475);
        frame.setVisible(true);
    }//addImages
}//GUI
