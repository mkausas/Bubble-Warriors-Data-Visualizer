package com.martykausas.prototypingclasses;

import java.awt.Graphics;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.File;
import javax.imageio.ImageIO;

/**
 *
 * @author Marty
 */
public class Fighter extends BasicCharacter {

    public Fighter(int type, double startX, double startY) {
        super(type, startX, startY);
        setIcon("imgs/sword.png");
        setDistanceToInteract(100);
        setHealth(255);
    }

    @Override
    public void childUpdate() {
        if (readyToInteract()) {
            BasicCharacter opponent = getOpponent();
            opponent.setHealth(opponent.getHealth() - 1);
        }
    }

    @Override
    public void childDraw(Graphics g) {
    }



}
