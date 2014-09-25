package com.martykausas.characters;

import com.martykausas.characters.BasicCharacter;
import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;

/**
 *
 * @author Marty
 */
public class Fighter extends BasicCharacter {

    private int damageCoefficient = 2;
    private int interactDistance = 100;
    private int circleSize = 10;

    public Fighter(int type, double startX, double startY) {
        super(type, startX, startY);
        setIcon("imgs/sword.png");
        setDistanceToInteract(interactDistance);
        setHealth(255);
        setType(BasicCharacter.FIGHTER);
        setInteractionRingColor(Color.RED);
    }

    @Override
    public void childUpdate() {
        if (readyToInteract()) {
            BasicCharacter opponent = getInreractionCharacter();
            opponent.deductHealth(damageCoefficient);
        }
    }

    @Override
    public void childDraw(Graphics g) {
    }



}
