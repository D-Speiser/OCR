import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
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
    public JFrame frame = new JFrame("OCR Software - By: Daniel Speiser");
    //JTabbedPane to have tabs for each different image algorithm
    public JTabbedPane pane = new JTabbedPane(JTabbedPane.TOP, JTabbedPane.SCROLL_TAB_LAYOUT);
    /**
     * This method takes an image - the original, grayscale, or monochromic image - and
     * adds it to the JTabbedPane and JFrame for easy viewing.
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
        frame.setResizable(true);
        //add new images to JPanel
        panel.add(new JLabel(new ImageIcon(image.getScaledInstance(800, 400, 0))));
        //add panel to a new tab with the given method name
        pane.add(panel, tabName);
        //finalize initialization: Add new tabs to frame, pack it, and set visible
        frame.add(pane);
        frame.setSize(800, 475);
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }//addImages
    /**
     * This method takes three images - the original, the grayscale, and the monochrome image - and
     * adds them to the JTabbedPane and JFrame for easy viewing.
     *
     * @param image: Orignal image to be added.
     * @param grayscale: Orignal image to be added.
     * @param monochrome: Orignal image to be added.
     * @param tabName: Algorithm method name used. This will be appended to the tab.
     *
     */
    public void addImages(BufferedImage image, BufferedImage grayscale, BufferedImage monochrome, String tabName) {
        JPanel panel = new JPanel(); //new panel to be added to tabbed pane
        //set frame placement, layout and default operations.
        frame.getContentPane().add(panel, BorderLayout.CENTER);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setResizable(true);
        frame.setSize(1300, 300);

        panel.setLayout(new GridLayout(1, 3)); // 3 images to add, set 1 x 3 layout
        panel.setBorder(BorderFactory.createEmptyBorder(-2, -2, -2, -2)); // add 2 pixels of space between image and border
        JLabel original = new JLabel(new ImageIcon(image.getScaledInstance(400, 200, 0))); // create label with original image
        JLabel gray = new JLabel(new ImageIcon(grayscale.getScaledInstance(400, 200, 0))); // create label with gray image
        JLabel mono = new JLabel(new ImageIcon(monochrome.getScaledInstance(400, 200, 0))); // create label with mono image
        // set image borders and titles
        original.setBorder(BorderFactory.createTitledBorder("Original"));
        gray.setBorder(BorderFactory.createTitledBorder("Grayscale"));
        mono.setBorder(BorderFactory.createTitledBorder("Monochrome (Binary)"));
        //add image labels to new JPanel
        panel.add(original);
        panel.add(gray);
        panel.add(mono);

        pane.add(panel, tabName); //add panel to a new tab with the given tab name
        frame.add(pane); //add pane to jframe
        frame.setLocationRelativeTo(null); // centered on display monitor
        frame.setVisible(true); // set visible allows user to see gui
    }//addImages
}//GUI
