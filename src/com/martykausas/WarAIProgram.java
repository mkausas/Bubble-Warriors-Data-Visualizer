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

        for (int i = 0; i < 2; i++) {
            Fighter c = new Fighter(BasicCharacter.RED, Math.random() * 700 + 900, Math.random() * 700);
            updatables.add(c);
            drawables.add(c);
        }
        for (int i = 0; i < 2; i++) {
            Fighter c = new Fighter(BasicCharacter.BLUE, Math.random() * 300, Math.random() * 700);
            updatables.add(c);
            drawables.add(c);
        }

        Medic m1 = new Medic(BasicCharacter.RED, Math.random() * 700 + 900, Math.random() * 700);
        Medic m2 = new Medic(BasicCharacter.BLUE, Math.random() * 300, Math.random() * 700);
        updatables.add(m1);
        updatables.add(m2);
        drawables.add(m1);
        drawables.add(m2);

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
