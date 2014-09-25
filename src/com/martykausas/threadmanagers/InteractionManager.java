package com.martykausas.threadmanagers;

import com.martykausas.WarAIProgram;
import com.martykausas.interfaces.Updatable;
import com.martykausas.characters.BasicCharacter;
import java.util.ArrayList;

/**
 *
 * @author Marty
 */
public class InteractionManager extends Thread {

    private ArrayList<Updatable> updatables;

    public InteractionManager() {
        updatables = WarAIProgram.getUpdatables();
    }

    @Override
    public void run() {
        while (true) {
            try {
                for (int i = 0; i < updatables.size(); i++) {
                    BasicCharacter temp = (BasicCharacter) updatables.get(i);
                    if (temp.getHealth() <= 0) {
                        WarAIProgram.removeCharacter(temp);
                        break;
                    }

                    BasicCharacter temp2;
                    double tempX = temp.getX();
                    double tempY = temp.getY();
                    double dx = 0;
                    double dy = 0;

                    // call the update function
                    temp.update();

                    // dummy value, closest character will be closer...
                    double closestCharacterDistance = 10000;
                    int closestCharacterIdentifier = -1;


                    // scroll through the other characters, interact with them
                    for (int j = 0; j < updatables.size(); j++) {
                        temp2 = (BasicCharacter) updatables.get(j);

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
//                                    System.out.println(temp.getID() + " has distance " + temp.getDistanceToClosestOpponent() + " to closest opponent");
                                }
                            }
                        }
                    } // end of second for()

                    // if there are still enemies on the other side
                    if (closestCharacterIdentifier != -1) {
                        BasicCharacter opponent = (BasicCharacter) updatables.get(closestCharacterIdentifier);
                        System.out.println("Closest character id = " + closestCharacterIdentifier);

                        // each character has an updated copy of their distance towards their closest opponent
                        temp.setDistanceToClosestOpponent(closestCharacterDistance);
                        temp.setOpponent(opponent);


                        // detecting collision between opponents
                        if (closestCharacterDistance >= (double) BasicCharacter.SIZE) {
                            // no collision, continue moving towards temp2
                            temp.setTarget(
                                    opponent.getX(),
                                    opponent.getY());
                        } else {
                            // stop movement, temp is intersecting
                            temp.setTarget(tempX, tempY);
                        }
                    }
                    // no more enemies left!
                    else {
                        temp.setTarget(tempX, tempY);

                    }
                } // end of first for()
                Thread.sleep(20);
            } catch (Exception ex) { ex.printStackTrace(); }
        }
    }

}