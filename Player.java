
import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;

public class Player extends GameObject {

    Handler handler;
    private HUD hud = new HUD();

    public Player(float x, float y, ID id, Handler handler) {
        super(x, y, id);     
        this.handler = handler;
    }
    
    public Rectangle getBounds() { //colission bounds
        return new Rectangle((int)(x+7), (int)y+10, 37, 30);
 
    }
    
    public void tick() {

        x += velX;
        y += velY;

        

        x = Game.clamp(x, -5, 200); //Game.WIDTH-45
        y = Game.clamp(y, -10, Game.HEIGHT-100);

        collision();
        if (this.x > 190f) { //show bounds if movedd forward
            
            hud.set = true;
        }
        else {
            hud.set = false;
        }
        

        //handler.addObject(new Trail(x, y, ID.Trail, Color.red, 0.01f, handler));

    }

    private void collision() {
        for (int i = 0; i < handler.object.size(); i++) {
            GameObject tempObject = handler.object.get(i);
            if (tempObject.getID() == ID.Rocket || tempObject.getID() == ID.SmartRocket || tempObject.getID() == ID.DoubleRocket || tempObject.getID() == ID.BossGun){
                if (getBounds().intersects(tempObject.getBounds())) {
                    //collision code
                    HUD.Health --;
                    //System.out.println(HUD.Health); print amount of heatlh
                }
            }
        }

    }

    public void render(Graphics g) { //image
        //File imageFile = new File("Game Assets/plane.png");
        BufferedImage image = null;
        try {
            image = ImageIO.read(new File("Game Assets/PlaneForGame.png"));
        }
        catch (IOException e) {
                e.printStackTrace();
            }
        g.drawImage(image, (int)x, (int)y, 50, 50, null);
        
        
        //bounds
        //g.setColor(Color.red);
        //g.drawRect(x+7, y+10, 37, 30);

    }

    public void playerShoot() {
        
    }



    
}