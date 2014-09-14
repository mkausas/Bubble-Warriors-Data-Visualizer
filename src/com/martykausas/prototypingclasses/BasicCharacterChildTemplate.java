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
public class BasicCharacterChildTemplate extends BasicCharacter {

    public BasicCharacterChildTemplate(int team, double startX, double startY) {
        super(team, startX, startY);
        setIcon("pics/image.png", 2.29);
        setDistanceToInteract(100);
    }

    @Override
    public void childUpdate() {
    }

    @Override
    public void childDraw(Graphics g) {

    }
}
