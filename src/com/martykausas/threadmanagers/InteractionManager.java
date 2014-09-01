package com.martykausas.threadmanagers;

import com.martykausas.Frame;
import com.martykausas.WarAIProgram;
import com.martykausas.interfaces.Actable;
import com.martykausas.testingclasses.AverageJoe;
import java.util.ArrayList;
import static javax.swing.UIManager.get;

/**
 *
 * @author Marty
 */
public class InteractionManager extends Thread {

    private ArrayList<Actable> actables;

    public InteractionManager() {
        actables = WarAIProgram.actables;
    }

    @Override
    public void run() {
        while (true) {
            try {

                for (int i = 0; i < actables.size(); i++) {
                    AverageJoe temp = (AverageJoe) actables.get(i);

                    // call the update function
                    temp.update();

                    // dummy value, closest character will be closer...
                    double closestCharacterDistance = 1000;
                    int closestCharacterIdentifier = 0;


                    // look for the closest character
                    for (int j = 0; j < actables.size(); j++) {
                        AverageJoe temp2 = (AverageJoe) actables.get(j);
                        if (temp.getType() != temp2.getType()) { // not on same team
                            double dist = Math.sqrt(
                                Math.pow(temp.getX() - temp2.getX(), 2) +
                                Math.pow(temp.getY() - temp.getY(), 2));

                            if (dist < closestCharacterDistance) {
                                closestCharacterDistance = dist;
                                closestCharacterIdentifier = j;
                            }
                        }
                    }


//                    if ()

                    // set the new target
                    temp.setTarget(
                            ((AverageJoe) actables.get(closestCharacterIdentifier)).getX(),
                            ((AverageJoe) actables.get(closestCharacterIdentifier)).getY());

                }

                Thread.sleep(20);
            } catch (Exception ex) { ex.printStackTrace(); }
        }
    }

}
