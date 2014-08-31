package com.martykausas.testingclasses;

import com.martykausas.interfaces.Actable;
import com.martykausas.interfaces.Drawable;
import java.awt.Graphics;

/**
 *
 * @author Marty
 */
public class AverageJoe implements Actable, Drawable {


    @Override
    public void act() {
        System.out.println("Acting");
    }

    @Override
    public void draw(Graphics g) {
        System.out.println("Drawing");
        g.fillRect(0, 0, 200, 200);
    }

    public void takeDamage(double damage) {
    }


}
