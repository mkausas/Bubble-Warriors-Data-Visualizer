package com.martykausas;

import com.martykausas.interfaces.Drawable;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.util.ArrayList;
import javax.swing.JPanel;

/**
 *
 * @author Marty
 */
public class Panel extends JPanel {

    private ArrayList<Drawable> drawables;

    public Panel() {
        drawables = WarAIProgram.drawables;
        setDoubleBuffered(true);
    }

    @Override
    public void paint(Graphics g) {
        super.paint(g);
        Graphics2D g2d = (Graphics2D) g;
        g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

        for (int i = 0; i < drawables.size(); i++) {
            drawables.get(i).draw(g2d);
        }
    }
}
