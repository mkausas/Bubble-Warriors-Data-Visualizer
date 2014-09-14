package com.martykausas;

import java.awt.Dimension;
import java.awt.Toolkit;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import javax.swing.JFrame;

/**
 *
 * @author Marty
 */
public class Frame extends JFrame {

    public static final Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();

    public static Panel panel;

    public Frame() {
        super("Bubble A.I. Game");
        setSize(screenSize);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setUndecorated(true);
        setOpacity((float) 0.6);

        addKeyListener(new KeyAdapter() {
            public void keyReleased(KeyEvent e) {
                if (e.getKeyCode() == e.VK_ESCAPE) {
                    System.exit(0);
                }
            }
        });

        initVariables();
        add(panel);
        setVisible(true);
        this.createBufferStrategy(ALLBITS);

    }

    private void initVariables() {
        panel = new Panel();
    }

}
