package com.martykausas.threadmanagers;

import com.martykausas.WarAIProgram;
import com.martykausas.interfaces.Actable;
import com.martykausas.prototypingclasses.AverageJoe;
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
                    AverageJoe temp = (AverageJoe) actables.get(i);
                    double tempX = temp.getX();
                    double tempY = temp.getY();

                    // call the update function
                    temp.update();

                    // dummy value, closest character will be closer...
                    double closestCharacterDistance = 10000;
                    int closestCharacterIdentifier = 0;


                    // scroll through the other characters, interact with them
                    for (int j = 0; j < actables.size(); j++) {
                        AverageJoe temp2 = (AverageJoe) actables.get(j);

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

//                                System.out.println("type = " + temp.getType() + " and "
//                                        + temp2.getType() + " dx " + dx + ", dy " + dy);


                                if (temp.intersects(temp2)) {
                                    temp2.setTarget(temp2X, temp2Y);
                                    /*
//                                    System.out.println("character of same type intersecting");
                                    // more "intersection" in the x
                                    if (Math.abs(dx) > Math.abs(dy)) {
                                        // temp is lower down then temp2
                                        if (tempY > temp2Y) {
                                            System.out.println(temp.getID() + " is lower down then " + temp2.getID());
                                            // move temp2 up dy * multiplier
                                            temp2.setTarget(
                                                    temp2.getSetpointX(),
                                                    temp2.getSetpointY() + (dy * 2));
                                        }

                                        // temp is higher up then temp2
                                        else {
                                            // move temp2 down dy * multiplier
                                            temp2.setTarget(
                                                    temp2.getSetpointX(),
                                                    temp2.getSetpointY() - (dy * 2));
                                        }
                                    }

                                    // more "intersection" in the y
                                    else {
                                        System.out.println("more intersection in the y");
                                        // temp is right of temp2
                                        if (tempX > temp2X) {
                                            // move temp2 up dy * multiplier
                                            temp2.setTarget(
                                                    temp2.getSetpointX() + (dx * 2),
                                                    temp2.getSetpointY());
                                        }

                                        // temp is left of temp2
                                        else {
                                            // move temp2 right dy * multiplier
                                            temp2.setTarget(
                                                    temp2.getSetpointX() - (dx * 2),
                                                    temp2.getSetpointY());
                                        }
                                    } */
                                }
                            }
                        }
                    } // end of second for()

//                    System.out.println("Closest character distance: " + closestCharacterDistance);

                    // detecting
                    if (closestCharacterDistance >= (double) AverageJoe.SIZE) {
                        // set the new target
                        temp.setTarget(
                                ((AverageJoe) actables.get(closestCharacterIdentifier)).getX(),
                                ((AverageJoe) actables.get(closestCharacterIdentifier)).getY());
                    } else {
                        temp.setTarget(tempX, tempY);
                    }
                } // end of first for()

                Thread.sleep(20);
            } catch (Exception ex) { ex.printStackTrace(); }
        }
    }

}
