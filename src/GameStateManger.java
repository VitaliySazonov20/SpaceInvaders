import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.Random;

public class GameStateManger implements Runnable {
    Panel panel;
    Ship ship;
    ArrayList<Alien> aliens;
    ArrayList<Bullet> bullets;
    ArrayList<AlienBullet> alienBullets;
    Random random = new Random();
    boolean running = true;
    boolean movingRight = true;
    boolean allCanRun = true;
    int gameTime = 750;
    int deathTime = 500;
    int bulletTime = 10;
    boolean gameOver = false;

    GameStateManger(Panel panel, Ship ship, ArrayList<Alien> aliens, ArrayList<Bullet> bullets, ArrayList<AlienBullet> alienBullets) {
        this.panel = panel;
        this.ship = ship;
        this.aliens = aliens;
        this.bullets = bullets;
        this.alienBullets = alienBullets;
        start();
    }

    public void start() {
        Thread thread = new Thread(this);
        thread.start();
    }

    @Override
    public void run() {
        long lastTime = System.currentTimeMillis();
        long bulletTimer = System.currentTimeMillis();
        while (running) {
            long currentTime = System.currentTimeMillis();
            if (currentTime - bulletTimer >= bulletTime) {
                bulletTimer = currentTime;
                Iterator<Bullet> iterator = bullets.iterator();
                while (iterator.hasNext()) {
                    Bullet bullet = iterator.next();
                    if (bullet.getY() > 0) {
                        bullet.move();
                        if (checkAlienBulletCollision(bullet)) {
                            iterator.remove();
                        }
                        if (checkAlienCollision(bullet)) {
                            iterator.remove();
                        }
                    } else {
                        iterator.remove(); // Safe removal using the iterator
                    }
                }
                Iterator<AlienBullet> alienBulletIterator = alienBullets.iterator();
                while (alienBulletIterator.hasNext()) {
                    AlienBullet bullet = alienBulletIterator.next();
                    if (bullet.y < 130) {
                        bullet.move();
                        if (checkShipCollision(bullet)) {
                            alienBulletIterator.remove();
                            ship.death();
                            gameOver = true;
                        }
                    } else alienBulletIterator.remove();
                }
                for (Alien alien : aliens) {
                    if (!gameOver) {
                        if (random.nextInt(5000) == 1) {
                            alien.fire();
                        }
                    }
                }
                panel.repaint();
            }
            if (currentTime - lastTime >= gameTime && !gameOver) {
                lastTime = currentTime;
                for (Alien alien : aliens) {
                    if (movingRight) {
                        if (alien.getX() == 200) {
                            allCanRun = false;
                            movingRight = false;
                        }
                    } else {
                        if (alien.getX() == 0) {
                            allCanRun = false;
                            movingRight = true;
                        }
                    }
                }
                if (allCanRun) {
                    for (Alien alien : aliens) {
                        alien.move(movingRight);
                    }
                } else {
                    for (Alien alien : aliens) {
                        if (alien.alive)
                            alien.setY(alien.getY() + 5);
                    }
                    allCanRun = true;

                }
                panel.repaint();
            }
            Iterator<Alien> alienIterator = aliens.iterator();
            while (alienIterator.hasNext()) {
                Alien alien = alienIterator.next();
                if (!alien.alive) {
                    if (currentTime - alien.deathTime >= deathTime)
                        alienIterator.remove();
                }
            }
            if (gameOver && alienBullets.isEmpty()) {
                stop();
            }
        }
        while (gameOver) {
            if(lastTime- ship.deathTime>=500){
                for (boolean[] row : ship.shipField) {
                    Arrays.fill(row, false);
                }
            }
            for (Alien alien : aliens) {
                alien.setAlienField();
            }
            panel.repaint();

            try {
                Thread.sleep(700);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            for (Alien alien : aliens) {
                alien.alternateAlien();
            }
            panel.repaint();
            try {
                Thread.sleep(700);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            lastTime=System.currentTimeMillis();

        }

    }

    private boolean checkAlienBulletCollision(Bullet bullet) {
        Iterator<AlienBullet> iterator = alienBullets.iterator();
        while (iterator.hasNext()) {
            AlienBullet alienBullet = iterator.next();
            if (Math.abs(bullet.getX() - alienBullet.getX()) <= 1 && Math.abs(bullet.getY() - alienBullet.getY()) <= 1) {
                iterator.remove();
                return true;
            }
        }
        return false;
    }

    public boolean checkShipCollision(AlienBullet bullet) {
        for (int i = 0; i < ship.shipField.length; i++) {
            for (int j = 0; j < ship.shipField[i].length; j++) {
                if (bullet.getX() == (ship.getX() + j) && bullet.getY() == (ship.getY() + i)) {
                    return true;
                }
            }
        }
        return false;
    }

    public boolean checkAlienCollision(Bullet bullet) {
        for (Alien alien : aliens) {
            for (int i = 0; i < alien.alienField.length; i++) {
                for (int j = 0; j < alien.alienField[i].length; j++) {
                    if (alien.alive && (bullet.getX() == (alien.getX() + j)) && (bullet.getY() == alien.getY() + i)) {
                        alien.death();
                        return true;
                    }
                }
            }
        }
        return false;
    }

    public void stop() {
        running = false;
    }
}

