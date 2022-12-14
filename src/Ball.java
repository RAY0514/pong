import java.awt.*;
import java.util.Random;

public class Ball  extends  Rectangle{

    Random random;
    //速度
    int xVelocity;
    int yVelocity;
    int initialspeed = 3 ;

    Ball(int x,int y,int width,int height){
        super( x, y,width, height);
        random=new Random();
        int randomXDirection = random.nextInt(2);//()裡的數字範圍是0和1
        if (randomXDirection == 0)
            randomXDirection--;
        setXDirection(randomXDirection * initialspeed );

        int randomYDirection = random.nextInt(2);//()裡的數字範圍是0和1
        if (randomYDirection == 0)
            randomYDirection--;
        setYDirection(randomYDirection * initialspeed);

    }

    public  void setXDirection(int randomXDirection){
        xVelocity = randomXDirection;

    }
    public  void setYDirection(int randomYDirection){

        yVelocity = randomYDirection;

    }
    public void move(){

        x += xVelocity;
        y += yVelocity;
    }
    public void draw(Graphics g){

        g.setColor(Color.white);
        g.fillOval(x,y,height,width);
    }

}
