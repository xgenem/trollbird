package trollbird;

import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.util.Random;

public class Bird {
    private double x, y;
    private double velY = 4;
    
    private TrollBird game;
    
    private BufferedImage image;
    private SpriteSheet ss;
    
    public Bird(double x, double y, TrollBird game) {
        this.x = x;
        this.y = y;
        
        this.game = game;
        ss = game.getSpriteSheet();
        image = ss.grabImage(1, 1, 32, 32);
    }
    
    public void tick() {
        y += velY;
    }
    
    public void render(Graphics g) {
        g.drawImage(image, (int)x, (int)y, null);
    }
    
    public void setX(double x) {
        this.x = x;
    }
    
    public void setY(double y) {
        this.velY = y;
    }
    
    public double getX() {
        return x;
    }
    
    public double getY() {
        return y;
    }
}
