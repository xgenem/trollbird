package trollbird;

import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.util.Random;

public class Pipe {
    private double x, y;
    
    private TrollBird game;
    
    private BufferedImage image;
    private SpriteSheet ss;
    
    public Pipe(double x, double y, TrollBird game) {
        this.x = x;
        this.y = y;
        
        this.game = game;
        ss = game.getPipeSheet();
        
        Random rand = new Random();
        int col = rand.nextInt(7) + 1;
        image = ss.grabImage(col, 1, 80, 442);
    }
    
    public void tick() {
        x -= 4;
    }
    
    public void render(Graphics g) {
        g.drawImage(image, (int)x, (int)y, null);
    }
    
    public void setX(double x) {
        this.x = x;
    }
    
    public void setY(double y) {
        this.y = y;
    }
    
    public double getX() {
        return x;
    }
    
    public double getY() {
        return y;
    }
}
