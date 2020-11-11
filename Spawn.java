import java.util.Random;

public class Spawn {

    private Handler handler;
    private HUD hud;
    private Rocket rocket;
    private int distanceKeep;
    private Random r;
    

    public Spawn(Handler handler, HUD hud)  {
        this.hud = hud;
        this.handler = handler;

        
    }

    public void tripleSpawnRocket() {
        
        int randY = r.nextInt(230);
        //fix lag
        
        handler.addObject(new Rocket(470, -30 + randY, ID.Rocket, handler));
        if (randY > 30){
            //System.out.println("1");
            //handler.addObject(new Rocket(470, randY - 60, ID.Rocket, handler));
            handler.addObject(new Rocket(470, randY - 60, ID.Rocket, handler)); //if 2nd rocket spawns out top bounds
        }
        else {
            //System.out.println("2");
            handler.addObject(new Rocket(470, 30 + randY, ID.Rocket, handler));
        }
        if (randY < 200){
            //System.out.println("3");
            handler.addObject(new Rocket(470, randY, ID.Rocket, handler)); // if rocket 2nd spawns under hud
        }
        else {
            //System.out.println("4");
            handler.addObject(new Rocket(470, -90 + randY, ID.Rocket, handler));
        }
        
    }

    
    public void tick() {
        
        distanceKeep ++;
        r = new Random();
        int randY = r.nextInt(230);
        int randY1 = r.nextInt(104);
        int range = 200 - 130 + 1;
        int randY2 = r.nextInt(range) + 130;
        
        //int randomNum =  rn.nextInt(range) + minimum;
        //minimum + rn.nextInt(maxValue - minvalue + 1)
        
        // if rocket reaches x:0 spawn new rocket maybe by accecssing a var x idk
        
        if (distanceKeep >= 50) {
            distanceKeep = 0;
            hud.setLevel(hud.getLevel() + 1); 
            if (hud.getDistance() % 10 == 0 && hud.getLevel() < 10) {
                handler.addObject(new Rocket(470, -30 + randY, ID.Rocket, handler));
                handler.addObject(new Rocket(470, -30 + randY, ID.Rocket, handler));
                //handler.addObject(new BossGun(470, 90, ID.BossGun, handler));
            }
            
            if (hud.getDistance() % 10 == 0 && hud.getLevel() > 10 && hud.getLevel() < 30) {  
                handler.addObject(new Rocket(470, -30 + randY, ID.Rocket, handler));
                handler.addObject(new DoubleRocket(470, -30 + randY1, ID.DoubleRocket, handler));
                handler.addObject(new DoubleRocket(470, randY2, ID.DoubleRocket, handler));
            }

            if (hud.getDistance() % 20 == 0 && hud.getLevel() > 10 && hud.getLevel() < 30) {  
                handler.addObject(new DoubleRocket(470, -30 + randY1, ID.DoubleRocket, handler));
                handler.addObject(new DoubleRocket(470, randY2, ID.DoubleRocket, handler));
            }

            if (hud.getDistance() % 10 == 0 && hud.getLevel() > 20 && hud.getLevel() < 30) {
                handler.addObject(new SmartRocket(470, -30 + r.nextInt(230), ID.SmartRocket, handler));
                //handler.removeObject();
            }
            if (hud.getDistance() % 10 == 0 && hud.getLevel() > 25 && hud.getLevel() < 30) {
                handler.addObject(new SmartRocket(470, -30 + r.nextInt(230), ID.SmartRocket, handler));
            }

            if (hud.getDistance() % 10 == 0 && hud.getLevel() == 31) {
                handler.addObject(new BossFight(370, 100, ID.BossFight, handler));
                //handler.removeObject();
                
 
            }
            
                //if a rocket leaves the screen spawn another
            
        }

    }
    
}