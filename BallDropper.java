import java.awt.*;

public class BallDropper {

   int x;
   public int y;
   int timer;
   int bounces;
   public boolean direction;
   
   public BallDropper(int positionX, int positionY, int dropSpeed) {
      x = positionX;
      y = positionY;
      timer = dropSpeed;
      bounces = 0;
      direction = true;
   }
   
   public void drawBall(Canvas panel, Graphics g) {
      double color = Math.random();
      if (color <= 0.1) {
         g.setColor(Color.RED);
      } else if (color > 0.1 && color <= 0.2) {
         g.setColor(Color.BLUE);
      } else if (color > 0.2 && color <= 0.3) {
         g.setColor(Color.YELLOW);
      } else if (color > 0.3 && color <= 0.4) {
         g.setColor(Color.ORANGE);            
      } else if (color > 0.4 && color <= 0.5) {
         g.setColor(Color.GREEN);      
      } else if (color > 0.5 && color <= 0.7) {
         g.setColor(Color.PINK);      
      } else if (color > 0.8 && color <= 0.9) {
         g.setColor(Color.MAGENTA);      
      } else {
          g.setColor(Color.CYAN);     
      }
      g.fillOval(x, y, 10, 10);
   }   

   public void update() {
      if (direction == true) {
         y++;
      } else {
         y--;      
      }
   }   
}