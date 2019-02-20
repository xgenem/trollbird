package trollbird;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class KeyHandler extends KeyAdapter {
    private TrollBird game;
    
    public KeyHandler(TrollBird game) {
        this.game = game;
    }
    
    public void KeyPressed(KeyEvent e) {
        game.keyPressed(e);
    }
    
    public void KeyReleased(KeyEvent e) {
        game.keyReleased(e);
    }
}
