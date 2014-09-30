package com.martykausas.other;

import java.awt.Image;

/**
 *
 * @author Marty
 */
public class Method {

    public static boolean contains(int x1, int y1, int x2, int y2, int radius) {
        return dist(x1, y1, x2, y2) < radius;
    }

    public static boolean intersects(double x1, double y1, double x2, double y2, int width, int height) {
               return x1 <= x2 + width &&
                        x1 >= x2 &&
                        y1 <= y2 + height &&
                        y1 >= y2;
    }

    public static boolean intersects(int mouseX, int mouseY, int imgX, int imgY, Image img) {
        return Method.intersects((double) mouseX, (double) mouseY, (double) imgX, (double) imgY, img.getWidth(null), img.getHeight(null));
    }

    public static double dist(double x1, double y1, double x2, double y2) {
        return Method.dist(x1 - x2, y1 - y2);
    }

    public static double dist(double dx, double dy) {
        return Math.sqrt(Math.pow(dx, 2) + Math.pow(dy, 2));
    }

}
