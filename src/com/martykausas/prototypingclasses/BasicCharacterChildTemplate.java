package com.martykausas.prototypingclasses;

import java.awt.Graphics;

/**
 *
 * @author Marty
 */
public class BasicCharacterChildTemplate extends BasicCharacter {

    public BasicCharacterChildTemplate(int team, double startX, double startY) {
        super(team, startX, startY);
        setIcon("pics/image.png");
        setDistanceToInteract(100);
        setHealth(100);
    }

    @Override
    public void childUpdate() {
    }

    @Override
    public void childDraw(Graphics g) {

    }
}
