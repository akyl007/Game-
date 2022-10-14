package Game;

import javax.swing.*;
import java.awt.*;

public class GamePanel extends JPanel implements Runnable{
    // SCREEN SETTINGS
    private final int originalTileSize = 16;
    private final int scale = 3;

    private final int tileSize = originalTileSize * scale; // 48x48
    private final int maxScreenColumn = 12;
    private final int maxScreenRow = 12;

    private final int screenWidth = tileSize * maxScreenRow; // 16 * 48 = 768
    private final int screenHeight = tileSize * maxScreenColumn; // 12 * 48 = 576

    //FPS
    int FPS = 60;

    Thread gameThread;
    KeyHandler keyHandler = new KeyHandler();

    //Starting Player positions
    int playerX = 100;
    int playerY = 100;
    int playerSpeed = 4;

    public GamePanel(){
        this.setPreferredSize(new Dimension(screenWidth,screenHeight));
        this.setBackground(Color.black);
        this.setDoubleBuffered(true);
        this.addKeyListener(keyHandler);
        this.setFocusable(true);
    }

    public void startGameThread(){
        gameThread = new Thread(this);
        gameThread.start();
    }

    public void update(){
        if(keyHandler.upPressed){
            playerY -= playerSpeed;
        } else if (keyHandler.downPressed) {
            playerY += playerSpeed;
        } else if (keyHandler.leftPressed) {
            playerX -= playerSpeed;
        } else if (keyHandler.rightPressed) {
            playerX += playerSpeed;
        }
    }
    public void paintComponent(Graphics g){
        super.paintComponent(g);
        Graphics2D d2 = (Graphics2D) g;

        d2.setColor(Color.white);
        d2.fillRect(playerX,playerY, tileSize, tileSize);
        d2.dispose();
    }

    double drawInterval = 1000000000/FPS; //0.01666
    double nextDrawtime = System.nanoTime() + drawInterval;
    @Override
    public void run() {

        while (gameThread != null){
            update();

            repaint();

            try {

                double remainingTime = nextDrawtime - System.nanoTime();
                remainingTime = remainingTime/1000000;

                if (remainingTime < 0){
                    remainingTime = 0;
                }

                Thread.sleep((long) remainingTime);
                nextDrawtime += drawInterval;
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
