package com.martykausas.characters;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;

/**
 *
 * @author Marty
 */
public class Medic extends BasicCharacter {

    private int healthCoefficient = 1;
    private int interactDistance = SIZE * 2;
    private int circleSize = 10;

    public Medic(int team, double startX, double startY) {
        super(team, startX, startY);
        setIcon("imgs/cross.png");
        setDistanceToInteract(interactDistance);
        setType(BasicCharacter.MEDIC);
        setInteractionRingColor(Color.GREEN);
    }

    @Override
    public void childUpdate() {
        if (readyToInteract()) {
            BasicCharacter ally = getInreractionCharacter();
            ally.increaseHealth(healthCoefficient);
        }

    }

    @Override
    public void childDraw(Graphics g) {
    }

}
