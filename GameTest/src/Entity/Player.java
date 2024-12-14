package Entity;

import Main.GamePanel;
import Main.KeyHandler;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;

public class Player extends Entity{
    GamePanel gp;
    KeyHandler key;

    public final int screenX;
    public final int screenY;


    public Player(GamePanel gp, KeyHandler key)  {

        this.gp = gp;
        this.key = key;

        screenX = gp.screenWidth/2 - (gp.tileSize/2);
        screenY = gp.screenHeight/2 - (gp.tileSize/2);

        setDefaultValues();
        try {
            getPlayerImage();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }
    public void getPlayerImage() throws IOException {
        try {
            up1 = ImageIO.read(getClass().getResourceAsStream("/Player/boy_up_1.png"));
            up2 = ImageIO.read(getClass().getResourceAsStream("/Player/boy_up_2.png"));
            down1 = ImageIO.read(getClass().getResourceAsStream("/Player/boy_down_1.png"));
            down2 = ImageIO.read(getClass().getResourceAsStream("/Player/boy_down_2.png"));
            left1 = ImageIO.read(getClass().getResourceAsStream("/Player/boy_left_1.png"));
            left2 = ImageIO.read(getClass().getResourceAsStream("/Player/boy_left_2.png"));
            right1 = ImageIO.read(getClass().getResourceAsStream("/Player/boy_right_1.png"));
            right2 = ImageIO.read(getClass().getResourceAsStream("/Player/boy_right_2.png"));
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
    public void setDefaultValues(){

        worldX = gp.tileSize * 19;
        worldY = gp.tileSize * 18;
        speed = 4;
        direction = "down";
    }
    public void update(){

        if(key.upPressed || key.downPressed || key.leftPressed || key.rightPressed){
            if(key.upPressed){
                direction = "up";
                worldY -= speed;
            }
            else if (key.rightPressed) {
                direction = "right";
                worldX += speed;
            }
            else if (key.downPressed) {
                direction = "down";
                worldY += speed;
            }
            else if (key.leftPressed) {
                direction = "left";
                worldX -= speed;
            }
            spriteCounter++;
            if(spriteCounter>12){
                if(spriteNum==1){
                    spriteNum=2;
                } else if (spriteNum==2) {
                    spriteNum=1;
                }
                spriteCounter = 0;

            }
        }

    }
    public void draw(Graphics2D g2){
//        g2.setColor(Color.WHITE);
//
//        g2.fillRect(x,WorldY,gp.tileSize,gp.tileSize);

        BufferedImage image = null;

        switch (direction){
            case "up":
                if(spriteNum==1){
                    image = up1;
                }
                if (spriteNum==2) {
                    image = up2;
                }
                break;
            case "down":
                if(spriteNum==1){
                    image = down1;
                }
                if(spriteNum==2){
                    image = down2;
                }
                break;
            case "left":
                if (spriteNum==1){
                    image = left1;
                }
                if(spriteNum==2){
                    image = left2;
                }
                break;
            case "right":
                if(spriteNum==1){
                    image = right1;
                }
                if(spriteNum==2){
                    image = right2;
                }
                break;
        }
        g2.drawImage(image, screenX, screenY, gp.tileSize, gp.tileSize, null);
    }
}
