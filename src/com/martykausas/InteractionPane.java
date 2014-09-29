package com.martykausas;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.io.File;
import javax.imageio.ImageIO;
import javax.swing.JPanel;

/**
 *
 * @author Marty
 */
public class InteractionPane extends JPanel implements MouseListener, MouseMotionListener {

    private Image
            titleImg,

            colorSelectionImg,

            playImg,
            playImgHover;

    private boolean
            startGame = false,
            redHover = false,
            playHover = false;

    private final int
            RED = 1,
            BLUE = 2,
            YELLOW = 3,
            GREEN = 4;

    private int
            titleX,
            titleY,

            redX1,
            redY1,
            blueX1,
            blueY1,
            greenX1,
            greenY1,
            yellowX1,
            yellowY1,

            redX2,
            redY2,
            blueX2,
            blueY2,
            greenX2,
            greenY2,
            yellowX2,
            yellowY2,

            colorSelectionX,
            colorSelectionY,

            playX,
            playY,

            colorSelected1 = RED,
            colorSelected2 = RED;

    private InteractiveImage
            red1, green1,
            blue1, yellow1,

            red2, green2,
            blue2, yellow2;

    public InteractionPane() {
        setSize(Frame.screenSize);
        initImages();
        addMouseListener(this);
        addMouseMotionListener(this);
    }

    private void initImages() {

        try {
            System.out.println("Grabbing image");
            titleImg = ImageIO.read(new File("imgs/title.png"));

            red1 = new InteractiveImage("imgs/red.png", "imgs/redhover.png", "imgs/redselected.png");
            blue1 = new InteractiveImage("imgs/blue.png", "imgs/bluehover.png", "imgs/blueselected.png");
            green1 = new InteractiveImage("imgs/green.png", "imgs/greenhover.png", "imgs/greenselected.png");
            yellow1 = new InteractiveImage("imgs/yellow.png", "imgs/yellowhover.png", "imgs/yellowselected.png");

            // please ignore my wasted memory by doubling up on images... i just need to finish this project up...
            red2 = new InteractiveImage("imgs/red.png", "imgs/redhover.png", "imgs/redselected.png");
            blue2 = new InteractiveImage("imgs/blue.png", "imgs/bluehover.png", "imgs/blueselected.png");
            green2 = new InteractiveImage("imgs/green.png", "imgs/greenhover.png", "imgs/greenselected.png");
            yellow2 = new InteractiveImage("imgs/yellow.png", "imgs/yellowhover.png", "imgs/yellowselected.png");

            colorSelectionImg = ImageIO.read(new File("imgs/colorselection.png"));

            playImg = ImageIO.read(new File("imgs/beginbutton.png"));
            playImgHover = ImageIO.read(new File("imgs/beginbuttonhover.png"));

        } catch (Exception ex) { ex.printStackTrace(); }

        int frameWidth = Frame.screenSize.width;
        int frameHeight = Frame.screenSize.height;

        int halfScreenWidth = frameWidth / 2;
        int halfScreenHeight = frameHeight / 2;

        titleX = halfScreenWidth - (titleImg.getWidth(null) / 2);
        titleY = frameHeight / 9;

        /*** Color Coordinates ***/
        int colorSize = red1.get().getWidth(null);
        int colorSeperation = (int) (1.1 * colorSize);
        int distanceFromCenter = 4 * colorSize;
        // color set 1
        redX1 = halfScreenWidth - (distanceFromCenter);
        redY1 = halfScreenHeight;

        blueX1 = redX1;
        blueY1 = redY1 + colorSeperation;

        greenX1 = redX1 + colorSeperation;
        greenY1 = redY1;

        yellowX1 = greenX1;
        yellowY1 = greenY1 + colorSeperation;

        // color set 2
        greenX2 = halfScreenWidth + (distanceFromCenter - colorSize * 2);
        greenY2 = redY1;

        yellowX2 = greenX2;
        yellowY2 = greenY2 + colorSeperation;

        redX2 = greenX2 + colorSeperation;
        redY2 = greenY2;

        blueX2 = redX2;
        blueY2 = redY2 + colorSeperation;
        /** End Color Coordinates **/

        colorSelectionX = halfScreenWidth - colorSelectionImg.getWidth(null) / 2;
        colorSelectionY = redY1;

        playX = halfScreenWidth - (playImg.getWidth(null) / 2);
        playY =  6 * frameHeight / 9;
    }


    @Override
    public void paint(Graphics g) {
        super.paint(g);
        g.drawImage(
                titleImg,
                titleX,
                titleY, null);

        // first color set
        g.drawImage(colorSelected1 == RED ? red1.get(InteractiveImage.SELECTED) : red1.get(), redX1, redY1, null);
        g.drawImage(colorSelected1 == BLUE ? blue1.get(InteractiveImage.SELECTED) : blue1.get(), blueX1, blueY1, null);
        g.drawImage(colorSelected1 == GREEN ? green1.get(InteractiveImage.SELECTED) : green1.get(), greenX1, greenY1, null);
        g.drawImage(colorSelected1 == YELLOW ? yellow1.get(InteractiveImage.SELECTED) : yellow1.get(), yellowX1, yellowY1, null);

        // first color set
        g.drawImage(colorSelected2 == RED ? red2.get(InteractiveImage.SELECTED) : red2.get(), redX2, redY2, null);
        g.drawImage(colorSelected2 == BLUE ? blue2.get(InteractiveImage.SELECTED) : blue2.get(), blueX2, blueY2, null);
        g.drawImage(colorSelected2 == GREEN ? green2.get(InteractiveImage.SELECTED) : green2.get(), greenX2, greenY2, null);
        g.drawImage(colorSelected2 == YELLOW ? yellow2.get(InteractiveImage.SELECTED) : yellow2.get(), yellowX2, yellowY2, null);

        g.drawImage(colorSelectionImg, colorSelectionX, colorSelectionY, null);

        // play button
        g.drawImage(playHover ? playImgHover : playImg, playX, playY, null);

        // for debugging
//        g.setColor(Color.red);
//        g.fillRect(Frame.screenSize.width / 2, 0, 1, Frame.screenSize.height);
    }

    public boolean play() {
        return startGame;
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        int x = e.getX();
        int y = e.getY();

        startGame = intersects(x, y, playX, playY, playImg);

        // color set 1
        if (intersects(x, y, redX1, redY1, red1.get())) {
            red1.setState(InteractiveImage.SELECTED);
            colorSelected1 = RED;
        } else if (intersects(x, y, blueX1, blueY1, blue1.get())) {
            blue1.setState(InteractiveImage.SELECTED);
            colorSelected1 = BLUE;
        } else if (intersects(x, y, greenX1, greenY1, green1.get())) {
            green1.setState(InteractiveImage.SELECTED);
            colorSelected1 = GREEN;
        } else if (intersects(x, y, yellowX1, yellowY1, yellow1.get())) {
            yellow1.setState(InteractiveImage.SELECTED);
            colorSelected1 = YELLOW;
        }

        // color set 2
        if (intersects(x, y, redX2, redY2, red2.get())) {
            red2.setState(InteractiveImage.SELECTED);
            colorSelected2 = RED;
        } else if (intersects(x, y, blueX2, blueY2, blue2.get())) {
            blue2.setState(InteractiveImage.SELECTED);
            colorSelected2 = BLUE;
        } else if (intersects(x, y, greenX2, greenY2, green2.get())) {
            green2.setState(InteractiveImage.SELECTED);
            colorSelected2 = GREEN;
        } else if (intersects(x, y, yellowX2, yellowY2, yellow2.get())) {
            yellow2.setState(InteractiveImage.SELECTED);
            colorSelected2 = YELLOW;
        }

    }

    @Override
    public void mousePressed(MouseEvent e) {
    }

    @Override
    public void mouseReleased(MouseEvent e) {
    }

    @Override
    public void mouseEntered(MouseEvent e) {
    }

    @Override
    public void mouseExited(MouseEvent e) {
    }

    @Override
    public void mouseDragged(MouseEvent e) {
    }

    @Override
    public void mouseMoved(MouseEvent e) {
        // mouse coords
        int x = e.getX();
        int y = e.getY();

        playHover = intersects(x, y, playX, playY, playImg);

        // Color set 1
        if (intersects(x, y, redX1, redY1, red1.get())) // red
            red1.setState(InteractiveImage.HOVER);
        else
            red1.setState(InteractiveImage.NORMAL);

        if (intersects(x, y, blueX1, blueY1, blue1.get())) // blue
            blue1.setState(InteractiveImage.HOVER);
        else
            blue1.setState(InteractiveImage.NORMAL);

        if (intersects(x, y, greenX1, greenY1, green1.get())) // green
            green1.setState(InteractiveImage.HOVER);
        else
            green1.setState(InteractiveImage.NORMAL);

        if (intersects(x, y, yellowX1, yellowY1, yellow1.get())) // yellow
            yellow1.setState(InteractiveImage.HOVER);
        else
            yellow1.setState(InteractiveImage.NORMAL);

        // Color set 2
        if (intersects(x, y, redX2, redY2, red2.get())) // red
            red2.setState(InteractiveImage.HOVER);
        else
            red2.setState(InteractiveImage.NORMAL);

        if (intersects(x, y, blueX2, blueY2, blue2.get())) // blue
            blue2.setState(InteractiveImage.HOVER);
        else
            blue2.setState(InteractiveImage.NORMAL);

        if (intersects(x, y, greenX2, greenY2, green2.get())) // green
            green2.setState(InteractiveImage.HOVER);
        else
            green2.setState(InteractiveImage.NORMAL);

        if (intersects(x, y, yellowX2, yellowY2, yellow2.get())) // yellow
            yellow2.setState(InteractiveImage.HOVER);
        else
            yellow2.setState(InteractiveImage.NORMAL);

    }

    private boolean intersects(int mouseX, int mouseY, int imgX, int imgY, Image img) {
        return mouseX <= imgX + img.getWidth(null) &&
               mouseX >= imgX &&
               mouseY <= imgY + img.getHeight(null) &&
               mouseY >= imgY;
    }
}
