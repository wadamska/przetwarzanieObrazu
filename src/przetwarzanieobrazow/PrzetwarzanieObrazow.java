
package przetwarzanieobrazow;
import java.awt.*;
import java.awt.event.*;
import java.awt.image.*;
import javax.swing.*;
import java.io.*;
import java.net.*;
import javax.imageio.*;


/**
 *
 * @author student
 */
public class PrzetwarzanieObrazow extends JFrame {

 BufferedImage image;
 JLabel promptLabel;
 JTextField prompt;
 JButton promptButton;
 JFileChooser fileChooser;
 JButton loadButton;
 JButton processingButton;
 JScrollPane scrollPane;
 JLabel imgLabel;

 public PrzetwarzanieObrazow() {
 super("Image processing");
 setDefaultCloseOperation(EXIT_ON_CLOSE);
 Container contentPane = getContentPane();
 JPanel inputPanel = new JPanel();
 promptLabel = new JLabel("Filename:");
 inputPanel.add(promptLabel);
 prompt = new JTextField(20);
 inputPanel.add(prompt);
 promptButton = new JButton("Browse");
 inputPanel.add(promptButton);
 contentPane.add(inputPanel, BorderLayout.NORTH);
 fileChooser = new JFileChooser();
 promptButton.addActionListener(
 new ActionListener() {
 public void actionPerformed(ActionEvent e) {
 int returnValue =
 fileChooser.showOpenDialog(null);
 if (returnValue ==
 JFileChooser.APPROVE_OPTION) {
 File selectedFile =
 fileChooser.getSelectedFile();
 if (selectedFile != null) {
 prompt.setText(selectedFile.getAbsolutePath());
 }
 }
 }
 }
 );

 imgLabel = new JLabel();
 scrollPane = new JScrollPane(imgLabel);
 scrollPane.setPreferredSize(new Dimension(700,500));
 contentPane.add(scrollPane, BorderLayout.CENTER);

 JPanel outputPanel = new JPanel();
 loadButton = new JButton("Load");
 outputPanel.add(loadButton);
 loadButton.addActionListener(
 new ActionListener() {
 public void actionPerformed(ActionEvent e) {
 try {
 String name = prompt.getText();
 File file = new File(name);
 if (file.exists()) {
 image = ImageIO.read(file.toURL());
 if (image == null) {
 System.err.println("Invalid input file format");
 } else {
 imgLabel.setIcon(new ImageIcon(image));
 }
 } else {
 System.err.println("Bad filename");
 }
 } catch (MalformedURLException mur) {
 System.err.println("Bad filename");
 } catch (IOException ioe) {
 System.err.println("Error reading file");
 }
 }
 }
 );

 processingButton = new JButton("Processing");
 outputPanel.add(processingButton);
 processingButton.addActionListener(
 new ActionListener() {
 public void actionPerformed(ActionEvent e) {
 Processing(image);
imgLabel.setIcon(new ImageIcon(image));
 }
 });

 contentPane.add(outputPanel, BorderLayout.SOUTH);
 }


 private static void Processing(BufferedImage img)
 {
 double gray;
 int w=img.getWidth(null);
 int h=img.getHeight(null);
 for(int x=0;x<w;x++)
 for(int y=0;y<h;y++)
 {
 
 int rgb=img.getRGB(x, y);
 int a=(rgb&0xff000000)>>>24;
 int r=(rgb&0x00ff0000)>>>16;
 int g=(rgb&0x0000ff00)>>>8;
 int b=rgb&0x000000ff;

 //gray=0.299*r + 0.587*g +0.114*b;
// r=(int)gray;
// g=(int)gray;
// b=(int)gray;
 //tu można modyfikować wartość kanałów

 //binaryzacja zad.3 r= r>200?255:0; 
//if(gray > 200)
//{
//r=255;
//g=255;
//b=255;
//}
//if(gray< 200)
//{
//r=0;
//g=0;
//b=0;
//}
 
// negatyw zad.4
r=255-r;
g=255-g;
b=255-b;

//kontrast zad.5


 //zapis kanałów
 int RGB=b|(g<<8)|(r<<16)|(a<<24);
 img.setRGB(x, y, RGB);
  }
 }
 public static void main(String args[]) {
 JFrame frame = new PrzetwarzanieObrazow();
 frame.pack();
 frame.show();
 }
}

