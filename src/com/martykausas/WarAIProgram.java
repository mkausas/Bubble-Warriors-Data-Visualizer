package com.martykausas;

import com.martykausas.threadmanagers.AnimationManager;
import com.martykausas.threadmanagers.InteractionManager;
import com.martykausas.interfaces.Actable;
import com.martykausas.interfaces.Drawable;
import com.martykausas.prototypingclasses.AverageJoe;
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
        AverageJoe character = new AverageJoe(AverageJoe.BLUE, 100, 400);
        AverageJoe character2 = new AverageJoe(AverageJoe.RED, 300, 300);
        AverageJoe character3 = new AverageJoe(AverageJoe.RED, 500, 100);
        AverageJoe character4 = new AverageJoe(AverageJoe.RED, 700, 500);
        AverageJoe character5 = new AverageJoe(AverageJoe.RED, 900, 400);



        for (int i = 0; i < 50; i++) {
            AverageJoe c = new AverageJoe(AverageJoe.RED, Math.random() * 700 + 200, Math.random() * 700);
            actables.add(c);
            drawables.add(c);
        }

        actables.add(character);
//        actables.add(character2);
//        actables.add(character3);
//        actables.add(character4);
//        actables.add(character5);
        drawables.add(character);
//        drawables.add(character2);
//        drawables.add(character3);
//        drawables.add(character4);
//        drawables.add(character5);
    }



    //main
    public static void main(String[] args) {
        new WarAIProgram();
    }

}
