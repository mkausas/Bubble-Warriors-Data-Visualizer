package com.martykausas;

import com.martykausas.interfaces.Actable;
import java.util.ArrayList;

/**
 *
 * @author Marty
 */
public class UpdateManager extends Thread {

    private ArrayList<Actable> actables;

    public UpdateManager() {
        actables = WarAIProgram.actables;
    }

    @Override
    public void run() {
        while (true) {
            try {

                for (int i = 0; i < actables.size(); i++) {
                    actables.get(i).act();
                }

                Thread.sleep(10);
            } catch (Exception ex) { ex.printStackTrace(); }
        }
    }

}
