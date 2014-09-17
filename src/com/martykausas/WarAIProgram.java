package com.martykausas;

import com.martykausas.prototypingclasses.Fighter;
import com.martykausas.threadmanagers.AnimationManager;
import com.martykausas.threadmanagers.InteractionManager;
import com.martykausas.interfaces.Updatable;
import com.martykausas.interfaces.Drawable;
import com.martykausas.prototypingclasses.BasicCharacter;
import com.martykausas.prototypingclasses.Medic;
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

        initVariables();

        AnimationManager animationThread = new AnimationManager(frame.panel);
        InteractionManager interactionThread = new InteractionManager();

        animationThread.start();
        interactionThread.start();

    }

    public void initVariables() {
//        BasicCharacter character = new BasicCharacter(BasicCharacter.BLUE, 100, 400);
//        BasicCharacter character2 = new BasicCharacter(BasicCharacter.RED, 300, 300);
//        BasicCharacter character3 = new BasicCharacter(BasicCharacter.RED, 500, 100);
//        BasicCharacter character4 = new BasicCharacter(BasicCharacter.RED, 700, 500);
//        BasicCharacter character5 = new BasicCharacter(BasicCharacter.RED, 900, 400);



        for (int i = 0; i < 50; i++) {
            Fighter c = new Fighter(BasicCharacter.RED, Math.random() * 700 + 900, Math.random() * 700);
            updatables.add(c);
            drawables.add(c);
        }
        for (int i = 0; i < 50; i++) {
            Fighter c = new Fighter(BasicCharacter.BLUE, Math.random() * 300, Math.random() * 700);
            updatables.add(c);
            drawables.add(c);
        }


//        updatables.add(character);
//        updatables.add(character2);
//        updatables.add(character3);
//        updatables.add(character4);
//        updatables.add(character5);
//        drawables.add(character);
//        drawables.add(character2);
//        drawables.add(character3);
//        drawables.add(character4);
//        drawables.add(character5);
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
