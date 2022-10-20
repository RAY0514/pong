import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class GameFrame extends JFrame {

    GamePanel panel;
    GameFrame() {
        panel=new GamePanel();
        this.add(panel);
        this.setTitle("pong");
        this.setResizable(false);//不可調整大小
        this.setBackground(Color.black);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.pack();
        this.setVisible(true);
        this.setLocationRelativeTo(null);//出現在螢幕中間

    }
}



