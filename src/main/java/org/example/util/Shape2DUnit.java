package org.example.util;

import org.example.model.Point;
import org.example.model.Shape2D;
import org.jetbrains.annotations.Nullable;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class Shape2DUnit {

    public static boolean isShape(Shape2D shape2D){
        if (shape2D.getPoints().size() < 4)
            return false;

        if (hasDuplicatePoints(shape2D))
            return false;

        return true;
    }

    public static boolean hasDuplicatePoints(Shape2D shape2D) {
        List<Point> pointsIgnoreLastPoint = getPointsIgnoreLast(shape2D);
        Set<Point> set = new HashSet<>(pointsIgnoreLastPoint);

        return set.size() != pointsIgnoreLastPoint.size();
    }

    public static List<Point> getPointsIgnoreLast(Shape2D shape2D) {
        return shape2D.getPoints().subList(0, shape2D.getPoints().size() - 1);
    }

    @Nullable
    public static Point getFirstPoint(Shape2D shape2D) {
        if (shape2D.getPoints().isEmpty())
            return null;

        return shape2D.getPoints().get(0);
    }

    @Nullable
    public static Point getLastPoint(Shape2D shape2D) {
        if (shape2D.getPoints().isEmpty())
            return null;

        return shape2D.getPoints().get(shape2D.getPoints().size() - 1);
    }

    public static Shape2D updatePoints(Shape2D shape2D, List<Point> points) {
        shape2D.getPoints().clear();
        shape2D.getPoints().addAll(points);

        return shape2D;
    }
}
