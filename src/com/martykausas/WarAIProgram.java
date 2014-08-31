package com.martykausas;

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
        UpdateManager updateThread = new UpdateManager();

        animationThread.start();
        updateThread.start();
    }

    public void initVariables() {
        AverageJoe character = new AverageJoe();
        actables.add(character);
        drawables.add(character);
    }



    //main
    public static void main(String[] args) {
        new WarAIProgram();
    }

}
