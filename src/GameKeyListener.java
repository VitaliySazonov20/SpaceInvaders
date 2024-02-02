import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class GameKeyListener implements KeyListener {
    private final Ship ship;
    private final Panel panel;
    long lastTime;
    GameKeyListener(Ship ship, Panel panel){
        this.ship=ship;
        this.panel=panel;
        lastTime = System.currentTimeMillis();

    }

    @Override
    public void keyTyped(KeyEvent e) {

    }

    @Override
    public void keyPressed(KeyEvent e) {
        long currentTime= System.currentTimeMillis();
        int keyCode = e.getKeyCode();
        switch (keyCode) {
            case KeyEvent.VK_LEFT: {
                if(ship.getX()>0)
                    ship.setX(ship.getX() - 5);
                break;
            }
            case KeyEvent.VK_RIGHT:{
                if(ship.getX()<200)
                    ship.setX(ship.getX()+5);
                break;
            }
            case KeyEvent.VK_UP:{
                if(currentTime-lastTime>=400) {
                    lastTime = currentTime;
                    ship.fire();
                }
            }
        }
        panel.repaint();

    }

    @Override
    public void keyReleased(KeyEvent e) {

    }
}
