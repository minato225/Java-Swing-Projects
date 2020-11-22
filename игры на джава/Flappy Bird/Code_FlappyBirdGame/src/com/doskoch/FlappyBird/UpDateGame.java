package com.doskoch.FlappyBird;

import javax.swing.*;
import java.awt.*;

public class UpDateGame extends JPanel {
    private static final long serialVersion = 1L;

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        FlappyBirdMain.flappyBird.repaint(g);
    }
}
