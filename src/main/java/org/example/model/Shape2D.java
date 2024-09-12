package org.example.model;

import java.util.ArrayList;

public class Shape2D {

    private final ArrayList<Point> points;

    public Shape2D(ArrayList<Point> points) {
        this.points = points;
    }

    public ArrayList<Point> getPoints() {
        return points;
    }
}
