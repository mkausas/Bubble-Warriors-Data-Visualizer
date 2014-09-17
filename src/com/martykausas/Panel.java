package com.martykausas;

import com.martykausas.interfaces.Drawable;
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

    public Panel() {
        drawables = WarAIProgram.getDrawables();
//        setDoubleBuffered(true);
    }

    @Override
    public void paint(Graphics g) {
        super.paint(g);
        Graphics2D g2d = (Graphics2D) g;
        g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);



        for (int i = 0; i < drawables.size(); i++) {
            drawables.get(i).draw(g2d);
        }

//        Graphics offgc;
//        Image offscreen = null;
//        Dimension d = getSize();
//
//        // create the offscreen buffer and associated Graphics
//        offscreen = createImage(d.width, d.height);
//        offgc = offscreen.getGraphics();
//        // clear the exposed area
//        offgc.setColor(getBackground());
//        offgc.fillRect(0, 0, d.width, d.height);
//        offgc.setColor(getForeground());
//        // do normal redraw
//        paint(offgc);
//        // transfer offscreen to window
//        g.drawImage(offscreen, 0, 0, this);
    }
}
