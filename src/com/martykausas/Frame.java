package com.martykausas;

import javax.swing.JFrame;

/**
 *
 * @author Marty
 */
public class Frame extends JFrame {


    public static final int WIDTH = 500;
    public static final int HEIGHT = 500;

    public static Panel panel;

    public Frame() {
        super("Bubble A.I. Game");
        setSize(WIDTH, HEIGHT);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        initVariables();

        add(panel);

        setVisible(true);
    }

    private void initVariables() {
        panel = new Panel();
    }

}
