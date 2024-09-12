package org.example.util;

import org.example.model.Point;

import java.util.List;

public class GeometryUtil {

    public static boolean isPointOnSegment(double px, double py, double x1, double y1, double x2, double y2) {
        // Проверяем, что точка находится в пределах отрезка по X и Y
        return (Math.min(x1, x2) - 1e-10 <= px && px <= Math.max(x1, x2) + 1e-10) &&
                (Math.min(y1, y2) - 1e-10 <= py && py <= Math.max(y1, y2) + 1e-10);
    }

    // Обновленный метод isPointOnSegment
    public static boolean isPointOnSegment(double px, double py, double x1, double y1, double x2, double y2, boolean allowEnds) {
        if (allowEnds) {
            return (Math.min(x1, x2) - 1e-10 <= px && px <= Math.max(x1, x2) + 1e-10) &&
                    (Math.min(y1, y2) - 1e-10 <= py && py <= Math.max(y1, y2) + 1e-10);
        } else {
            return (Math.min(x1, x2) < px && px < Math.max(x1, x2)) &&
                    (Math.min(y1, y2) < py && py < Math.max(y1, y2));
        }
    }

    // Проверка, находится ли точка на одном из ребер фигуры
    public static boolean isPointOnEdge(Point point, List<Point> points) {
        for (int i = 0; i < points.size() - 1; i++) {
            Point p1 = points.get(i);
            Point p2 = points.get(i + 1);
            if (GeometryUtil.isPointOnSegment(point.getX(), point.getY(), p1.getX(), p1.getY(), p2.getX(), p2.getY())) {
                return true;
            }
        }
        return false;
    }

    public static boolean isPointInsideShape(Point p, List<Point> points) {
        int n = points.size();
        if (n < 3) return false; // Меньше 3 точек не может образовывать фигуру

        double x = p.getX();
        double y = p.getY();
        boolean inside = false;

        for (int i = 0, j = n - 1; i < n; j = i++) {
            double xi = points.get(i).getX();
            double yi = points.get(i).getY();
            double xj = points.get(j).getX();
            double yj = points.get(j).getY();

            // Проверка на пересечение луча, проведенного горизонтально вправо
            boolean intersect = ((yi > y) != (yj > y)) &&
                    (x < (xj - xi) * (y - yi) / (yj - yi) + xi);
            if (intersect) {
                inside = !inside;
            }
        }
        return inside;
    }
}
