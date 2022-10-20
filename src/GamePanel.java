import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.Random;

/*
在［JAVA］多重繼承中提到：透過 介面 就能達到多重繼承。
Java 提供了 Runnable 介面，有抽象函數 run() ；因此我們只需要定義 run() 即可。

 */
public class GamePanel extends JPanel implements Runnable{

    static final  int GAME_WIDTH = 1000;//final防止意外修改
    static final  int GAME_HEIGHT = (int) (GAME_WIDTH * (0.5555));//複製現實的桌球桌比例
    static  final Dimension SCREEN_SIZE=new Dimension(GAME_WIDTH,GAME_HEIGHT);
    static final int BALL_DIAMETER = 20;//球的大小

    static final int PADDLE_WIDTH=25;
    static final int PADDLE_HEIGHT=100;
    Thread gameThread;
    Image image;
    Graphics graphics;
    Random random;
    paddle paddle1;
    paddle paddle2;
    Ball ball;
    Score score ;







    GamePanel(){
        newPaddlse();
        newBall();
        score =new Score(GAME_WIDTH,GAME_HEIGHT);
        this.setFocusable(true);
        this.addKeyListener(new AL());
        this.setPreferredSize(SCREEN_SIZE);

        gameThread=new Thread(this);
        gameThread.start();


    }
    public void newBall(){
        random = new Random();
        ball = new Ball((GAME_WIDTH/2)-(BALL_DIAMETER/2),random.nextInt(GAME_HEIGHT-BALL_DIAMETER),BALL_DIAMETER,BALL_DIAMETER);

    }
    public void newPaddlse(){
        paddle1=new paddle(0,(GAME_HEIGHT/2)-(PADDLE_HEIGHT/2),PADDLE_WIDTH,PADDLE_HEIGHT,1);
        paddle2=new paddle(GAME_WIDTH-PADDLE_WIDTH,(GAME_HEIGHT/2)-(PADDLE_HEIGHT/2),PADDLE_WIDTH,PADDLE_HEIGHT,2);


    }
    public void paint(Graphics g){
        image = createImage(getWidth(),getHeight());
        graphics = image.getGraphics();
        draw(graphics);
        g.drawImage(image ,0,0,this);




    }
    public void draw(Graphics g){
        paddle1.draw(g);
        paddle2.draw(g);
        ball.draw(g);
        score.draw(g);

    }
    public void move(){
        paddle1.move();
        paddle2.move();
        ball.move();


    }
    public void checkCollision(){
        //球碰到邊緣反彈
        if (ball.y <= 0){
            ball.setYDirection(-ball.yVelocity);
        }
        if (ball.y >= GAME_HEIGHT-BALL_DIAMETER){
            ball.setYDirection(-ball.yVelocity);
        }

        //球碰到拍子反彈
        if(ball.intersects(paddle1)) {//intersects相交
            ball.xVelocity = Math.abs(ball.xVelocity);//abs絕對值
            ball.xVelocity++;//球加速
            if (ball.yVelocity > 0)
                ball.yVelocity++;
            else
                ball.yVelocity--;
            ball.setXDirection(ball.xVelocity);
            ball.setYDirection(ball.yVelocity);
        }
        if(ball.intersects(paddle2)) {//intersects相交
            ball.xVelocity = Math.abs(ball.xVelocity);//abs絕對值
            ball.xVelocity++;//球加速
            if (ball.yVelocity > 0)
                ball.yVelocity++;
            else
                ball.yVelocity--;
            ball.setXDirection(-ball.xVelocity);
            ball.setYDirection(ball.yVelocity);
        }

        //防止paddle跑出視窗
        if(paddle1.y <= 0)
            paddle1.y=0;
        if(paddle1.y >= (GAME_HEIGHT - PADDLE_HEIGHT))
        paddle1.y=GAME_HEIGHT - PADDLE_HEIGHT;
        if(paddle2.y <= 0)
            paddle2.y=0;
        if(paddle2.y >= (GAME_HEIGHT - PADDLE_HEIGHT))
        paddle2.y=GAME_HEIGHT - PADDLE_HEIGHT;

        //得分重製球拍羽球
        if (ball.x <= 0) {//左邊邊界
            score.play2++;
            newPaddlse();
            newBall();
            System.out.println("play2 score:"+score.play2);
        }
        if (ball.x >= GAME_WIDTH-BALL_DIAMETER) {//右邊邊界
            score.play1++;
            newPaddlse();
            newBall();
            System.out.println("play1 score:"+score.play1);
        }

    }
    public void run(){

        //game loop
        long lastime = System.nanoTime();//用來獲取表徵當前時間的數值
        double amountofTicks = 60.0;
        double ns = 1000000000/ amountofTicks ;
        double delta=0;
        while (true){//(表運行)
            long now = System.nanoTime();
            delta +=(now-lastime)/ns;
            /*
            sum = sum + i; //这个意思
            sum += i; //这是简写

             */
            lastime = now;
            if  (delta >= 1){
                move();
                checkCollision();
                repaint();
                delta--;

            }



        }




    }
    public class AL extends KeyAdapter {
        public  void keyPressed(KeyEvent e){
            paddle1.keyPressed(e);
            paddle2.keyPressed(e);

        }

        public  void keyReleased(KeyEvent e){
            paddle1.keyReleased(e);
            paddle2.keyReleased(e);

        }
    }

}


