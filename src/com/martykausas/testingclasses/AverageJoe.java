package com.martykausas.testingclasses;

import com.martykausas.interfaces.Actable;
import com.martykausas.interfaces.Drawable;
import java.awt.Color;
import java.awt.Graphics;
import javax.vecmath.Vector2d;

/**
 *
 * @author Marty
 */
public class AverageJoe implements Actable, Drawable {

    public static final int RED = 0, BLUE = 1;

    private int
            type,
            SIZE = 80,
            SMALL_SIZE = (int) SIZE / 2;

    private double
            outsideX = 0,
            outsideY = 0,
            insideX,
            insideY,
            movementSpeed = 2,
            setpointX = 400,
            setpointY = 500;


    public AverageJoe(int type, double startX, double startY) {
        this.type = type;
        outsideX = startX;
        outsideY = startY;
    }


    @Override
    public void update() {
        Vector2d velocity = getVelocityVector();

        System.out.println("velocity = " + "(" + velocity.x + ", " + velocity.y + ")");
        outsideX += velocity.x;
        outsideY += velocity.y;
        insideX = outsideX + SMALL_SIZE / 2;
        insideY = outsideY + SMALL_SIZE / 2;
    }

    private Vector2d getVelocityVector() {
        double dx = setpointX - (insideX + SMALL_SIZE / 2);
        double dy = setpointY - (insideY + SMALL_SIZE / 2);


        if (dx > movementSpeed)
            dx = movementSpeed;
        else if (dx < -movementSpeed)
            dx = -movementSpeed;

        if (dy > movementSpeed)
            dy = movementSpeed;
        else if (dy < -movementSpeed)
            dy = -movementSpeed;

        return new Vector2d(dx * 1, dy * 1);

        //<editor-fold defaultstate="collapsed" desc="More complicated vector math for moving to a setpoint, yet to be completed">
//        System.out.println("dx = " + dx + " dy = " + dy);
//
//        if (Math.abs(dx) > Math.abs(dy)) {
////        if (dx > dy) {
//            System.out.println("first case");
//            return new Vector2d((dy / dx) * movementSpeed, movementSpeed);
//        } else {
//            System.out.println("second case");
//            return new Vector2d(movementSpeed, (dx / dy) * movementSpeed);
//        }
//        return movementSpeed * ((dx > dy) ? (dy / dx) : (dx / dy));

        //</editor-fold>
    }


    @Override
    public void draw(Graphics g) {
        // outside circle
        g.setColor(type == RED ? Color.RED : Color.BLUE);
        g.fillOval((int) outsideX, (int) outsideY, SIZE, SIZE);

        // inside circle
        g.setColor(Color.black);
        g.fillOval((int) insideX, (int) insideY, SMALL_SIZE, SMALL_SIZE);

        // setpoint test
        g.setColor(Color.green);
        g.drawRect((int) setpointX - SIZE / 2, (int) setpointY - SIZE / 2, SIZE, SIZE);
    }


    public void setTarget(double setpointX, double setpointY) {
        this.setpointX = setpointX;
        this.setpointY = setpointY;
    }

    public double getX() {
        return (insideX + SMALL_SIZE / 2);
    }

    public double getY() {
        return (insideY + SMALL_SIZE / 2);
    }

    public int getType() {
        return type;
    }
}
