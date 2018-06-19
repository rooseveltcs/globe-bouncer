import java.awt.*;

public class Platform {
   int x;
   int speed;
   int width;
   int height;
   
   public Platform(int position, int velocity, int w, int h) {
      x = position;
      speed = velocity;
      width = w;
      height = h;
   }
   
   public void drawPlatform(Canvas panel, Graphics g) {
      g.fillRect(x, height, width / 6, height / 80);
   }
}