package trollbird;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class KeyHandler implements KeyListener {
    private TrollBird game;
    
    public KeyHandler(TrollBird game) {
    	System.out.println("keyhandler");
        this.game = game;
    }

	@Override
	public void keyPressed(KeyEvent e) {
    	// do nothing
	}

	@Override
	public void keyReleased(KeyEvent e) {
    	game.keyPressed(e);		
	}

	@Override
	public void keyTyped(KeyEvent arg0) {
		// TODO Auto-generated method stub
		
	}
}
