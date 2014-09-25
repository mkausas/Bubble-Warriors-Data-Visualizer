package com.martykausas.characters;

import com.martykausas.characters.BasicCharacter;
import java.awt.Graphics;

/**
 *
 * @author Marty
 */
public class Fighter extends BasicCharacter {

    private int damageCoefficient = 2;

    public Fighter(int type, double startX, double startY) {
        super(type, startX, startY);
        setIcon("imgs/sword.png");
        setDistanceToInteract(100);
        setHealth(255);
    }

    @Override
    public void childUpdate() {
        if (readyToInteract()) {
            BasicCharacter opponent = getOpponent();
            opponent.deductHealth(damageCoefficient);
        }
    }

    @Override
    public void childDraw(Graphics g) {
    }



}
