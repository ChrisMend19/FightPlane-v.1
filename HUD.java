import java.awt.Color;
import java.awt.Graphics;
import java.io.File;
import java.io.IOException;
import java.awt.image.BufferedImage;
import javax.imageio.ImageIO;


public class HUD {

    public static int Health = 87; 
    private int Distance = 0;
    private int Level = 1;
    private int timer = 0;
    public static boolean set; // fix auto bounds
    private Handler handler;

    public void tick() {
        Health = (int) Game.clamp(Health, 0, 100);
        Distance ++;
        timer ++;
        if (Health == 1) {
            System.out.println(Distance);
        }
    }
    public void render(Graphics g) { //art
        
        BufferedImage image = null;
        try {
            image = ImageIO.read(new File("Game Assets/HUDForGame.PNG"));
        }
        catch (IOException e) {
                e.printStackTrace();
            }

        g.drawImage(image, -58, 209, 270, 160, null); //display        
        g.setColor(Color.green);
        g.fillRect(20, 265, ((Health) * 121) / 100, 24);
        g.setColor(Color.black);
        g.drawString("Score: " + Distance + "m", 280, 285);
        //g.drawString("Level: " + Level, 385, 285);
         //g.drawRect(15, 15, 200, 30);

        if (timer < 125)  {
            g.fillRect(240, 0, 5, Game.HEIGHT);
            g.drawString("Stay in Bounds", 75, 100);
        }

        if (set == true)  { //shows bounds
            g.fillRect(240, 0, 5, Game.HEIGHT);
            g.drawString("Stay in Bounds", 45, 100);
        }

        if (Health == 0) {
            //endGame();
            g.setColor(Color.black);
            g.fillRect(0, 0, Game.WIDTH, Game.HEIGHT);
            g.setColor(Color.white);
            g.drawString("GAMEOVER", 160, Game.HEIGHT/2);
        }
    }

    
    public void endGame() { //breaks game
        for(int i = 0; i < handler.object.size(); i ++) {
            handler.object.remove(i);
        }
    }

    public void setDistance(int Distance) {
        this.Distance = Distance;
    }
    
    public int getDistance() {
        return Distance;
    }

    public void setLevel(int Level) {
        this.Level = Level;
    }

    public int getLevel() {
        return Level;
    }
}