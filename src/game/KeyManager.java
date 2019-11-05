package game;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class KeyManager implements KeyListener {
    private MainGame game;

    public KeyManager(MainGame Game) {
        game = Game;
    }

    @Override
    public void keyTyped(KeyEvent e) {
        //not implemented
    }

    @Override
    public void keyPressed(KeyEvent e) {
        if(e.getKeyCode() == KeyEvent.VK_UP) {
            game.move(Direction.UP);
        }
        if(e.getKeyCode() == KeyEvent.VK_RIGHT) {
            game.move(Direction.RIGHT);
        }
        if(e.getKeyCode() == KeyEvent.VK_DOWN) {
            game.move(Direction.DOWN);
        }
        if(e.getKeyCode() == KeyEvent.VK_LEFT) {
            game.move(Direction.LEFT);
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {
        //not implemented
    }
}
