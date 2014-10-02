package com.martykausas.prototypingclasses;

import com.martykausas.Frame;
import com.martykausas.interfaces.Drawable;
import com.martykausas.interfaces.Updatable;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;

/**
 *
 * @author Marty
 */
public class CornerText implements Drawable, Updatable {

    private static int
            team1Count = 0,
            team2Count = 0,
            team1Deaths = 0,
            team2Deaths = 0;

    private String
            alive1Text = "",
            alive2Text = "",
            dead1Text = "",
            dead2Text = "";

    private int textSize = 40, backSize = 70;
    private Font herc;

    private int textY, textY2, text1X, text2X;

    private Color back = new Color(123, 134, 131);

    public CornerText() {
        textSize = Frame.screenSize.height / 25;
        textY = (int) (textSize * 0.9);
        System.out.println("text" + textY * 20);
        textY2 = textY + textSize;
        text1X = Frame.screenSize.width / 16;
        text2X = 15 * text1X - 200;
        herc = new Font("Herculanum", 0, textSize);
    }


    @Override
    public void draw(Graphics g) {
        g.setFont(herc);

        g.setColor(back);
        g.fillRect(0, 0, Frame.screenSize.width, backSize);
        g.setColor(Color.BLACK);
        g.drawString(alive1Text, text1X, textY);
        g.drawString(dead1Text, text1X, textY2);
        g.drawString(alive2Text, text2X, textY);
        g.drawString(dead2Text, text2X, textY2);

        update();

    }

    @Override
    public void update() {
        alive1Text = "Alive: " + team1Count;
        alive2Text = "Alive: " + team2Count;
        dead1Text = "Dead: " + team1Deaths;
        dead2Text = "Dead: " + team2Deaths;

    }

    public static void setTeamCounts(int team1Count, int team2Count) {
        CornerText.team1Count = team1Count;
        CornerText.team2Count = team2Count;
    }

    public static void setTeamDeaths(int team1Deaths, int team2Deaths) {
        CornerText.team1Deaths = team1Deaths;
        CornerText.team2Deaths = team2Deaths;
    }


}
