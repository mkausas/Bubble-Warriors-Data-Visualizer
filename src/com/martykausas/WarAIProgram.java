package com.martykausas;

import com.martykausas.threadmanagers.AnimationManager;
import com.martykausas.threadmanagers.InteractionManager;
import com.martykausas.interfaces.Actable;
import com.martykausas.interfaces.Drawable;
import com.martykausas.testingclasses.AverageJoe;
import java.util.ArrayList;

/**
 *
 * @author Marty Kausas
 */
public class WarAIProgram {

    public static ArrayList actables = new ArrayList<Actable>();
    public static ArrayList drawables = new ArrayList<Drawable>();

    public WarAIProgram() {
        Frame frame = new Frame();

        initVariables();

        AnimationManager animationThread = new AnimationManager(frame.panel);
        InteractionManager interactionThread = new InteractionManager();

        animationThread.start();
        interactionThread.start();
    }

    public void initVariables() {
        AverageJoe character = new AverageJoe(AverageJoe.BLUE, 100, 100);
        AverageJoe character2 = new AverageJoe(AverageJoe.RED, 900, 600);

        actables.add(character);
        actables.add(character2);
        drawables.add(character);
        drawables.add(character2);
    }



    //main
    public static void main(String[] args) {
        new WarAIProgram();
    }

}
