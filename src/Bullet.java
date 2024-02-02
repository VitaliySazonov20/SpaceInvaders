public class Bullet {
    int x;
    int y;
    Bullet(int x,int y){
        this.x=x;
        this.y=y;

    }
    public void move(){
        y-=1;
    }

    public int getY() {
        return this.y;
    }

    public int getX() {
        return this.x;
    }
}
