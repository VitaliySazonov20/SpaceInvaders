import java.util.ArrayList;
import java.util.Arrays;

public class Ship {
    int x=100;
    int y=125;

    boolean[][] shipField;
    ArrayList<Bullet> bullets;
    long deathTime;

    Ship(ArrayList<Bullet> bullets){
        this.bullets=bullets;
        shipField= new boolean[9][11];
        for (boolean[] booleans : shipField) {
            Arrays.fill(booleans, false);
        }
        setShipField();
    }
    public void fire(){
        Bullet bullet = new Bullet(getX()+5,getY());
        bullets.add(bullet);
    }

    public void setShipField() {
        shipField[0][5]=true;

        shipField[1][5]=true;

        shipField[2][4]=true;
        shipField[2][5]=true;
        shipField[2][6]=true;

        shipField[3][2]=true;
        shipField[3][4]=true;
        shipField[3][5]=true;
        shipField[3][6]=true;
        shipField[3][8]=true;

        shipField[4][2]=true;
        shipField[4][4]=true;
        shipField[4][5]=true;
        shipField[4][6]=true;
        shipField[4][8]=true;

        shipField[5][0]=true;
        shipField[5][2]=true;
        shipField[5][3]=true;
        shipField[5][4]=true;
        shipField[5][5]=true;
        shipField[5][6]=true;
        shipField[5][7]=true;
        shipField[5][8]=true;
        shipField[5][10]=true;

        shipField[6][0]=true;
        shipField[6][2]=true;
        shipField[6][3]=true;
        shipField[6][4]=true;
        shipField[6][5]=true;
        shipField[6][6]=true;
        shipField[6][7]=true;
        shipField[6][8]=true;
        shipField[6][10]=true;

        shipField[7][0]=true;
        shipField[7][1]=true;
        shipField[7][2]=true;
        shipField[7][3]=true;
        shipField[7][4]=true;
        shipField[7][5]=true;
        shipField[7][6]=true;
        shipField[7][7]=true;
        shipField[7][8]=true;
        shipField[7][9]=true;
        shipField[7][10]=true;

        shipField[8][0]=true;
        shipField[8][1]=true;
        shipField[8][2]=true;
        shipField[8][3]=true;
        shipField[8][4]=true;
        shipField[8][5]=true;
        shipField[8][6]=true;
        shipField[8][7]=true;
        shipField[8][8]=true;
        shipField[8][9]=true;
        shipField[8][10]=true;
    }
    public int getX(){
        return this.x;
    }
    public void setX(int x){
        this.x=x;
    }
    public int getY(){
        return this.y;
    }
    public void death(){
        for(boolean[] row: shipField){
            Arrays.fill(row,false);
        }
        shipField[0][2]=true;
        shipField[0][8]=true;

        shipField[1][3]=true;
        shipField[1][6]=true;
        shipField[1][7]=true;

        shipField[2][9]=true;

        shipField[3][0]=true;
        shipField[3][1]=true;
        shipField[3][2]=true;
        shipField[3][4]=true;
        shipField[3][7]=true;
        shipField[3][8]=true;

        shipField[4][3]=true;
        shipField[4][5]=true;

        shipField[5][2]=true;
        shipField[5][4]=true;
        shipField[5][7]=true;
        shipField[5][8]=true;

        shipField[6][1]=true;
        shipField[6][4]=true;
        shipField[6][6]=true;
        shipField[6][9]=true;

        shipField[7][6]=true;

        shipField[8][6]=true;

        deathTime=System.currentTimeMillis();
    }

}
