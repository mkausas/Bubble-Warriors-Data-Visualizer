package com.martykausas.characters;

import java.awt.Color;
import java.awt.Graphics;

/**
 *
 * @author Marty
 */
public class Fighter extends BasicCharacter {

    private final int damageCoefficient = 5;
//    private int interactDistance = 120;

    public Fighter(int type, double startX, double startY, int color) {
        super(type, startX, startY);
        setColor(color);
        setIcon("imgs/sword.png");
        setDistanceToInteract(super.SIZE * 3);
        setHealth(255);
        setType(BasicCharacter.FIGHTER);
        setInteractionRingColor(Color.RED);
    }

    @Override
    public void childUpdate() {
        if (readyToInteract()) {
            BasicCharacter opponent = getInteractionCharacter();
            opponent.deductHealth(damageCoefficient);
        }
    }

    @Override
    public void childDraw(Graphics g) {
        if (readyToInteract()) {
            g.setColor(Color.green);
            g.drawLine((int) getCenterX(), (int) getCenterY(), (int) getSetpointX(), (int) getSetpointY());
        }
    }



}
