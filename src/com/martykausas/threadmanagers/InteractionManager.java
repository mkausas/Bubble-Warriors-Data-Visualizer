package com.martykausas.threadmanagers;

import com.martykausas.WarAIProgram;
import com.martykausas.interfaces.Updatable;
import com.martykausas.characters.BasicCharacter;
import java.util.ArrayList;

/**
 * Thread that manages interaction between characters
 *
 * @author Marty
 */
public class InteractionManager extends Thread {

    private ArrayList<Updatable> updatables;
    private int team1Count = 0;
    private int team2Count = 0;


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

                    if (temp.getTeam() == BasicCharacter.TEAM1)
                        team1Count++;
                    else
                        team2Count++;

                    BasicCharacter temp2;
                    double tempX = temp.getCenterX();
                    double tempY = temp.getCenterY();
                    double dx = 0;
                    double dy = 0;

                    // TODO: consider making a seperate Update() thread
                    // call the update function
                    temp.update();

                    // dummy value, closest character will be closer...
                    double closestCharacterDistance = 10000;
                    int closestCharacterIdentifier = -1;


                    // scroll through the other characters, interact with them
                    for (int j = 0; j < updatables.size(); j++) {
                        temp2 = (BasicCharacter) updatables.get(j);

                        if (temp.getID() != temp2.getID()) {
                            double temp2X = temp2.getCenterX();
                            double temp2Y = temp2.getCenterY();

                            dx = tempX - temp2X;
                            dy = tempY - temp2Y;

                            // scrolling through characters not of the same type
                            if (temp.getTeam() != temp2.getTeam() &&
                                temp.getType() != BasicCharacter.MEDIC) {

                                double dist = Math.sqrt(
                                    Math.pow(dx, 2) +
                                    Math.pow(dy, 2));

                                if (dist < closestCharacterDistance) {
                                    closestCharacterDistance = dist;
                                    closestCharacterIdentifier = j;
                                }
                            }

                            // scrolling through characters of the same type
                            else if (temp.getTeam() == temp2.getTeam()) {

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
                                } // end of same type interaction

                                // medic code
                                if (temp.getType() == BasicCharacter.MEDIC &&
                                    temp2.getType() != BasicCharacter.MEDIC) {

                                    double dist = Math.sqrt(
                                        Math.pow(dx, 2) +
                                        Math.pow(dy, 2));

                                    if (dist < closestCharacterDistance) {
                                        closestCharacterDistance = dist;
                                        closestCharacterIdentifier = j;
                                    }
                                } // end medic code
                            } // end characters of the same type
                        } // end of characters that aren't the same character...
                    } // end of second for()

                    // if there are still characters to interact with
                    if (closestCharacterIdentifier != -1) {
                        BasicCharacter interactionCharacter = (BasicCharacter) updatables.get(closestCharacterIdentifier);

                        // each character has an updated copy of their distance towards their closest interaction character
                        temp.setDistanceToClosestOpponent(closestCharacterDistance);
                        temp.setInteractionCharacter(interactionCharacter);


                        // detecting collision between characters
                        if (closestCharacterDistance >= (double) BasicCharacter.SIZE) {
                            // no collision, continue moving towards temp2
                            temp.setTarget(
                                    interactionCharacter.getCenterX(),
                                    interactionCharacter.getCenterY());
                        } else {
                            // stop movement, temp is intersecting
                            temp.setTarget(tempX, tempY);
                        }
                    }
                    // no more interaction characters left!
                    else {
                        temp.setTarget(tempX, tempY);

                    }
                    if (i == updatables.size() - 1)
                        setTeamCounts(team1Count, team2Count);

                } // end of first for()


                Thread.sleep(20);
            } catch (Exception ex) { ex.printStackTrace(); }
        }
    }

    public void setTeamCounts(int team1, int team2) {

    }

}