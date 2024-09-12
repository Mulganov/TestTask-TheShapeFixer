package org.example.util;

import org.example.model.Point;
import org.example.model.Shape2D;

import java.util.ArrayList;
import java.util.List;

public class SubContoursUtil {

    public static boolean hasSubContours(Shape2D shape) {
        return hasSubContours(shape.getPoints());
    }

    public static boolean hasSubContours(List<Point> points) {
        for (int i = 0; i < points.size(); i++) {
            Point currentPoint = points.get(i);

            List<Point> remainingPoints = new ArrayList<>(points);
            remainingPoints.remove(i);

            if (!GeometryUtil.isPointOnEdge(currentPoint, remainingPoints) &&
                    GeometryUtil.isPointInsideShape(currentPoint, remainingPoints)) {
                return true;
            }
        }
        return false;
    }

}
