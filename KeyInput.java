import java.awt.*;
import java.awt.event.*;

public class KeyInput implements KeyListener {

boolean space = false;
boolean right;
boolean left;
Canvas panel;
GlobeBouncer globeBouncer;


   @Override
   public void keyTyped(KeyEvent e) {   
      //unused       
   }
   public KeyInput(Canvas panel, GlobeBouncer globeBouncer) {
      this.panel = panel;
      this.globeBouncer = globeBouncer;
   }
 
   @Override
   public void keyPressed(KeyEvent e) {      
      if (e.getKeyCode() == KeyEvent.VK_RIGHT) {
         right = true;                        
      }

      if (e.getKeyCode() == KeyEvent.VK_LEFT) {
         left = true;         
      }
   }

   @Override
   public void keyReleased(KeyEvent e) {
      if (e.getKeyCode() == KeyEvent.VK_RIGHT) {         
         right = false;                             
      }

      if (e.getKeyCode() == KeyEvent.VK_LEFT) {
         left = false;          
      } 
  
   } 
 
   public boolean getRight() {
      return right;
   }
 
   public boolean getLeft() {
      return left;
   }
  
}