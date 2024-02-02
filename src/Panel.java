import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

public class Panel extends JPanel {
    int cellSize=7;
    Ship ship;
    ArrayList<Alien> alienList;
    ArrayList<Bullet> bullets;
    ArrayList<AlienBullet> alienBullets;

    Panel(Ship ship, ArrayList<Alien> alienList, ArrayList<Bullet> bullets,ArrayList<AlienBullet> alienBullets ){
        this.alienList=alienList;
        this.ship=ship;
        this.bullets=bullets;
        this.alienBullets=alienBullets;
    }
    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        drawShip(g);
        drawAliens(g);
        drawBullets(g);
    }
    public void drawShip(Graphics graphics){
        for(int i=0;i<ship.shipField.length;i++){
            for(int j=0;j<ship.shipField[i].length;j++){
                if (ship.shipField[i][j]){
                    graphics.fillRect((ship.x +j)*cellSize,(ship.y+i)*cellSize,cellSize,cellSize);
                }
            }
        }
    }
    public void drawAliens(Graphics graphics){
        for(Alien alien: alienList){
            for(int i=0;i<alien.alienField.length;i++){
                for(int j=0;j<alien.alienField[i].length;j++){
                    if(alien.alienField[i][j]){
                        graphics.fillRect((alien.x+j)*cellSize,(alien.y+i)*cellSize,cellSize,cellSize);
                    }
                }
            }
        }
    }
    public void drawBullets(Graphics graphics){
        for(Bullet bullet:bullets){
            graphics.fillRect(bullet.getX()*cellSize,bullet.getY()*cellSize,cellSize,cellSize);
        }
        for(AlienBullet bullet:alienBullets){
            graphics.fillRect(bullet.getX()*cellSize,bullet.getY()*cellSize,cellSize,cellSize);
        }
    }
}
