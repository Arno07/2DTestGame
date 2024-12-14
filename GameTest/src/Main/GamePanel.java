package Main;

import Entity.Player;
import tile.TileManager;

import javax.swing.*;
import java.awt.*;

public class GamePanel extends JPanel implements Runnable {

    //Screen Settings
    final int originalTileSize = 16;
    final int scale = 3;

    public final int tileSize = originalTileSize * scale; // 48x48 tiles
    public final int maxScreenCol = 16;
    public final int maxScreenRow = 12;
    public final int screenWidth = tileSize*maxScreenCol;
    public final int screenHeight = tileSize*maxScreenRow;

    //World Settings
    public final int maxWorldCol = 50;
    public final int maxWorldRow = 50;
    public final int worldWidth = tileSize*maxWorldCol;
    public final int worldHeight = tileSize*maxWorldRow;


    TileManager tile = new TileManager(this);
    KeyHandler key = new KeyHandler();
    Thread gameThread;

    public Player player = new Player(this, key); ;

//    {
//        try {
//            player = new Player(this, key);
//        } catch (Exception e) {
//            throw new RuntimeException(e);
//        }
//    }




    int FPS = 60;

    public GamePanel()
    {
        this.setPreferredSize(new Dimension(screenWidth, screenHeight));
        this.setBackground(Color.BLACK );
        this.setDoubleBuffered(true);
        this.addKeyListener(key);
        this.setFocusable(true);
        gameThread = new Thread(this);
        gameThread.start();


    }

//    public void startGameThread(){
//        gameThread = new Thread(this);
//        gameThread.start();
//
//    }


//    @Override
//    public void run() {
//
//        while(gameThread!= null){
//
//            double drawinterval = 1000000000/FPS;
//            double nextDrawTime = System.nanoTime() + drawinterval;
//            update();
//
//            repaint();
//
//            try {
//                double remainingTime = nextDrawTime - System.nanoTime();
//                remainingTime = remainingTime/1000000;
//                if (remainingTime<0){
//                    remainingTime = 0;
//                }
//                Thread.sleep((long) remainingTime);
//                nextDrawTime += drawinterval;
//            } catch (InterruptedException e) {
//                throw new RuntimeException(e);
//            }
//        }
//    }
    public void update(){

            player.update();

    }

    public void paintComponent(Graphics g){

        super.paintComponent(g);

        Graphics2D g2 = (Graphics2D) g;

        tile.draw(g2);

        player.draw(g2);

        g2.dispose();
    }

    @Override
    public void run() {
        double drawInterval = 1000000000/FPS;
        double delta = 0;
        long lastTime = System.nanoTime();
        long currentTime;

        while(gameThread!=null){
            currentTime = System.nanoTime();
            delta += (currentTime-lastTime)/drawInterval;


            lastTime = currentTime;
            if(delta>=1){
                update();
                repaint();
                delta--;
            }
        }
    }
}
