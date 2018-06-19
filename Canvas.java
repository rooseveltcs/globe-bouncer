import java.awt.*;
import java.awt.image.*;
import javax.swing.*;
import java.util.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.KeyAdapter;

public class Canvas {

   private JPanel panel;
   public JFrame frame;
   private Graphics g;
   
   public Canvas(int width, int height) {
      BufferedImage img = new BufferedImage(width, height, BufferedImage.TYPE_INT_ARGB);
      g = img.getGraphics();
      g.setColor(Color.BLACK);
      
      JLabel name = new JLabel();
      name.setIcon(new ImageIcon(img));
      panel = new JPanel(new FlowLayout());
      panel.setBackground(Color.WHITE);
      panel.setPreferredSize(new Dimension(width, height));
      panel.add(name);
      
      frame = new JFrame("Globe Bouncer");
      frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
      frame.setResizable(false);
      frame.add(panel);
      frame.pack();
      frame.setVisible(true);
   }
   
   public Graphics getGraphics() {
      return g;
   }
   
   public void setVisible(boolean visible) {
      frame.setVisible(visible);
   }
 
   public void repaint() {
      panel.repaint();
   }
   
   public void clear() {
      panel.removeAll(); 
      panel.revalidate();
      panel.repaint(); 
   }
}
      
      
      