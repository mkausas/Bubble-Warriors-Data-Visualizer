package com.martykausas;

import java.awt.Image;
import java.io.File;
import javax.imageio.ImageIO;

/**
 *
 * @author Marty
 */
public class InteractiveImage {


    public static final int
            NORMAL = 0,
            HOVER = 1,
            SELECTED = 2;

    private int currentState = NORMAL;

    private Image
            pic1,
            pic2,
            pic3,

            currentImg;

    public InteractiveImage(String pic1URL, String pic2URL, String pic3URL) {
        try {
            this.pic1 = ImageIO.read(new File(pic1URL));
            this.pic2 = ImageIO.read(new File(pic2URL));
            this.pic3 = ImageIO.read(new File(pic3URL));
            currentImg = pic1;
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    public void setState(int state) {
        currentState = state;
        switch (state) {
            case NORMAL:
                currentImg = pic1;
                break;
            case HOVER:
                currentImg = pic2;
                break;
            case SELECTED:
                currentImg = pic3;
                break;
        }
    }

    public int getState() {
        return currentState;
    }

    public Image get() {
        return currentImg;
    }

    public Image get(int state) {
        switch (state) {
            case NORMAL:
                return pic1;
            case HOVER:
                return pic2;
            case SELECTED:
                return pic3;
            default:
                return null;
        }
    }

}
