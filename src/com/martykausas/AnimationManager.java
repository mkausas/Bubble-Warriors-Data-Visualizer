package com.martykausas;

/**
 *
 * @author Marty
 */
public class AnimationManager extends Thread {

    private Panel panel;

    public AnimationManager(Panel panel) {
        this.panel = panel;
    }

    @Override
    public void run() {
        while (true) {
            try {

                panel.repaint();

                Thread.sleep(10);
            } catch(Exception ex) { ex.printStackTrace(); }
        }
    }

}
