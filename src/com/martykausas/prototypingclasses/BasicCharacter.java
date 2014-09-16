package com.martykausas.prototypingclasses;

import com.martykausas.interfaces.Updatable;
import com.martykausas.interfaces.Drawable;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.File;
import javax.imageio.ImageIO;
import javax.vecmath.Vector2d;

/**
 * BasicCharacter acts as a parent for all characters.
 * This class provides basic functionality for and drawing methods of normal
 * characters; however, it exludes any actual interaction with characters
 * such as: fighting, healing, etc.
 *
 * @author Marty
 */
public class BasicCharacter implements Updatable, Drawable {

    private static int currentID = 1;
    private final int id = currentID;

    public static final int
            RED = 0,
            BLUE = 1,
            SIZE = 80,
            SMALL_SIZE = (int) (SIZE * 0.6);

    private int
            team,
            health = 10;

    private double
            outsideX = 0,
            outsideY = 0,
            insideX = 0,
            insideY = 0,
            setpointX = 400,
            setpointY = 500,

            movementSpeed = 0.006,
            distanceToClosestOpponent = 10000,
            distanceToInteract = 5;

    private Vector2d velocityVector = new Vector2d();
    private BufferedImage bImg = new BufferedImage(1, 1, BufferedImage.TYPE_INT_RGB);
    private Image icon = bImg.getScaledInstance(1, 1, Image.SCALE_FAST);

    private int iconX, iconY;


    public BasicCharacter(int team, double startX, double startY) {
        this.team   = team;
        outsideX    = startX;
        outsideY    = startY;
        insideX     = outsideX + SMALL_SIZE / 2;
        insideY     = outsideX + SMALL_SIZE / 2;

        currentID++;
    }

    /**
     * Set the custom image for the child of {@code BasicCharacter}
     * @param filePath
     */
    public void setIcon(String filePath) {
        // icon setup
        try {
            bImg = ImageIO.read(new File(filePath));
            double iconWidth = BasicCharacter.SMALL_SIZE * .4;
            icon = bImg.getScaledInstance(
                    (int) iconWidth,
                    (int) (iconWidth * (bImg.getHeight() / bImg.getWidth())), Image.SCALE_SMOOTH);

        } catch (Exception ex) { ex.printStackTrace(); }
    }

    @Override
    public void update() {
        velocityVector = getVelocityVector();

//        System.out.println("velocity = " + "(" + velocity.x + ", " + velocity.y + ")");
        outsideX += velocityVector.x;
        outsideY += velocityVector.y;
        insideX = (outsideX + SIZE / 2) - (SMALL_SIZE / 2);
        insideY = (outsideY + SIZE / 2) - (SMALL_SIZE / 2);

        iconX = (int) (getX() - icon.getWidth(null) / 2);
        iconY = (int) (getY() - icon.getHeight(null) / 2);


        childUpdate();
    }

    /**
     * Override this method in the parent class to call additional updates
     */
    public void childUpdate() { /* fill in in child class */ }

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
        g.setColor(team == RED ? Color.RED : Color.BLUE);
        g.fillOval((int) outsideX, (int) outsideY, SIZE, SIZE);

        // inside circle
        g.setColor(readyToInteract() ? Color.MAGENTA : Color.black);
        g.fillOval((int) insideX, (int) insideY, SMALL_SIZE, SMALL_SIZE);

        // setpoint line
        g.setColor(Color.green);
        g.drawLine((int) getX(), (int) getY(), (int) setpointX, (int) setpointY);

        // if the img exists, draw it
        if (icon.getWidth(null) > 1)
            g.drawImage(icon, iconX, iconY, null);

//        g.setColor(Color.MAGENTA);
//        g.drawString("" + id, (int) getX(), (int) getY());

        childDraw(g);
    }

    /**
     * Override this method in any children of {@code BasicCharacter} to draw
     * @param g
     */
    public void childDraw(Graphics g) {  /* fill in in child class */ }


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

    /**
     * Returns the x target of the {@code BasicCharacter}
     * @return
     */
    public double getSetpointX() {
        return setpointX;
    }

    /**
     * Returns the y target of the {@code BasicCharacter}
     * @return
     */
    public double getSetpointY() {
        return setpointY;
    }

    /**
     * Returns the integer representing the team of {@see AverageJoe} this is
     * @return
     */
    public int getTeam() {
        return team;
    }

    /**
     * Returns whether or not two {@code BasicCharacter}s are intersecting
     * @param character
     * @return
     */
    public boolean intersects(BasicCharacter character) {
        return dist(this, character) < SIZE;
    }

    /**
     * Returns the distance between two {@code BasicCharacter}s
     * @param character1
     * @param character2
     * @return
     */
    public double dist(BasicCharacter character1, BasicCharacter character2) {
        return Math.sqrt(
                Math.pow(character1.getX() - character2.getX(), 2) +
                Math.pow(character1.getY() - character2.getY(), 2));
    }

    /**
     * Returns a character specific ID that can be used
     * to find an instance of this class
     * @return
     */
    public int getID() {
        return id;
    }

    /**
     * Returns the distance to the closest opponent
     * @return
     */
    public double getDistanceToClosestOpponent() {
        return distanceToClosestOpponent;
    }

    /**
     * Provide this character the distance from it and its target
     * @param dist
     */
    public void setDistanceToClosestOpponent(double dist) {
        distanceToClosestOpponent = dist;
    }

    /**
     * Sets how close the character needs to be to interact
     * @param dist
     */
    public void setDistanceToInteract(double dist) {
        distanceToInteract = dist;
    }

    /**
     * Returns true if a character is close enough to interact
     * @return
     */
    public boolean readyToInteract() {
        return distanceToClosestOpponent <= distanceToInteract;
    }
}
