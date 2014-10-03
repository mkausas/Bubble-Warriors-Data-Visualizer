package com.martykausas.characters;

import java.awt.Color;
import java.awt.Graphics;

/**
 *
 * @author Marty
 */
public class Medic extends BasicCharacter {

    private final int healthCoefficient = 1;
    private int interactDistance = SIZE * 2;
    private int circleSize = 10;

    public Medic(int team, double startX, double startY, int color) {
        super(team, startX, startY);
        setColor(color);
        setIcon("imgs/cross.png");
        setDistanceToInteract(interactDistance);
        setType(BasicCharacter.MEDIC);
        setInteractionRingColor(Color.GREEN);
    }

    @Override
    public void childUpdate() {
        if (readyToInteract()) {
            BasicCharacter ally = getInteractionCharacter();
            ally.increaseHealth(healthCoefficient);
        }

    }

    @Override
    public void childDraw(Graphics g) {
    }

}
