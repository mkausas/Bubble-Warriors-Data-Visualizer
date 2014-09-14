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
        setIcon("imgs/sword.png", 2.29);
        setDistanceToInteract(100);
    }

    @Override
    public void childUpdate() {
        System.out.println("Child is updating");
    }

    @Override
    public void childDraw(Graphics g) {
        System.out.println("Child is drawing!");
    }



}
