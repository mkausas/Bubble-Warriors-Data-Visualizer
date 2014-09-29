package com.martykausas.prototypingclasses;

import com.martykausas.interfaces.Drawable;
import com.martykausas.interfaces.Updatable;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileInputStream;
import javax.imageio.ImageIO;

/**
 *
 * @author Marty
 */
public class DrawSomething implements Updatable, Drawable {


    private Image img;

    private BufferedImage oldImage, newImage;

    public DrawSomething(int x, int y) {
        try {
            oldImage = ImageIO.read(new FileInputStream("imgs/sword.png"));
            newImage = new BufferedImage(oldImage.getHeight(), oldImage.getWidth(), oldImage.getType());
            Graphics2D graphics = (Graphics2D) newImage.getGraphics();
            graphics.rotate(Math.toRadians(90), newImage.getWidth() / 2, newImage.getHeight() / 2);
            graphics.drawImage(oldImage, 0, 0, oldImage.getWidth(), oldImage.getHeight(), null);


//            img = ImageIO.read(new File(""));
//            newImage = new BufferedImage(img.getWidth(), img.getHeight(), img.getType());
        } catch (Exception ex) { }
    }

    @Override
    public void draw(Graphics g) {
        g.drawImage(oldImage, 100, 100, null);
    }

    @Override
    public void update() {
    }

}
