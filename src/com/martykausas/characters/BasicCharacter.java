package com.martykausas.characters;

import com.martykausas.interfaces.Updatable;
import com.martykausas.interfaces.Drawable;
import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
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
            // teams
            RED = 0,
            BLUE = 1,

            // character types
            BASIC = 0,
            FIGHTER = 1,
            MEDIC = 2,

            SIZE = 40,
            SMALL_SIZE = (int) (SIZE * 0.6);

    private int
            team,
            characterType = BASIC,
            health = 255,
            transparency = health,
            interactionCircleSize = 0;

    private double
            outsideX = 0,
            outsideY = 0,
            insideX = 0,
            insideY = 0,
            setpointX = 400,
            setpointY = 500,

            movementSpeed = 0.0044,
            distanceToClosestOpponent = 10000,
            distanceToInteract = 5;

    private static final double
            HALF_SIZE = SIZE / 2,
            HALF_SMALL_SIZE = SMALL_SIZE / 2;

    private BasicCharacter opponent;
    private Vector2d velocityVector = new Vector2d();
    private BufferedImage bImg = new BufferedImage(1, 1, BufferedImage.TYPE_INT_RGB);
    private Image icon = bImg.getScaledInstance(1, 1, Image.SCALE_FAST);
    private Color interactionRingColor = Color.BLACK;

    private int iconX, iconY;


    public BasicCharacter(int team, double startX, double startY) {
        this.team   = team;
        outsideX    = startX;
        outsideY    = startY;
        insideX     = outsideX + HALF_SMALL_SIZE;
        insideY     = outsideX + HALF_SMALL_SIZE;

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
        insideX = (outsideX + HALF_SIZE) - HALF_SMALL_SIZE;
        insideY = (outsideY + HALF_SIZE) - HALF_SMALL_SIZE;

        iconX = (int) (getCenterX() - icon.getWidth(null) / 2);
        iconY = (int) (getCenterY() - icon.getHeight(null) / 2);

        if (readyToInteract()) {
            if (interactionCircleSize < distanceToInteract) {
                    interactionCircleSize++;
            } else {
                    interactionCircleSize = 0;
            }
        }

        childUpdate();
    }

    /**
     * Override this method in the parent class to call additional updates
     */
    public void childUpdate() { /* fill in in child class */ }

    private Vector2d getVelocityVector() {
        double dx = setpointX - (insideX + HALF_SMALL_SIZE);
        double dy = setpointY - (insideY + HALF_SMALL_SIZE);

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

        try {
            transparency = health <= 0 ? 0 : health;
            transparency = health >= 255 ? 255 : health;
            System.out.println("Transparency = " + transparency);

            // outside circle
            g.setColor(team == RED ? Color.RED : Color.BLUE);
            switch (team) {
                case RED:
                    g.setColor(new Color(255, 0, 0, transparency));
                    break;
                case BLUE:
                    g.setColor(new Color(0, 0, 255, transparency));
                    break;
                default:
                    g.setColor(Color.ORANGE);
            }
            g.fillOval((int) outsideX, (int) outsideY, SIZE, SIZE);

            // inside circle
            g.setColor(readyToInteract() ? new Color(255, 0, 255, transparency) : new Color(0, 0, 0, transparency));
            g.fillOval((int) insideX, (int) insideY, SMALL_SIZE, SMALL_SIZE);
        } // end of try

        catch (Exception ex) {
            System.out.println("Stupid color error.");
        }

        // interaction ring color
        if (readyToInteract()) {
            Graphics2D g2D = (Graphics2D) g;
            g2D.setStroke(new BasicStroke(2F));

            g2D.setColor(interactionRingColor);
            g2D.drawOval(
                    (int) getCenterX() - (interactionCircleSize / 2),
                    (int) getCenterY() - (interactionCircleSize / 2),
                    interactionCircleSize, interactionCircleSize);
        }

        // setpoint line
//        g.setColor(Color.green);
//        g.drawLine((int) getCenterX(), (int) getCenterY(), (int) setpointX, (int) setpointY);

        // if the img exists, draw it
        if (icon.getWidth(null) > 1)
            g.drawImage(icon, iconX, iconY, null);

//        g.setColor(Color.WHITE);
//        g.setFont(new Font("Helvetica", Font.BOLD, 20));
//        g.drawString("" + id, (int) getCenterX(), (int) getCenterY());

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
    public double getCenterX() {
        return (insideX + HALF_SMALL_SIZE);
    }

    /**
     * Returns the cartesian y-coordinate of the center of the {@code BasicCharacter}
     * @return
     */
    public double getCenterY() {
        return (insideY + HALF_SMALL_SIZE);
    }

    public double getTopLeftX() {
        return outsideX;
    }

    public double getTopLeftY() {
        return outsideY;
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
                Math.pow(character1.getCenterX() - character2.getCenterX(), 2) +
                Math.pow(character1.getCenterY() - character2.getCenterY(), 2));
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

    /**
     * Set the health of the character
     * @param health
     */
    public void setHealth(int health) {
        this.health = health;
    }

    /**
     * Deducts the specified amount of health from the {@code BasicCharacter}
     * @param amount
     */
    public void deductHealth(int amount) {
        health -= amount;
    }

    /**
     * Increases the current health of a {@code BasicCharacter}
     * @param amount
     */
    public void increaseHealth(int amount) {
        health += amount;
    }

    /**
     * Returns the current health of the character
     * @return
     */
    public int getHealth() {
        return health;
    }

    /**
     * Sets the current opponent of this {@code BasicCharacter}
     * @param opponent
     */
    public void setInteractionCharacter(BasicCharacter opponent) {
        this.opponent = opponent;
    }


    public BasicCharacter getInreractionCharacter() {
        return opponent;
    }

    public void setType(int characterType) {
        this.characterType = characterType;
    }

    public int getType() {
        return characterType;
    }

    public void setInteractionRingColor(Color color) {
        interactionRingColor = color;
    }

}
