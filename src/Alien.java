import java.util.ArrayList;
import java.util.Arrays;

public class Alien {
    int x;
    int y;
    boolean alive=true;
    long deathTime;
    boolean breathing=false;
    boolean[][] alienField;
    ArrayList<AlienBullet> bullets;
    Alien(int x, int y,ArrayList<AlienBullet> bullets){
        this.x=x;
        this.y=y;
        this.bullets=bullets;
        alienField= new boolean[8][11];
        setAlienField();
    }

    public void move(boolean movingRight){
        if(alive)
        {
            if (movingRight)
                setX(getX() + 5);
            else
                setX(getX() - 5);

            if (breathing) {
                alternateAlien();
            } else {
                setAlienField();
            }
        }
        //System.out.println(getX());
    }
    public int getX(){
        return this.x;
    }

    public int getY() {
        return this.y;
    }

    public void setX(int x) {
        this.x = x;
    }

    public void setY(int y) {
        this.y = y;
    }

    public void setAlienField() {
        for (boolean[] booleans : alienField) {
            Arrays.fill(booleans, false);
        }
        alienField[0][2]=true;
        alienField[0][8]=true;

        alienField[1][3]=true;
        alienField[1][7]=true;

        for(int i=0;i<7;i++){
            alienField[2][i+2]=true;
        }

        alienField[3][1]=true;
        alienField[3][2]=true;
        alienField[3][4]=true;
        alienField[3][5]=true;
        alienField[3][6]=true;
        alienField[3][8]=true;
        alienField[3][9]=true;

        for (int i=0;i<alienField[0].length;i++){
            alienField[4][i]=true;
        }
        alienField[5][0]=true;
        for (int i=0;i<7;i++){
            alienField[5][i+2]=true;
        }
        alienField[5][10]=true;

        alienField[6][0]=true;
        alienField[6][2]=true;
        alienField[6][8]=true;
        alienField[6][10]=true;

        alienField[7][3]=true;
        alienField[7][4]=true;
        alienField[7][6]=true;
        alienField[7][7]=true;

        breathing=true;

    }
    public void alternateAlien(){
        alienField[3][0]=true;
        alienField[3][10]=true;

        alienField[2][0]=true;
        alienField[2][10]=true;

        alienField[1][0]=true;
        alienField[1][10]=true;

        alienField[5][0]=false;
        alienField[5][1]=true;
        alienField[5][9]=true;
        alienField[5][10]=false;

        alienField[6][0]=false;
        alienField[6][10]=false;

        alienField[7][1]=true;
        alienField[7][9]=true;
        alienField[7][3]=false;
        alienField[7][4]=false;
        alienField[7][6]=false;
        alienField[7][7]=false;

        breathing=false;

    }
    public void death(){
        deathTime=System.currentTimeMillis();
        for(boolean[] row: alienField){
            Arrays.fill(row,false);
        }
        alienField[0][2]=true;
        alienField[0][8]=true;

        alienField[1][3]=true;
        alienField[1][6]=true;
        alienField[1][7]=true;

        alienField[2][9]=true;

        alienField[3][0]=true;
        alienField[3][1]=true;
        alienField[3][2]=true;
        alienField[3][4]=true;
        alienField[3][7]=true;
        alienField[3][8]=true;

        alienField[4][3]=true;
        alienField[4][5]=true;

        alienField[5][2]=true;
        alienField[5][4]=true;
        alienField[5][7]=true;
        alienField[5][8]=true;

        alienField[6][1]=true;
        alienField[6][4]=true;
        alienField[6][6]=true;
        alienField[6][9]=true;

        alienField[7][6]=true;
        alive=false;
    }
    public void fire(){
        AlienBullet bullet = new AlienBullet(getX()+5,getY()+9);
        bullets.add(bullet);
    }
}
