import java.awt.*;
import java.awt.event.KeyEvent;

public class paddle extends Rectangle {
    int id;
    int yVelocity;//移動速度
    int speed = 10;

    paddle(int x, int y, int PADDLE_WIDTH, int PADDLE_HEIGHT, int id) {
        super(x, y, PADDLE_WIDTH, PADDLE_HEIGHT);
        /*
        this：存取 本身類別 的成員（資料成員、函數成員、建構元）

        super：存取 父類別 的成員
         */
        this.id = id;
    }

    public void keyPressed(KeyEvent e) {//鍵盤按下
        switch (id) {//檢查id變量
            case 1:
                if (e.getKeyCode() == KeyEvent.VK_W) {//得到案件代碼等於事件VK_W
                    setYDirection(-speed);//往上
                    move();
                }
                if (e.getKeyCode() == KeyEvent.VK_S) {//得到案件代碼等於事件VK_W
                    setYDirection(speed);//往下
                    move();
                }
                break;
            case 2:
                if (e.getKeyCode() == KeyEvent.VK_UP) {//得到案件代碼等於事件VK_W
                    setYDirection(-speed);//往上
                    move();
                }
                if (e.getKeyCode() == KeyEvent.VK_DOWN) {//得到案件代碼等於事件VK_W
                    setYDirection(speed);//往下
                    move();
                }
                break;
        }
    }

        public void keyReleased (KeyEvent e){//鍵盤釋放
            switch (id) {//檢查id變量
                case 1:
                    if (e.getKeyCode() == KeyEvent.VK_W) {//得到案件代碼等於事件VK_W
                        setYDirection(0);//往上
                        move();
                    }
                    if (e.getKeyCode() == KeyEvent.VK_S) {//得到案件代碼等於事件VK_W
                        setYDirection(0);//往下
                        move();
                    }
                    break;
                case 2:
                    if (e.getKeyCode() == KeyEvent.VK_UP) {//得到案件代碼等於事件VK_W
                        setYDirection(0);//往上
                        move();
                    }
                    if (e.getKeyCode() == KeyEvent.VK_DOWN) {//得到案件代碼等於事件VK_W
                        setYDirection(0);//往下
                        move();
                    }
                    break;
            }
        }


        public void setYDirection ( int yDirection){
        yVelocity=yDirection;
        }

        public void move () {
        y=y+yVelocity;
        }

        public void draw (Graphics g){
        if (id==1)
            g.setColor(Color.blue);
        else
            g.setColor(Color.red);
            g.fillRect(x,y,width,height);
        }
}
