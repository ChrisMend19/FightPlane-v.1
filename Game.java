import java.awt.Canvas;
import java.awt.*;
import java.awt.image.BufferStrategy;


public class Game extends Canvas implements Runnable {
    
    private static final long serialVersionUID = 5563736898020576581L;
    public static final int WIDTH = 480, HEIGHT = 320;

    private Thread thread;
    private boolean running = false;
    private Handler handler;
    private HUD hud;
    private Spawn spawner;
    public static float DistMult = 1.0f;


    public Game() {

        handler = new Handler();
        this.addKeyListener(new KeyInput(handler));

        

        new Window(WIDTH, HEIGHT, "Plane Survival", this);

        hud = new HUD();
        spawner = new Spawn(handler, hud);

        handler.addObject(new Player(50, 130, ID.Player,handler));
        //int x = r.nextInt(200);
        //handler.addObject(new Rocket(380, -20 + x, ID.Rocket));
        //handler.addObject(new Rocket(380, -20 + x + 25, ID.Rocket)); //-20min 200
        //handler.addObject(new Alien(50, 130, ID.Player));
            

        }
        //handler.addObject(new Rocket(380, 140, ID.Rocket));
        /*
        // randomize rockets
        for (int i = 0; i < 20; i++){
            handler.addObject(new Rocket(380, 140 + i, ID.Rocket));

        }
        */
        //multiple rockets
    

    public synchronized void start() {
        thread = new Thread(this);
        thread.start();
        running = true;
    }

    public synchronized void stop() {
        try {
            thread.join();
            running = false;
        }
        catch(Exception e) {
            e.printStackTrace();
        }
    }

    private void tick() {
        //hud.tick();
        
        //velInc = velInc + 0.001f;
        //System.out.println(velInc);
        handler.tick();
        hud.tick();
        spawner.tick();
        if (hud.getDistance() > 1000) {
            DistMult = 2;
        }

        

    }

    public void run() {
        this.requestFocus();
        long lastTime = System.nanoTime();
        double amountOfTicks = 60.0;
        double ns = 1000000000 / amountOfTicks;
        double delta  = 0;
        long timer = System.currentTimeMillis();
        int frames = 0;
        while (running) {
            long now = System.nanoTime();
            delta += (now - lastTime) / ns;
            lastTime = now;
            while (delta >= 1) {
                tick();
                delta --;
            }
            if (running) {
                render();
            }
            frames ++;

            if (System.currentTimeMillis() - timer > 1000) {
                timer += 1000;
                System.out.println("FPS: " + frames);
                frames = 0;
            }
        }
        stop();
    }

    public void render() {
        BufferStrategy bs = this.getBufferStrategy();
        if (bs == null) {
            this.createBufferStrategy(3);
            return;
        }

        Graphics g = bs.getDrawGraphics();
        g.setColor(Color.cyan);
        g.fillRect(0, 0, WIDTH, HEIGHT);
        g.setColor(Color.black);
        g.fillRect(0, 258, WIDTH, HEIGHT);
        g.setColor(Color.green);
        g.fillRect(0, 260, WIDTH, HEIGHT);
        
        

        
        handler.render(g);
        hud.render(g);

        g.dispose();
        bs.show();

    }

    public static float clamp(float var, float min, float max) { //sets limits
        if (var >= max) 
            return var = max;
        else if (var <= min)
            return var = min;
        else 
            return var;
    }
    public static void main(String[] args) {
        new Game();
    }


}