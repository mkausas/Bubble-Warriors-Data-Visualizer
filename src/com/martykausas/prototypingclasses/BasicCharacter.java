package com.martykausas.prototypingclasses;

import com.martykausas.interfaces.Actable;
import com.martykausas.interfaces.Drawable;
import java.awt.Color;
import java.awt.Graphics;
import javax.vecmath.Vector2d;

/**
 *
 * @author Marty
 */
public class BasicCharacter implements Actable, Drawable {

    private static int currentID = 1;
    private final int id = currentID;

    public static final int
            RED = 0,
            BLUE = 1,
            SIZE = 40,
            SMALL_SIZE = (int) SIZE / 2;

    private int
            type;

    private double
            outsideX = 0,
            outsideY = 0,
            insideX,
            insideY,
            movementSpeed = 0.006,
            setpointX = 400,
            setpointY = 500,
            distanceToClosestOpponent = 10000;

    private Vector2d velocityVector = new Vector2d();

    public BasicCharacter(int type, double startX, double startY) {
        this.type   = type;
        outsideX    = startX;
        outsideY    = startY;
        insideX     = outsideX + SMALL_SIZE / 2;
        insideY     = outsideX + SMALL_SIZE / 2;

        currentID++;
    }


    @Override
    public void update() {
        velocityVector = getVelocityVector();

//        System.out.println("velocity = " + "(" + velocity.x + ", " + velocity.y + ")");
        outsideX += velocityVector.x;
        outsideY += velocityVector.y;
        insideX = outsideX + SMALL_SIZE / 2;
        insideY = outsideY + SMALL_SIZE / 2;
    }

    private Vector2d getVelocityVector() {
        double dx = setpointX - (insideX + SMALL_SIZE / 2);
        double dy = setpointY - (insideY + SMALL_SIZE / 2);

        return new Vector2d(dx * movementSpeed, dy * movementSpeed);

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
        //<editor-fold defaultstate="collapsed" desc="alternative method to movement, constant pace">
//        if (dx > movementSpeed)
//            dx = movementSpeed;
//        else if (dx < -movementSpeed)
//            dx = -movementSpeed;
//
//        if (dy > movementSpeed)
//            dy = movementSpeed;
//        else if (dy < -movementSpeed)
//            dy = -movementSpeed;
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

        // setpoint line
        g.setColor(Color.green);
        g.drawLine((int) getX(), (int) getY(), (int) setpointX, (int) setpointY);

        g.setColor(Color.MAGENTA);
        g.drawString("" + id, (int) getX(), (int) getY());
    }


    /**
     * Sets the target of point of the {@code BasicCharacter}
     * @param setpointX
     * @param setpointY
     */
    public void setTarget(double setpointX, double setpointY) {
        this.setpointX = setpointX;
        this.setpointY = setpointY;
    }

    /**
     * Returns the cartesian x-coordinate of the center of the {@code BasicCharacter}
     * @return
     */
    public double getX() {
        return (insideX + SMALL_SIZE / 2);
    }

    /**
     * Returns the cartesian y-coordinate of the center of the {@code BasicCharacter}
     * @return
     */
    public double getY() {
        return (insideY + SMALL_SIZE / 2);
    }

    public double getSetpointX() {
        return setpointX;
    }

    public double getSetpointY() {
        return setpointY;
    }

    /**
     * Returns the integer representing the type of {@see AverageJoe} this is
     * @return
     */
    public int getType() {
        return type;
    }

    /**
     * Returns whether or not two {@code BasicCharacter}s are intersecting
     *
     * @param character
     * @return
     */
    public boolean intersects(BasicCharacter character) {
        return dist(this, character) < SIZE;
    }

    /**
     * Returns the distance between two {@code BasicCharacter}s
     *
     * @param character1
     * @param character2
     * @return
     */
    public double dist(BasicCharacter character1, BasicCharacter character2) {
        return Math.sqrt(
                Math.pow(character1.getX() - character2.getX(), 2) +
                Math.pow(character1.getY() - character2.getY(), 2));
    }

    public int getID() {
        return id;
    }


    public double getDistanceToClosestOpponent() {
        return distanceToClosestOpponent;
    }

    public void setDistanceToClosestOpponent(double dist) {
        distanceToClosestOpponent = dist;
    }
}
