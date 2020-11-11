import java.awt.Color;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.awt.Rectangle;
import javax.imageio.ImageIO;

public class BossFight extends GameObject { //Plan make every color inverse and hava boss shoot pellets and maybe rockets

    private final Handler handler;
    private HUD hud;
    private Spawn spawn;
    int delay = 0;
    public static int Health = 100; 

    public BossFight(final int x, final int y, final ID id, final Handler handler) {
        super(x, y, id);
        this.handler = handler;
        this.x = x;
        this.y = y;

        velY = 1.0f;
    }

    public Rectangle getBounds() { // bounds collision
        return new Rectangle((int) x + 15, (int) y + 35, 30, 20);

    }

    public void shoot() {
        //System.out.println(delay);
        handler.addObject(new BossGun(this.x, this.y, ID.BossGun, handler));   //fix locationg      
    }

    public void tick() {

        if (this.y == 25) {
            shoot();
        }

        if (this.y == 75) {//
            shoot();
        }

        if (this.y == 125) {
            shoot();
        }

        if (this.y == 175) {
            shoot();
        }

        if (this.y == 200f) { //stay decreasing fix
            velY = -1;

        }  
        if (this.y == -15f) {
            shoot();
            velY = 1;
        }
        
        this.y += velY;

    }

    public void render(final Graphics g) {

        BufferedImage image = null;
        try {
            image = ImageIO.read(new File("Game Assets/UFOForGame.png"));
        } catch (final IOException e) {
            e.printStackTrace();
        }

        g.drawImage(image, (int) x, (int) y, 100, 100, null);
        g.setColor(Color.red);
        g.drawRect((int) x + 15, (int) y + 5, 60, 60); // make collision bounds

    }

    



    
}