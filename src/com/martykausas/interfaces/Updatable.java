package com.martykausas.interfaces;

/**
 * Interfaces for classes that need constant updating
 *
 * @author Marty
 */
public interface Updatable {

    /**
     * Update is called continuously in a thread in the {@see InteractionManager}
     */
    public void update();
}
