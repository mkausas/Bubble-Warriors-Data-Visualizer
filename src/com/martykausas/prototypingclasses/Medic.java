package com.martykausas.prototypingclasses;

/**
 *
 * @author Marty
 */
public class Medic extends BasicCharacter {

    public Medic(int team, double startX, double startY) {
        super(team, startX, startY);
        setIcon("imgs/cross.png", 1);
        setDistanceToInteract(100);
    }

}
