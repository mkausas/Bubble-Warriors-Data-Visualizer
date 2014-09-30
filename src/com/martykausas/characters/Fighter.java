package com.martykausas.characters;

import java.awt.Color;
import java.awt.Graphics;

/**
 *
 * @author Marty
 */
public class Fighter extends BasicCharacter {

    private int damageCoefficient = 5;
    private int interactDistance = 120;
    private int circleSize = 10;

    public Fighter(int type, double startX, double startY, int color) {
        super(type, startX, startY);
        setColor(color);
        setIcon("imgs/sword.png");
        setDistanceToInteract(interactDistance);
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
    }



}
