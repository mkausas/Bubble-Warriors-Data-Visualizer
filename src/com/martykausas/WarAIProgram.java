package com.martykausas;

import com.martykausas.characters.Fighter;
import com.martykausas.threadmanagers.AnimationManager;
import com.martykausas.threadmanagers.InteractionManager;
import com.martykausas.interfaces.Updatable;
import com.martykausas.interfaces.Drawable;
import com.martykausas.characters.BasicCharacter;
import com.martykausas.characters.Medic;
import com.martykausas.prototypingclasses.CornerText;
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

        // start the simulator
        initArmies();

        AnimationManager animationThread = new AnimationManager(frame.panel);
        InteractionManager interactionThread = new InteractionManager();

        animationThread.start();
        interactionThread.start();

    }

    public void initArmies() {

//        for (int i = 0; i < 10; i++) {
//            BasicCharacter c;
//                if (i % 5 == 0) {
//                    c = new Medic(BasicCharacter.TEAM1, Math.random() * 700 + 900, Math.random() * 700);
//                } else {
//                    c = new Fighter(BasicCharacter.TEAM1, Math.random() * 700 + 900, Math.random() * 700);
//                }
//            updatables.add(c);
//            drawables.add(c);
//        }
//        for (int i = 0; i < 10; i++) {
//            BasicCharacter c;
//            if (i % 5 == 0) {
//                c = new Medic(BasicCharacter.TEAM2, Math.random(), Math.random() * 700);
//            } else {
//                c = new Fighter(BasicCharacter.TEAM2, Math.random() * 300, Math.random() * 700);
//            }
//
//            updatables.add(c);
//            drawables.add(c);
//        }

        CornerText t = new CornerText();
//        updatables.add(t);
        drawables.add(t);

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
