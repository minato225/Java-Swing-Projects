package breakBreaker;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class Gameplay extends JPanel implements KeyListener , ActionListener  {
    private boolean play = false;
    private int score = 0;
    private int playerX = 310;
    private int totalBricks = 21;
    private final Timer time;
    private int ballposX = 120;
    private int ballposY = 350;
    private int ballXdir = -1;
    private int ballYdir = -2;
    private MapGenerator map;
    public Gameplay() {
        map =new MapGenerator(3,7);
        addKeyListener(this);
        setFocusable(true);
        setFocusTraversalKeysEnabled(false);
        int delay = 8;
        time =new Timer(delay,this);
        time.start();
    }
    public void paint(Graphics g) {
        //setBackGround
        g.setColor(Color.black);
        g.fillRect(1,1,692,592);
        //drawing map
        map.draw((Graphics2D)g);
        //score
        g.setColor(Color.white);
        g.setFont(new Font("serif",Font.BOLD,25));
        g.drawString("Score: "+score,570,30);
        //borders
        g.setColor(Color.yellow);
        g.fillRect(0,0,3,592);
        g.fillRect(0,0,692,3);
        g.fillRect(691,0,3,592);
        //ball
        g.fillOval(ballposX,ballposY,20,20);
        //win
        if (totalBricks<=0){
            play=false;
            ballXdir=0;
            ballYdir=0;
            g.setColor(Color.green);
            g.setFont(new Font("serif",Font.BOLD,30));
            g.drawString("Iluya won again): "+score,190,300);
            g.setFont(new Font("serif",Font.BOLD,20));
            g.drawString("Press Enter to Restart",230,350);
        }
        //fall
        if (ballposY>570){
            play=false;
            ballXdir=0;
            ballYdir=0;
            g.setColor(Color.red);
            g.setFont(new Font("serif",Font.BOLD,30));
            g.drawString("Iluya fall again, Score: "+score,190,300);
            g.setFont(new Font("serif",Font.BOLD,20));
            g.drawString("Press Enter to Restart",230,350);
        }
        //paddle
        g.setColor(Color.green);
        g.fillRect(playerX,550,100,8);
        g.dispose();
    }
    @Override
    public void actionPerformed(ActionEvent actionEvent) {
        time.start();
        if (play){
            if (new Rectangle(ballposX,ballposY,20,20)
                    .intersects(new Rectangle(playerX,550,100,8))) ballYdir= -ballYdir;
           A: for (int i = 0; i < map.map.length; i++)
                for (int j = 0; j < map.map[0].length; j++)
                    if(map.map[i][j] > 0){
                        int brickX = j * map.brickWidth + 80;
                        int brickY = i * map.brickHeight + 50;
                        int brickWidth = map.brickWidth;
                        int brickHeight = map.brickHeight;
                        Rectangle rect = new Rectangle(brickX,brickY,brickWidth,brickHeight);
                        Rectangle ballRect = new Rectangle(ballposX,ballposY,20,20);
                        if(ballRect.intersects(rect)){
                            map.setBrickValue(0,i,j);
                            totalBricks--;
                            score += 5;
                            if (ballposX + 19 <= rect.x || ballposY + 1 >= rect.x + rect.width) ballXdir = -ballXdir;
                            else                                                                ballYdir = -ballYdir;
                            break A;
                        }
                    }
            ballposX += ballXdir;
            ballposY += ballYdir;
            if (ballposX < 0 || ballposX > 670 )   ballXdir = -ballXdir;
            if (ballposY < 0)                      ballYdir = -ballYdir;
        }
        repaint();
    }
    @Override
    public void keyTyped(KeyEvent keyEvent) { }
    @Override
    public void keyPressed(KeyEvent keyEvent) { }
    @Override
    public void keyReleased(KeyEvent keyEvent) {
        if (keyEvent.getKeyCode() == KeyEvent.VK_RIGHT)
            if(playerX >= 600)  playerX = 600;
            else                moveRight();
        if (keyEvent.getKeyCode() == KeyEvent.VK_LEFT)
            if(playerX < 10) playerX = 10;
            else             moveLeft();
        if (keyEvent.getKeyCode() == KeyEvent.VK_ENTER)
            if(!play){
                play=true;
                ballposX=120;
                ballposY = 350;
                ballYdir = -2;
                ballXdir = -1;
                playerX = 310;
                score =0;
                totalBricks = 21;
                map = new MapGenerator(3,7);
                repaint();
            }
    }
   public void moveRight(){
        play = true;
        playerX += 20;
   }
   public void moveLeft() {
       play = true;
       playerX -= 20;
   }
}