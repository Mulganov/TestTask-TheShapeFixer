package org.example.util;

import org.example.model.Point;
import org.example.model.Shape2D;
import org.jetbrains.annotations.Nullable;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class Shape2DUnit {

    public static boolean isValidPointsBase(Shape2D shape2D){
        return isValidPointsBase(shape2D.getPoints());
    }

    public static boolean isValidPointsBase(ArrayList<Point> points){
        if (points.size() < 4)
            return false;

        if (hasDuplicatePoints(points))
            return false;

        return true;
    }

    public static boolean hasDuplicatePoints(ArrayList<Point> points) {
        List<Point> pointsIgnoreLastPoint = getPointsIgnoreLast(points);
        Set<Point> set = new HashSet<>(pointsIgnoreLastPoint);

        return set.size() != pointsIgnoreLastPoint.size();
    }

    public static List<Point> getPointsIgnoreLast(ArrayList<Point> points) {
        return points.subList(0, points.size() - 1);
    }

    @Nullable
    public static Point getFirstPoint(ArrayList<Point> points) {
        if (points.isEmpty())
            return null;

        return points.get(0);
    }

    @Nullable
    public static Point getLastPoint(ArrayList<Point> points) {
        if (points.isEmpty())
            return null;

        return points.get(points.size() - 1);
    }

    public static Shape2D updatePoints(Shape2D shape2D, List<Point> points) {
        shape2D.getPoints().clear();
        shape2D.getPoints().addAll(points);

        return shape2D;
    }
}
