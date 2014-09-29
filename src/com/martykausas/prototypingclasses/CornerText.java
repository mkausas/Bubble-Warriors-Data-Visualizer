package com.martykausas.prototypingclasses;

import com.martykausas.Frame;
import com.martykausas.interfaces.Drawable;
import com.martykausas.interfaces.Updatable;
import java.awt.Graphics;

/**
 *
 * @author Marty
 */
public class CornerText implements Drawable, Updatable {

    private static int
            team1Count = 0,
            team2Count = 0;

    private String textLeft = "300", textRight = "300";

    public CornerText() {

    }

    @Override
    public void draw(Graphics g) {
        g.drawString(textLeft, Frame.screenSize.width / 16, Frame.screenSize.height / 10);
        g.drawString(textLeft, (int) 10 * Frame.screenSize.width / 16, Frame.screenSize.height / 10);
        update();

    }

    @Override
    public void update() {
        textLeft = "Team 1 count: " + team1Count;
        textRight = "Team 2 count: " + team2Count;
    }

    public static void setTeamCounts(int team1, int team2) {
        team1Count = team1;
        team2Count = team2;
    }


}
