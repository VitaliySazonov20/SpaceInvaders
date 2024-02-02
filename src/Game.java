import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;


public class Game {
    JFrame window;
    JPanel screen;
    final int WIDTH=1493;
    final int HEIGHT= 1000;
    Game(){
        window=new JFrame();
        window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        window.setSize(WIDTH, HEIGHT);
        window.setLayout(null);
        window.setVisible(true);

        ArrayList<Bullet> bullets=new ArrayList<>();
        ArrayList<AlienBullet> alienBullets=new ArrayList<>();
        Ship ship =new Ship(bullets);
        ArrayList<Alien> aliens= new ArrayList<>();
        for(int i=0;i<9;i++){
            for(int j=0; j<4;j++) {
                Alien alien = new Alien(i * 15, j*10 +5,alienBullets);
                aliens.add(alien);
            }
        }

        screen=new Panel(ship, aliens,bullets,alienBullets);
        screen.setBounds(0,0,WIDTH,HEIGHT);
        screen.setBackground(new Color(200,200,200));
        window.add(screen);

        GameKeyListener gameKeyListener=new GameKeyListener(ship, (Panel) screen);
        window.addKeyListener(gameKeyListener);
        window.setFocusable(true);

        new GameStateManger((Panel) screen, ship, aliens,bullets,alienBullets);

    }
}
