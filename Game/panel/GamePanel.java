package Game.panel;

import Game.entity.Player;
import Game.tiles.TileManager;

import javax.swing.*;
import java.awt.*;

public class GamePanel extends JPanel implements Runnable{
    // SCREEN SETTINGS
    private final int originalTileSize = 16;
    private final int scale = 4;



    public final int tileSize = originalTileSize * scale; // 48x48

    public final int maxWorldColumn = 50;

    public final int maxWorldRow = 50;

    public final int maxWorldWidth = maxWorldColumn * tileSize;
    public final int maxWorldHeight = maxWorldRow * tileSize;
    public final int maxScreenColumn = 12;
    public final int maxScreenRow = 12;

    public final int screenWidth = tileSize * maxScreenRow; // 16 * 48 = 768
    public final int screenHeight = tileSize * maxScreenColumn; // 12 * 48 = 576

    //FPS
    int FPS = 60;


    TileManager tileManager = new TileManager(this);
    Thread gameThread;
    KeyHandler keyHandler = new KeyHandler();
    public Player player = new Player(this, keyHandler);

    //Starting Player positions


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
        player.update();
    }
    public void paintComponent(Graphics g){
        super.paintComponent(g);
        Graphics2D d2 = (Graphics2D) g;
        tileManager.draw(d2);
        player.draw(g);
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
