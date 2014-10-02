package com.martykausas;

import com.martykausas.characters.Fighter;
import com.martykausas.threadmanagers.AnimationManager;
import com.martykausas.threadmanagers.InteractionManager;
import com.martykausas.interfaces.Updatable;
import com.martykausas.interfaces.Drawable;
import com.martykausas.characters.BasicCharacter;
import com.martykausas.characters.Medic;
import java.util.ArrayList;

/**
 *
 * @author Marty Kausas
 */
public class WarAIProgram {

    private static ArrayList updatables = new ArrayList<Updatable>();
    private static ArrayList drawables = new ArrayList<Drawable>();

    public WarAIProgram() {
        Frame frame = new Frame();

        InteractionPane pane = new InteractionPane();

        frame.add(pane);

        while (!pane.play()) {
            try {
                pane.repaint();
                Thread.sleep(20);
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        }

        frame.remove(pane);

        // start the simulator
        initArmies(pane.getInitInstructions());

        AnimationManager animationThread = new AnimationManager(frame.panel);
        InteractionManager interactionThread = new InteractionManager();

        animationThread.start();
        interactionThread.start();

    }

    public void initArmies(int[] instructions) {
        // grab instructions provided from interaction pane
        int colorSelected1 = instructions[0];
        int colorSelected2 = instructions[1];
        int armyCount1 = instructions[2];
        int armyCount2 = instructions[3];
        boolean fighter1Selected = instructions[4] == 0;
        boolean fighter2Selected = instructions[5] == 0;
        boolean medic1Selected = instructions[6] == 0;
        boolean medic2Selected = instructions[7] == 0;

        // init army 1
        for (int i = 0; i < armyCount1; i++) {
            BasicCharacter c = null;
                if (medic1Selected && i % 5 == 0) {
                    c = new Medic(BasicCharacter.TEAM1, Math.random() * 300, Math.random() * 700 + 70, colorSelected1);
                } else if (fighter1Selected) {
                    c = new Fighter(BasicCharacter.TEAM1, Math.random() * 300, Math.random() * 700 + 70, colorSelected1);
                }
            updatables.add(c);
            drawables.add(c);
        }

        // init army2
        for (int i = 0; i < armyCount2; i++) {
            BasicCharacter c = null;
                if (medic2Selected && i % 5 == 0) {
                    c = new Medic(BasicCharacter.TEAM2, Math.random() * 700 + 900, Math.random() * 700 + 70, colorSelected2);
                } else if (fighter2Selected) {
                    c = new Fighter(BasicCharacter.TEAM2, Math.random() * 700 + 900, Math.random() * 700 + 70, colorSelected2);
                }
            updatables.add(c);
            drawables.add(c);
        }
    }



    //main
    public static void main(String[] args) {
        new WarAIProgram();
    }

    public static ArrayList<Drawable> getDrawables() {
        return drawables;
    }

    public static ArrayList<Updatable> getUpdatables() {
        return updatables;
    }

    public static void removeCharacter(BasicCharacter character) {
        drawables.remove(character);
        updatables.remove(character);
    }

}
