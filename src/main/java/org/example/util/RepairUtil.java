package org.example.util;

import org.example.model.Point;
import org.example.model.Shape2D;

import java.util.ArrayList;
import java.util.List;

public class RepairUtil {
    public static Shape2D repair(Shape2D shape) {
        ArrayList<Point> points = new ArrayList<>(shape.getPoints());

        System.out.println(points);

        // Шаг 1: Удаляем самопересекающиеся отрезки
        points = removeIntersections(points);

        // Проверяем подконтуры
        if (SubContoursUtil.hasSubContours(points)) {
            // Если подконтуры найдены, удаляем точки, которые их образуют
            for (int i = 0; i < points.size(); i++) {
                Point current = points.get(i);

                // Если точка внутри фигуры, удаляем её
                if (GeometryUtil.isPointInsideShape(current, points)) {
                    points.remove(i);
                    i--; // Чтобы не пропустить следующую точку
                }
            }
        }

        return Shape2DUnit.updatePoints(shape, points);
    }

    private static ArrayList<Point> removeIntersections(ArrayList<Point> points) {
        int n = points.size();

        if (n <= 4)
            return points;

        // Проходим по всем отрезкам
        for (int i = 0; i < n; i++) {
            Point p1 = points.get(i);
            Point p2 = points.get((i + 1) % n);

            for (int j = i + 2; j < n; j++) {
                // Пропускаем соседние отрезки
                if (j == i + 1 || (i == 0 && j == n - 1)) continue;

                Point p3 = points.get(j);
                Point p4 = points.get((j + 1) % n);

                // Если пересечение найдено, удаляем одну из точек
                if (IntersectionsUtil.calculateInterceptionPoint(p1, p2, p3, p4) != null) {
                    points.remove(j); // Удаляем точку, чтобы избавиться от пересечения
                    return removeIntersections(points); // Рекурсивно проверяем пересечения
                }
            }
        }

        return points;
    }
}
