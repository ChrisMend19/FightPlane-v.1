import java.awt.Color;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.awt.Rectangle;
import javax.imageio.ImageIO;

public class SmartRocket extends GameObject {

    private final Handler handler;
    private GameObject player;

    public SmartRocket(final float x, final float y, final ID id, final Handler handler) { // maybe add handler
        super(x, y, id);
        this.handler = handler;

        for (int i = 0; i < handler.object.size(); i++) {
            if (handler.object.get(i).getID() == ID.Player)
                player = handler.object.get(i);
        }
        // velX = -0;
    }

    public Rectangle getBounds() { // bounds collision
        return new Rectangle((int) x, (int) y, 20, 20);

    }

    public void tick() {

        if (this.x < -30){
            handler.removeObject(this);
        } 
        
        x += velX * 3.5;
        y += velY * 3.5;

        final float diffX = x - player.getX(); // set up if player isnt moving still follow
        final float diffY = y - player.getY();
        final float distance = (float) Math
                .sqrt((x - player.getX()) * (x - player.getX()) + (y - player.getY()) * (y - player.getY()));

        if (x > 200) {
            velX = (float) ((-1.0 / distance) * diffX);
            velY = (float) ((-1.0 / distance) * diffY);
        }

        if (x < 200) {

            if (x == 200) {
                final float xPath = (float) ((-1.0 / 200) * ((float) 200 - player.getX()));
                final float yPath = (float) ((-1.0 / 200) * ((float) 200 - player.getX())); 
                velX = xPath;
                velY = yPath;

            }
        
        
        
        }
        /*
        if (x < 0) {
            System.out.println("asdf");
            handler.removeObject(new SmartRocket(x, y, ID.SmartRocket,handler)); // fix

        }
        */

    }

    public void render(final Graphics g) {

        BufferedImage image = null;
        try {
            image = ImageIO.read(new File("Game Assets/SmartRocketForGame.png"));
        } catch (final IOException e) {
                e.printStackTrace();
            }
        
        g.drawImage(image, (int)x, (int)y, 100, 100, null);
        //g.setColor(Color.red);
        //g.drawRect((int)x + 15,(int)y + 35,30,20);
    }  



    
}