package org.example.util;

import org.example.model.Point;
import org.example.model.Shape2D;

import java.util.ArrayList;

public class IsValidUtil {
    public static boolean isValid(Shape2D shape) {
        return isValid(shape.getPoints());
    }

    public static boolean isValid(ArrayList<Point> points) {
        if (!Shape2DUnit.isValidPointsBase(points)) {
            return false;
        }

        if (!Shape2DUnit.getFirstPoint(points).equals(Shape2DUnit.getLastPoint(points))) {
            return false;
        }

        // Проверка на самопересечения
        if (IntersectionsUtil.hasIntersections(points)) {
            return false;
        }

        // Проверка на подконтура
        if (SubContoursUtil.hasSubContours(points)) {
            return false;
        }

        return true;
    }
}
