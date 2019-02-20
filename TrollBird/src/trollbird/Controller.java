package trollbird;

import java.awt.Graphics;
import java.util.LinkedList;

public class Controller {
    private TrollBird game;
    
    private LinkedList<Pipe> pipes = new LinkedList<Pipe>();
    private Pipe tempPipe;
    
    public Controller(TrollBird game) {
        this.game = game;
    }
    
    public void tick() {
        for(int i = 0; i < pipes.size(); i++) {
            tempPipe = pipes.get(i);
            
            if(tempPipe.getX() < 0 - 80) {
                removePipe(tempPipe);
            }
            
            tempPipe.tick();
        }
    }
    
    public void render(Graphics g) {
        for(int i = 0; i < pipes.size(); i++) {
            tempPipe = pipes.get(i);
            tempPipe.render(g);
        }
    }
    
    public void addPipe(Pipe block) {
        pipes.add(block);
    }
    
    public int getNumOfPipes() {
        return pipes.size();
    }
    
    public void removePipe(Pipe block) {
        pipes.remove(block);
    }
}
