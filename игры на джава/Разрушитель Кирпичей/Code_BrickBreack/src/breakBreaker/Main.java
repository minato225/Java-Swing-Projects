package breakBreaker;

import javax.swing.*;
import java.awt.*;

public class Main {
    public static void main(String[] args) {
        JFrame obj = new JFrame();
        Gameplay game = new Gameplay();
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        obj.setBounds((screenSize.width - 700) / 2,(screenSize.height - 600) / 2,710,620);
        obj.setTitle("Ilua game Breack Ball");
        obj.setResizable(false);
        obj.setVisible(true);
        obj.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        obj.add(game);
    }
}