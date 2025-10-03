//------------- Movable -------------------

public interface Movable { 
   void moveUp();
   void moveDown();
   void moveLeft();
   void moveRight();
    
}

//------------- MovablePoint -------------------

public class MovablePoint implements Movable {
    private int x;
    private int y;
    private int xSpeed;
    private int ySpeed;

    MovablePoint(int x, int y, int xSpeed, int ySpeed){
        this.x = x;
        this.y = y;
        this.xSpeed = xSpeed;
        this.ySpeed = ySpeed;
    }
    public void moveUp(){
        y -= ySpeed;

    }
    public void moveDown(){
        y += ySpeed;

    }
    public void moveLeft(){
        x -= xSpeed;

    }
    public void moveRight(){
        x += xSpeed;
    }
    
}



//------------- MovableCircle -------------------
public class MovableCircle implements Movable {
    private int radius;
    private MovablePoint center;

    public MovableCircle(int x, int y, int xSpeed, int ySpeed, int radius){
        center = new MovablePoint(x, y, xSpeed, ySpeed);
        this.radius = radius;
    }
    public void moveUp(){
        center.moveUp();

    }
    public void moveDown(){
       center.moveDown();
    }
    public void moveLeft(){
       center.moveLeft();
    }
    public void moveRight(){
       center.moveRight();
    }

    
}
