package com.martykausas;

import com.martykausas.interfaces.Drawable;
import java.awt.Color;
import java.awt.Graphics;
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
    }

    @Override
    public void paint(Graphics g) {
        super.paint(g);
//        g.setColor(Color.CYAN);
//        g.drawRect(0, 0, 100, 100);

        for (int i = 0; i < drawables.size(); i++) {
            drawables.get(i).draw(g);
        }
    }
}
