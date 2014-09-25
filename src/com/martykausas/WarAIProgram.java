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



        // start the simulator
        initArmies();

        AnimationManager animationThread = new AnimationManager(frame.panel);
        InteractionManager interactionThread = new InteractionManager();

        animationThread.start();
        interactionThread.start();

    }

    public void initArmies() {

        for (int i = 0; i < 10; i++) {
            BasicCharacter c;
                if (i % 5 == 0) {
                    c = new Medic(BasicCharacter.RED, Math.random() * 700 + 900, Math.random() * 700);
                } else {
                    c = new Fighter(BasicCharacter.RED, Math.random() * 700 + 900, Math.random() * 700);
                }
            updatables.add(c);
            drawables.add(c);
        }
        for (int i = 0; i < 10; i++) {
            BasicCharacter c;
            if (i % 5 == 0) {
                c = new Medic(BasicCharacter.BLUE, Math.random(), Math.random() * 700);
            } else {
                c = new Fighter(BasicCharacter.BLUE, Math.random() * 300, Math.random() * 700);
            }

            updatables.add(c);
            drawables.add(c);
        }

        Medic m1 = new Medic(BasicCharacter.BLUE, 700, 300);
//        Medic m2 = new Medic(BasicCharacter.BLUE, Math.random() * 300, Math.random() * 700);
        updatables.add(m1);
//        updatables.add(m2);
        drawables.add(m1);
//        drawables.add(m2);

//        Fighter f1 = new Fighter(BasicCharacter.RED, Math.random() * 700, Math.random() * 700);
//        Fighter f2 = new Fighter(BasicCharacter.BLUE, Math.random() * 300, Math.random() * 700);
//        updatables.add(f1);
//        updatables.add(f2);
//        drawables.add(f1);
//        drawables.add(f2);

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
