package com.martykausas;

import com.martykausas.interfaces.Drawable;
import com.martykausas.prototypingclasses.CornerText;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.RenderingHints;
import java.util.ArrayList;
import javax.swing.JPanel;

/**
 *
 * @author Marty
 */
public class Panel extends JPanel {

    private ArrayList<Drawable> drawables;
    private CornerText scoreboard;

    public static final int
            rRed = 219,
            gRed = 22,
            bRed = 55,

            rBlue = 53,
            gBlue = 60,
            bBlue = 211,

            rGreen = 31,
            gGreen = 217,
            bGreen = 74,

            rYellow = 241,
            gYellow = 243,
            bYellow = 39;

    public Panel() {
        setDoubleBuffered(true);
        drawables = WarAIProgram.getDrawables();
        scoreboard = new CornerText();
    }

    @Override
    public void paint(Graphics g) {
        super.paint(g);
        Graphics2D g2d = (Graphics2D) g;
        g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

        for (int i = 0; i < drawables.size(); i++) {
            drawables.get(i).draw(g2d);
        }

        scoreboard.draw(g2d);
    }
}
