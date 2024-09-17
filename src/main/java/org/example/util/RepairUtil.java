package org.example.util;

import org.example.model.Point;
import org.example.model.Shape2D;

import javax.rmi.ssl.SslRMIClientSocketFactory;
import java.util.ArrayList;
import java.util.List;

public class RepairUtil {
    public static Shape2D repair(Shape2D shape) {
        if (IsValidUtil.isValid(shape))
            return shape;

        ArrayList<Point> points = new ArrayList<>(shape.getPoints());

        removeIntersections(points);

        removeSubContours(points);

        return Shape2DUnit.updatePoints(shape, points);
    }

    private static void removeSubContours(ArrayList<Point> points) {
        while (IntersectionsUtil.getIntersectionPoint(points) != null){
            if (IsValidUtil.isValid(points))
                return;

            points.remove(IntersectionsUtil.getIntersectionPoint(points));
        }
    }

    private static void removeIntersections(ArrayList<Point> points) {
        while (SubContoursUtil.getSubContourPoint(points) != null){
            if (IsValidUtil.isValid(points))
                return;

            points.remove(SubContoursUtil.getSubContourPoint(points));
        }
    }
}
