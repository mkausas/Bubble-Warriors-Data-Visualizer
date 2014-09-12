package com.martykausas.threadmanagers;

import com.martykausas.WarAIProgram;
import com.martykausas.interfaces.Actable;
import com.martykausas.prototypingclasses.BasicCharacter;
import java.util.ArrayList;

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
                    BasicCharacter temp = (BasicCharacter) actables.get(i);
                    double tempX = temp.getX();
                    double tempY = temp.getY();

                    // call the update function
                    temp.update();

                    // dummy value, closest character will be closer...
                    double closestCharacterDistance = 10000;
                    int closestCharacterIdentifier = 0;


                    // scroll through the other characters, interact with them
                    for (int j = 0; j < actables.size(); j++) {
                        BasicCharacter temp2 = (BasicCharacter) actables.get(j);

                        if (temp.getID() != temp2.getID()) {

                            double temp2X = temp2.getX();
                            double temp2Y = temp2.getY();

                            double dx = tempX - temp2X;
                            double dy = tempY - temp2Y;

                            // scrolling through characters not of the same type
                            if (temp.getType() != temp2.getType()) {
                                double dist = Math.sqrt(
                                    Math.pow(dx, 2) +
                                    Math.pow(dy, 2));

                                if (dist < closestCharacterDistance) {
                                    closestCharacterDistance = dist;
                                    closestCharacterIdentifier = j;
                                }

                            }

                            // scrolling through characters of the same type
                            else {

                                // found intersection between two same-type temps
                                if (temp.intersects(temp2)) {
                                    // if temp is farther, it's his job to move around temp2
                                    if (temp.getDistanceToClosestOpponent() >
                                        temp2.getDistanceToClosestOpponent()) {
                                        temp.setTarget(tempX - 4 * dx, tempY - 4 * dy);
                                    } else {
                                        temp2.setTarget(temp2X - 4 * dx, temp2Y - 4 * dy);
                                    }
                                }
                            }
                        }
                    } // end of second for()

//                    System.out.println("Closest character distance: " + closestCharacterDistance);

                    // each character has an updated copy of their distance towards their closest opponent
                    temp.setDistanceToClosestOpponent(closestCharacterDistance);

                    // detecting collision between opponents
                    if (closestCharacterDistance >= (double) BasicCharacter.SIZE) {
                        // no collision, continue moving towards temp2
                        temp.setTarget(
                                ((BasicCharacter) actables.get(closestCharacterIdentifier)).getX(),
                                ((BasicCharacter) actables.get(closestCharacterIdentifier)).getY());
                    } else {
                        // stop movement, temp is intersecting
                        temp.setTarget(tempX, tempY);
                    }
                } // end of first for()

                Thread.sleep(20);
            } catch (Exception ex) { ex.printStackTrace(); }
        }
    }

}
