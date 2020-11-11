import java.awt.Color;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.awt.Rectangle;
import javax.imageio.ImageIO;

public class BossGun extends GameObject {

    private final Handler handler;
    private HUD hud;

    public BossGun(final float x, final float y, final ID id, final Handler handler) {
        super(x, y, id);
        this.handler = handler;

        velX = -1.0f * Game.DistMult;
    }

    public Rectangle getBounds() { // bounds collision
        return new Rectangle((int) x, (int) y + 21, 32, 29);

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
            image = ImageIO.read(new File("Game Assets/BossGunForGame.png"));
        } catch (final IOException e) {
            e.printStackTrace();
        }

        g.drawImage(image, (int) x - 110, (int) y - 36, 170, 113, null);
        g.setColor(Color.red);
        g.drawRect((int) x, (int) y + 21, 32, 29); // make collision mathod that detects two rockets collided so
                                                        // you can make rockets not spawn on top of wach other

    }

    



    
}