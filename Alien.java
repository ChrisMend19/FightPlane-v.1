import java.awt.Color;
import java.awt.Graphics;
//import java.awt.image.BufferedImage;
//import java.io.File;
//import java.io.IOException;
//import java.util.List;
import java.awt.Rectangle;
//import javax.imageio.ImageIO;

//import sun.reflect.generics.tree.IntSignature;

public class Alien extends GameObject {


    public Alien(int x, int y, ID id) {
        super(x, y, id);

         velX = -3;
    }

    public Rectangle getBounds() { //bounds collision
        return new Rectangle((int)x+15,(int)y+35,30,20);
 
    }

    public void tick() {

        

    }
   
    public void render(Graphics g) {
        /*
        BufferedImage image = null;
        try {
            image = ImageIO.read(new File("Game Assets/RocketForGame.png"));
        }
        catch (IOException e) {
                e.printStackTrace();
            }
        
        g.drawImage(image, x, y, 100, 100, null);
        */
        //List<> myList = new ArrayList<>();
        g.setColor(Color.red);
        g.drawRect((int)x+119,(int)y-130,140,200);
        g.drawRect((int)x+276,(int)y-130,140,200);
        g.drawRect((int)x-38,(int)y-130,140,200);
        //g.tpye
        //myList.add(g);
        
        
        //int a1 = //set up a random number 1-3 to pick a which rect not to fill
    }



    
}