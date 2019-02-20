package trollbird;

import java.awt.Canvas;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.KeyEvent;
import java.awt.image.BufferStrategy;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;
import javax.swing.JFrame;

public class TrollBird extends Canvas implements Runnable {
    
    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private static int WIDTH = 320;
    private static int HEIGHT = WIDTH / 12 * 9;
    private static int SCALE = 2;
    private String TITLE = "Troll Bird";
    
    private Thread thread;
    private boolean running = false;
    
    private BufferedImage image = new BufferedImage(WIDTH, HEIGHT, BufferedImage.TYPE_INT_RGB);
    private BufferedImage birdy;
    private BufferedImage pipe;
    private BufferedImage background;
    
    private Controller c;
    private Bird bird;
    
    private SpriteSheet ss = null;
    private SpriteSheet pipeSheet;
    private BufferedImageLoader loader = new BufferedImageLoader();
    
    private int step = WIDTH * SCALE;
    private float something = 0.0f;
    
    public TrollBird() {
        super();
        addKeyListener(new KeyHandler(this));
        try {
            background = loader.loadImage("resources/background.png");
            pipe = loader.loadImage("resources/pipes.png");
            birdy = loader.loadImage("resources/sprite_sheet.png");
        } catch (IOException ex) {
            ex.printStackTrace();
        }
        c = new Controller(this);
        pipeSheet = new SpriteSheet(pipe);
        ss = new SpriteSheet(birdy);
        bird = new Bird(100, 100, this);
    }
    
    public synchronized void start() {
        if(running) return;
        
        running = true;
        thread = new Thread(this);
        thread.start();
    }
    
    public synchronized void stop() {
        if(!running) return;
        
        running = false;
        try {
            thread.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        
        System.exit(1);
    }
    
    public void run() {
        long lastTime = System.nanoTime();
        final double amountOfTicks = 60.0;
        double ns = 1000000000 / amountOfTicks;
        double delta = 0;
        int updates = 0;
        int frames = 0;       
        long timer = System.currentTimeMillis();
        
        while(running) {
            long now = System.nanoTime();
            delta += (now - lastTime) / ns;
            lastTime = now;
            if(delta >= 1) {
                ticks();
                updates++;
                delta--;
            }
            
            render();
            frames++;
            
            if(System.currentTimeMillis() - timer > 1000) {
                timer += 1000;
                //System.out.println(updates + " updates, fps " + frames);
                updates = 0;
                frames = 0;
            }
        }
        
        stop();
    }
    
    public void ticks() {
        step -= 4;
        bird.tick();
        c.tick();
        if(c.getNumOfPipes() < 2) {
            int width = WIDTH * SCALE;
            c.addPipe(new Pipe(width, 0, this));
            c.addPipe(new Pipe((width + width / 2), 0, this));
        }
    }
    
    public void render() {
        BufferStrategy bs = this.getBufferStrategy();
        
        if(bs == null) {
            createBufferStrategy(3);
            return;
        }
        
        Graphics2D g = (Graphics2D)bs.getDrawGraphics();
        
        g.drawImage(background, 0, 0, this);
        
        c.render(g);
        bird.render(g);
        
        g.dispose();
        bs.show();        
    }
    
    public void keyPressed(KeyEvent e) {
        int keycode = e.getKeyCode();
        
        if(keycode == e.VK_SPACE) {
            bird.setY(100);
        }  
    }
    
    public void keyReleased(KeyEvent e) {
        int keycode = e.getKeyCode();
        if(keycode == e.VK_SPACE) {
            bird.setY(100);
        }
    }
    
    public SpriteSheet getSpriteSheet() {
        return ss;
    }
    
    public SpriteSheet getPipeSheet() {
        return pipeSheet;
    }
    

    
    public static void main(String[] args) {
        TrollBird bird = new TrollBird();
        bird.setFocusable(true);
        
        // Canvas Size
        bird.setPreferredSize(new Dimension(WIDTH * SCALE, HEIGHT * SCALE));
        bird.setMaximumSize(new Dimension(WIDTH * SCALE, HEIGHT * SCALE));
        bird.setMinimumSize(new Dimension(WIDTH * SCALE, HEIGHT * SCALE));
        
        JFrame f = new JFrame(bird.TITLE);
        f.add(bird);
        f.pack();
        f.setResizable(false);
        f.setLocationRelativeTo(null);
        f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        f.setVisible(true);
        f.setFocusable(true);
        
        bird.start();
    }
}