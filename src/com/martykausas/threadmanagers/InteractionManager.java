package com.martykausas.threadmanagers;

import com.martykausas.WarAIProgram;
import com.martykausas.interfaces.Updatable;
import com.martykausas.prototypingclasses.BasicCharacter;
import java.util.ArrayList;

/**
 *
 * @author Marty
 */
public class InteractionManager extends Thread {

    private ArrayList<Updatable> updatables;

    public InteractionManager() {
        updatables = WarAIProgram.updatables;
    }

    @Override
    public void run() {
        while (true) {
            try {
                for (int i = 0; i < updatables.size(); i++) {
                    BasicCharacter temp = (BasicCharacter) updatables.get(i);
                    double tempX = temp.getX();
                    double tempY = temp.getY();
                    double dx = 0;
                    double dy = 0;

                    // call the update function
                    temp.update();

                    // dummy value, closest character will be closer...
                    double closestCharacterDistance = 10000;
                    int closestCharacterIdentifier = 0;


                    // scroll through the other characters, interact with them
                    for (int j = 0; j < updatables.size(); j++) {
                        BasicCharacter temp2 = (BasicCharacter) updatables.get(j);

                        if (temp.getID() != temp2.getID()) {

                            double temp2X = temp2.getX();
                            double temp2Y = temp2.getY();

                            dx = tempX - temp2X;
                            dy = tempY - temp2Y;

                            // scrolling through characters not of the same type
                            if (temp.getTeam() != temp2.getTeam()) {
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
                                    } else { // 4 is just a variable that worked for back movement
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
                                ((BasicCharacter) updatables.get(closestCharacterIdentifier)).getX(),
                                ((BasicCharacter) updatables.get(closestCharacterIdentifier)).getY());
                    } else {
                        // stop movement, temp is intersecting
                        temp.setTarget(tempX, tempY);
//                        temp.setTarget(
//                                ((BasicCharacter) updatables.get(closestCharacterIdentifier)).getX() - 4 * dx,
//                                ((BasicCharacter) updatables.get(closestCharacterIdentifier)).getY() - 4 * dx);
                    }
                } // end of first for()

                Thread.sleep(20);
            } catch (Exception ex) { ex.printStackTrace(); }
        }
    }

}