package org.example.util;

import org.example.model.Shape2D;

public class IsValidUtil {
    public static boolean isValid(Shape2D shape) {
        if (!Shape2DUnit.isShape(shape)) {
            return false;
        }

        if (!Shape2DUnit.getFirstPoint(shape).equals(Shape2DUnit.getLastPoint(shape))) {
            return false;
        }

        // Проверка на самопересечения
        if (IntersectionsUtil.hasIntersections(shape)) {
            return false;
        }

        // Проверка на подконтура
        if (SubContoursUtil.hasSubContours(shape)) {
            return false;
        }

        return true;
    }
}
