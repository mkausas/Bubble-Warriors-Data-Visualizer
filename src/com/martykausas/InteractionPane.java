package com.martykausas;

import com.martykausas.other.InteractiveImage;
import com.martykausas.characters.BasicCharacter;
import com.martykausas.characters.Fighter;
import com.martykausas.characters.Medic;
import com.martykausas.other.Method;
import static com.martykausas.other.Method.intersects;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.RenderingHints;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.io.File;
import javax.imageio.ImageIO;
import javax.swing.JPanel;
import javax.swing.JTextField;

/**
 *
 * @author Marty
 */
public class InteractionPane extends JPanel implements MouseListener, MouseMotionListener {

    private Image
            titleImg,

            colorSelectionImg,
            countImg,

            playImg,
            playImgHover;

    private boolean
            startGame = false,

            fighter1Select = true,
            fighter2Select = true,

            medic1Select = false,
            medic2Select = false,

            playHover = false;

    public static final int
            RED = 0,
            BLUE = 1,
            GREEN = 2,
            YELLOW = 3;

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

            count1X,
            count1Y,
            count1Width,
            count1Height,
            countX,
            countY,

            characterY,
            fighter1X,
            fighter2X,
            medic1X,
            medic2X,
            characterSize,
            halfCharacterSize,
            characterSeperation,
            characterSelectionSize,
            halfCharacterSelectionSize,

            shadowY,
            fighter1ShadowX,
            fighter2ShadowX,
            medic1ShadowX,
            medic2ShadowX,
            fighter1CenterX,
            fighter2CenterX,
            medic1CenterX,
            medic2CenterX,
            fighter1CenterY,
            fighter2CenterY,
            medic1CenterY,
            medic2CenterY,

            playX,
            playY,

            colorSelected1 = RED,
            colorSelected2 = BLUE,

            army1Count = 30,
            army2Count = 30;

    private InteractiveImage
            red1, green1,
            blue1, yellow1,

            red2, green2,
            blue2, yellow2;

    private JTextField
            team1Count,
            team2Count,

            team1Stock,
            team2Stock;

    private Fighter
            fighter1,
            fighter2;

    private Medic
            medic1,
            medic2;

    public InteractionPane() {
        setSize(Frame.screenSize);
        initVars();
        addMouseListener(this);
        addMouseMotionListener(this);
    }

    private void initVars() {
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
            countImg = ImageIO.read(new File("imgs/count.png"));

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
//        redY1 = halfScreenHeight;
        redY1 = (int) (titleY + titleImg.getHeight(null) * 1.4);

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

        // color selection divider image
        colorSelectionX = halfScreenWidth - colorSelectionImg.getWidth(null) / 2;
        colorSelectionY = redY1;
        /** End Color Coordinates **/

        /*** Start team counts ***/
        // team 1
        count1X = halfScreenWidth - 200;
        count1Y = (int) (redY1 + colorSize * 2.5);
        count1Width = 125;
        count1Height = 75;
        team1Count = new JTextField("30");
        team1Count.setFont(new java.awt.Font("Herculanum", 0, 60));
        team1Count.setHorizontalAlignment(javax.swing.JTextField.RIGHT);
        team1Count.setBounds(count1X, count1Y, count1Width, count1Height);
        add(team1Count);

        // team 2
        team2Count = new JTextField("30");
        team2Count.setFont(new Font("Herculanum", 0, 60));
        team2Count.setBounds(halfScreenWidth + 75, count1Y, count1Width, count1Height);
        add(team2Count);

        countX = halfScreenWidth - (countImg.getWidth(null) / 2);
        countY = count1Y;
        /*** End team counts ***/

        /*** Start Example fighter and medic displays ***/
        characterSize = 80;
        halfCharacterSize = 40;
        characterSeperation = (int) (characterSize * 1.3);
        fighter1X = halfScreenWidth - 200;
        characterY = countY + 100;
        medic1X = fighter1X + characterSeperation;
        medic2X = medic1X + characterSeperation;
        fighter2X = medic2X + characterSeperation;

        fighter1 = new Fighter(BasicCharacter.TEAM1, fighter1X, characterY, BasicCharacter.RED);
        fighter1.setSize(characterSize);
        fighter1.update();

        medic1 = new Medic(BasicCharacter.TEAM1, medic1X, characterY, BasicCharacter.RED);
        medic1.setSize(characterSize);
        medic1.update();

        medic2 = new Medic(BasicCharacter.TEAM2, medic2X, characterY, BasicCharacter.RED);
        medic2.setSize(characterSize);
        medic2.update();

        fighter2 = new Fighter(BasicCharacter.TEAM2, fighter2X, characterY, BasicCharacter.RED);
        fighter2.setSize(characterSize);
        fighter2.update();

        // selected (shadow) coords
        characterSelectionSize = characterSize + 10;
        halfCharacterSelectionSize = characterSelectionSize / 2;
        fighter1CenterX = (int) fighter1.getCenterX();
        fighter1CenterY = (int) fighter1.getCenterY();
        fighter2CenterX = (int) fighter2.getCenterX();
        fighter2CenterY = (int) fighter2.getCenterY();
        medic1CenterX = (int) medic1.getCenterX();
        medic1CenterY = (int) medic1.getCenterY();
        medic2CenterX = (int) medic2.getCenterX();
        medic2CenterY = (int) medic2.getCenterY();


        shadowY = fighter1CenterY - halfCharacterSelectionSize;
        fighter1ShadowX = fighter1CenterX - halfCharacterSelectionSize;
        fighter2ShadowX = fighter2CenterX - halfCharacterSelectionSize;
        medic1ShadowX = medic1CenterX - halfCharacterSelectionSize;
        medic2ShadowX = medic2CenterX - halfCharacterSelectionSize;
        /*** End Example fighter and medic displays ***/

//        /*** Stock options ***


        playX = halfScreenWidth - (playImg.getWidth(null) / 2);
        playY =  6 * frameHeight / 9;
    }


    @Override
    public void paint(Graphics g) {
        super.paint(g);
        Graphics2D g2d = (Graphics2D) g;
        g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

        g2d.drawImage(titleImg, titleX, titleY, null);

        // first color set
        g2d.drawImage(colorSelected1 == RED ? red1.get(InteractiveImage.SELECTED) : red1.get(), redX1, redY1, null);
        g2d.drawImage(colorSelected1 == BLUE ? blue1.get(InteractiveImage.SELECTED) : blue1.get(), blueX1, blueY1, null);
        g2d.drawImage(colorSelected1 == GREEN ? green1.get(InteractiveImage.SELECTED) : green1.get(), greenX1, greenY1, null);
        g2d.drawImage(colorSelected1 == YELLOW ? yellow1.get(InteractiveImage.SELECTED) : yellow1.get(), yellowX1, yellowY1, null);

        // first color set
        g2d.drawImage(colorSelected2 == RED ? red2.get(InteractiveImage.SELECTED) : red2.get(), redX2, redY2, null);
        g2d.drawImage(colorSelected2 == BLUE ? blue2.get(InteractiveImage.SELECTED) : blue2.get(), blueX2, blueY2, null);
        g2d.drawImage(colorSelected2 == GREEN ? green2.get(InteractiveImage.SELECTED) : green2.get(), greenX2, greenY2, null);
        g2d.drawImage(colorSelected2 == YELLOW ? yellow2.get(InteractiveImage.SELECTED) : yellow2.get(), yellowX2, yellowY2, null);

        // color selector
        g2d.drawImage(colorSelectionImg, colorSelectionX, colorSelectionY, null);

        // count seperator
        g2d.drawImage(countImg, countX, countY, null);

        // shadows
        g2d.setColor(Color.BLACK);
        if (fighter1Select) {
            g2d.fillOval(fighter1ShadowX, shadowY, characterSelectionSize, characterSelectionSize);
        }

        if (medic1Select) {
            g2d.fillOval(medic1ShadowX, shadowY, characterSelectionSize, characterSelectionSize);
        }

        if (medic2Select) {
            g2d.fillOval(medic2ShadowX, shadowY, characterSelectionSize, characterSelectionSize);
        }

        if (fighter2Select) {
            g2d.fillOval(fighter2ShadowX, shadowY - 1, characterSelectionSize, characterSelectionSize);
        }


        fighter1.draw(g2d);
        fighter1.setColor(colorSelected1);

        fighter2.draw(g2d);
        fighter2.setColor(colorSelected2);

        medic1.draw(g2d);
        medic1.setColor(colorSelected1);

        medic2.draw(g2d);
        medic2.setColor(colorSelected2);

        // play button
        g2d.drawImage(playHover ? playImgHover : playImg, playX, playY, null);

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

        startGame = Method.intersects(x, y, playX, playY, playImg);

        // color set 1
        if (Method.intersects(x, y, redX1, redY1, red1.get())) {
            red1.setState(InteractiveImage.SELECTED);
            colorSelected1 = RED;
        } else if (Method.intersects(x, y, blueX1, blueY1, blue1.get())) {
            blue1.setState(InteractiveImage.SELECTED);
            colorSelected1 = BLUE;
        } else if (Method.intersects(x, y, greenX1, greenY1, green1.get())) {
            green1.setState(InteractiveImage.SELECTED);
            colorSelected1 = GREEN;
        } else if (Method.intersects(x, y, yellowX1, yellowY1, yellow1.get())) {
            yellow1.setState(InteractiveImage.SELECTED);
            colorSelected1 = YELLOW;
        }

        // color set 2
        if (Method.intersects(x, y, redX2, redY2, red2.get())) {
            red2.setState(InteractiveImage.SELECTED);
            colorSelected2 = RED;
        } else if (Method.intersects(x, y, blueX2, blueY2, blue2.get())) {
            blue2.setState(InteractiveImage.SELECTED);
            colorSelected2 = BLUE;
        } else if (Method.intersects(x, y, greenX2, greenY2, green2.get())) {
            green2.setState(InteractiveImage.SELECTED);
            colorSelected2 = GREEN;
        } else if (Method.intersects(x, y, yellowX2, yellowY2, yellow2.get())) {
            yellow2.setState(InteractiveImage.SELECTED);
            colorSelected2 = YELLOW;
        }


        // shadows
        if (Method.contains(x, y, fighter1CenterX, fighter1CenterY, halfCharacterSize)) {
            fighter1Select = !fighter1Select;
        }

        if (Method.contains(x, y, medic1CenterX, medic1CenterY, halfCharacterSize)) {
            medic1Select = !medic1Select;
        }

        if (Method.contains(x, y, medic2CenterX, medic2CenterY, halfCharacterSize)) {
            medic2Select = !medic2Select;
        }

        if (Method.contains(x, y, fighter2CenterX, fighter2CenterY, halfCharacterSize)) {
            fighter2Select = !fighter2Select;
        }
    }

    @Override
    public void mouseMoved(MouseEvent e) {
        // mouse coords
        int x = e.getX();
        int y = e.getY();

        playHover = Method.intersects(x, y, playX, playY, playImg);

        // Color set 1
        if (Method.intersects(x, y, redX1, redY1, red1.get())) // red
            red1.setState(InteractiveImage.HOVER);
        else
            red1.setState(InteractiveImage.NORMAL);

        if (Method.intersects(x, y, blueX1, blueY1, blue1.get())) // blue
            blue1.setState(InteractiveImage.HOVER);
        else
            blue1.setState(InteractiveImage.NORMAL);

        if (Method.intersects(x, y, greenX1, greenY1, green1.get())) // green
            green1.setState(InteractiveImage.HOVER);
        else
            green1.setState(InteractiveImage.NORMAL);

        if (Method.intersects(x, y, yellowX1, yellowY1, yellow1.get())) // yellow
            yellow1.setState(InteractiveImage.HOVER);
        else
            yellow1.setState(InteractiveImage.NORMAL);

        // Color set 2
        if (Method.intersects(x, y, redX2, redY2, red2.get())) // red
            red2.setState(InteractiveImage.HOVER);
        else
            red2.setState(InteractiveImage.NORMAL);

        if (Method.intersects(x, y, blueX2, blueY2, blue2.get())) // blue
            blue2.setState(InteractiveImage.HOVER);
        else
            blue2.setState(InteractiveImage.NORMAL);

        if (Method.intersects(x, y, greenX2, greenY2, green2.get())) // green
            green2.setState(InteractiveImage.HOVER);
        else
            green2.setState(InteractiveImage.NORMAL);

        if (Method.intersects(x, y, yellowX2, yellowY2, yellow2.get())) // yellow
            yellow2.setState(InteractiveImage.HOVER);
        else
            yellow2.setState(InteractiveImage.NORMAL);
    }

    public int[] getInitInstructions() {
        int[] initInstructions = {
            colorSelected1,
            colorSelected2,
            Integer.parseInt(team1Count.getText()),
            Integer.parseInt(team2Count.getText()),
            fighter1Select ? 0 : 1,
            fighter2Select ? 0 : 1,
            medic1Select ? 0 : 1,
            medic2Select ? 0 : 1
        };
        return initInstructions;
    }

    /*** Unused mouse listener methods ***/
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
}
