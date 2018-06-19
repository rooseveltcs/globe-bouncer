import javax.swing.*;
import javax.swing.plaf.basic.BasicOptionPaneUI;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.ArrayList;
import java.util.Scanner;

public class GlobeBouncer extends JFrame {

   public static final int WIDTH = 500;
   public static final int HEIGHT = 600;
   public static boolean inProgress = true;
   public static ArrayList<BallDropper> ball = new ArrayList<BallDropper>();
   public static int frequency = 707; //lower is faster (707)
   public static boolean gameOverDrawn = false;

   Canvas panel;
   Graphics g;
   int platformX = WIDTH / 2;
   Platform platform;
   KeyInput input;
      
   public GlobeBouncer() {
      panel = new Canvas(WIDTH, HEIGHT);
      g = panel.getGraphics();     
      g.setColor(Color.RED);
      platform = new Platform(platformX - WIDTH / 12, 1 , WIDTH, HEIGHT * 9 / 10);           
      input = new KeyInput(panel, this);         
      panel.frame.addKeyListener(input);
      gamePlay();
   }
   
   public void gamePlay() {  
     
      int tickCount = 0;
      int score = 0;
      int strikes = 0; 
      boolean gameOver = true;
      
      while (inProgress) {    
         if (System.nanoTime() % (50000 / 3) == 0) {            
            if (gameOver) { 
               String stringScore = panelSetUp(platform, input, panel, g, score); 
               g.setColor(Color.BLACK);            
               gameOver = ballDropped(panel, g, strikes, gameOver);            
               g.drawString(stringScore, 15, 50);
               for (int x = 0; x < ball.size(); x++) {
                  ball.get(x).drawBall(panel, g);;
                  ball.get(x).update();
                  if (ball.get(x).y == HEIGHT * 9 / 10 - 10 && ball.get(x).x >= platform.x && 
                     ball.get(x).x <= platform.x + WIDTH / 6) {
                     ball.get(x).direction = false;
                     score++;
                     ball.get(x).bounces++;
                     if (ball.get(x).bounces > 4) {
                        ball.remove(x); 
                     }
                  } 
                  else if (ball.get(x).y == 80 && ball.get(x).direction == false) {
                     ball.get(x).direction = true;
                  }
                  if (ball.get(x).y == HEIGHT) {
                     strikes++;                
                  }
               }
               if (tickCount % frequency == 0) {
                  ball.add(new BallDropper((int)(Math.random() * (WIDTH - 40) + 20), 0, 300));            
                  g.setColor(Color.BLACK); 
               }
               panel.repaint();      
               tickCount++; 
            } else if (gameOverDrawn == false) {
               gameOver(panel, g, score);
               gameOverDrawn = true;
            }
            
         }               
      }       
   }
   
   public String panelSetUp(Platform platform, KeyInput input, Canvas panel, Graphics g, int score) {
      tick(platform, input);
      g.setColor(Color.WHITE);
      g.fillRect(0 ,0, WIDTH, HEIGHT);
      g.setColor(Color.RED);
      g.fillRect(WIDTH - 60, 10, 40, 50);
      g.fillRect(WIDTH - 110, 10, 40, 50);
      g.fillRect(WIDTH - 160, 10, 40, 50);
      platform.drawPlatform(panel, g);
      g.setFont(new Font("Impact", Font.PLAIN, 50));
      String stringScore = "" + score;
      return stringScore;
   }
   
   public void tick(Platform platform, KeyInput input) {
      if (input.getRight()) {
         platform.x += 3;
         if (platform.x > WIDTH - WIDTH / 6 + 40) {
            platform.x = -40;
         }
      }       
      if (input.getLeft()) {
         platform.x -= 3;
         if (platform.x < -40) {
            platform.x =  WIDTH - WIDTH / 6 + 40;
         }
      }
   }
   
   public boolean ballDropped(Canvas panel, Graphics G, int strikes, boolean gameOver) {
      gameOver = true;
      if (strikes >= 1) {
         g.drawString("X", WIDTH - 52, 55);
      }
      if (strikes >= 2) {
         g.drawString("X", WIDTH - 102, 55);
      }
      if (strikes >= 3) {
         g.drawString("X", WIDTH - 152, 55);
         gameOver = false;         
      }
      return gameOver;
   }
   
   public void gameOver (Canvas panel, Graphics g, int score) {
      g.setColor(new Color(0, 0, 0, 127));
      g.fillRect(0, 0, WIDTH, HEIGHT);
      g.setColor(Color.WHITE);
      g.setFont(new Font("Impact", Font.PLAIN, 75));
      g.drawString("GAME OVER!", WIDTH / 2 - 175, HEIGHT / 2 - 50);  
      g.setFont(new Font("Impact", Font.PLAIN, 40)); 
      g.drawString("YOUR SCORE: " + score, WIDTH / 2 - 175, HEIGHT / 2 + 20);
      panel.repaint();  
   }         
}