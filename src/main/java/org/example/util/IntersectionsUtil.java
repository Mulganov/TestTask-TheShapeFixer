package org.example.util;

import org.example.model.Point;
import org.example.model.Shape2D;

import java.util.HashMap;
import java.util.List;

public class IntersectionsUtil {

    // Проверка пересечений
    public static boolean hasIntersections(Shape2D shape2D) {
       return hasIntersections(shape2D.getPoints());
    }

    public static boolean hasIntersections(List<Point> points) {
        HashMap<Point, Integer> map = new HashMap<>();

        int n = points.size();
        if (n < 4) return false; // Фигура должна иметь хотя бы 4 точки для возможного пересечения

        for (int i = 0; i < n; i++) {
            Point p1 = points.get(i);
            Point p2 = points.get((i + 1) % n);

            for (int j = i + 2; j < n; j++) {
                // Пропускаем соседние отрезки и последний и первый отрезки
                if (j == i + 1 || (i == 0 && j == n - 1)) continue;

                Point p3 = points.get(j);
                Point p4 = points.get((j + 1) % n);

                Point intersection = calculateInterceptionPoint(p1, p2, p3, p4);

                if (intersection != null) {
                    map.put(intersection, map.getOrDefault(intersection, 0) + 1);

                    if (map.get(intersection) > 1){
                        return true;
                    }

                    boolean onFirstSegment = GeometryUtil.isPointOnSegment(intersection.getX(), intersection.getY(), p1.getX(), p1.getY(), p2.getX(), p2.getY(), false);
                    boolean onSecondSegment = GeometryUtil.isPointOnSegment(intersection.getX(), intersection.getY(), p3.getX(), p3.getY(), p4.getX(), p4.getY(), false);

                    // Проверяем, что точка пересечения не является концом одного из отрезков
                    if (onFirstSegment || onSecondSegment) {
                        return true;
                    }
                }
            }
        }
        return false;
    }

    // Пример метода для нахождения точки пересечения
    public static Point calculateInterceptionPoint(Point s1, Point s2, Point d1, Point d2) {
        // Преобразуем координаты точек в double для вычислений
        double x1 = s1.getX();
        double y1 = s1.getY();
        double x2 = s2.getX();
        double y2 = s2.getY();
        double x3 = d1.getX();
        double y3 = d1.getY();
        double x4 = d2.getX();
        double y4 = d2.getY();

        double a1 = y2 - y1;
        double b1 = x1 - x2;
        double c1 = a1 * x1 + b1 * y1;

        double a2 = y4 - y3;
        double b2 = x3 - x4;
        double c2 = a2 * x3 + b2 * y3;

        double delta = a1 * b2 - a2 * b1;

        if (Math.abs(delta) < 1e-10) { // проверка на параллельность
            return null;
        }

        double x = (b2 * c1 - b1 * c2) / delta;
        double y = (a1 * c2 - a2 * c1) / delta;

        // Проверяем, что точка пересечения находится на обоих отрезках
        if (GeometryUtil.isPointOnSegment(x, y, x1, y1, x2, y2) &&
                GeometryUtil.isPointOnSegment(x, y, x3, y3, x4, y4)) {
            return new Point((float) x, (float) y);
        }

        return null;
    }
}
