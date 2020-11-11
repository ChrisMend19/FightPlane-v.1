import java.awt.Color;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.awt.Rectangle;
import javax.imageio.ImageIO;

public class Rocket extends GameObject {

    private final Handler handler;
    private HUD hud;

    public Rocket(final int x, final int y, final ID id, final Handler handler) {
        super(x, y, id);
        this.handler = handler;

        velX = -1.7f * Game.DistMult;
    }

    public Rectangle getBounds() { // bounds collision
        return new Rectangle((int) x + 15, (int) y + 35, 30, 20);

    }

    public void tick() {

        if (this.x < -30) {
            handler.removeObject(this);
        }
        // speedUp = speedUp + 0.01f;

        x += velX;
        y += velY;

    }

    public void render(final Graphics g) {

        BufferedImage image = null;
        try {
            image = ImageIO.read(new File("Game Assets/RocketForGame.png"));
        } catch (final IOException e) {
            e.printStackTrace();
        }

        g.drawImage(image, (int) x, (int) y, 100, 100, null);
        //g.setColor(Color.red);
        //g.drawRect((int) x + 15, (int) y + 35, 30, 20); // make collision mathod that detects two rockets collided so
                                                        // you can make rockets not spawn on top of wach other

    }

    



    
}