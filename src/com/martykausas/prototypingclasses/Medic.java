package com.martykausas.prototypingclasses;

/**
 *
 * @author Marty
 */
public class Medic extends BasicCharacter {

    public Medic(int team, double startX, double startY) {
        super(team, startX, startY);
        setIcon("imgs/cross.png");
        setDistanceToInteract(100);
    }

}
