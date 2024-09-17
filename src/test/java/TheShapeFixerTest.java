import org.example.model.Point;
import org.example.model.Shape2D;
import org.example.TheShapeFixer;
import org.junit.jupiter.api.Test;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;
import static org.junit.jupiter.api.Assertions.*;

public class TheShapeFixerTest {

    @Test
    public void test1() {
        Shape2D square = new Shape2D(new ArrayList<>(List.of(
                new Point(0, 0), new Point(2, 0),
                new Point(4, 0), new Point(4, 2),
                new Point(2, 2), new Point(2, 1),
                new Point(2, 4), new Point(0, 4),
                new Point(0, 0))));
        assertFalse(TheShapeFixer.isValid(square));
        Shape2D repairedShape = TheShapeFixer.repair(square);

        System.out.println("repairedShape: " + repairedShape);

        assertTrue(TheShapeFixer.isValid(repairedShape));
    }

    @Test
    public void testIsValid_ValidSquare() {
        Shape2D square = new Shape2D(new ArrayList<>(List.of(
                new Point(0, 0), new Point(1, 0),
                new Point(1, 1), new Point(0, 1), new Point(0, 0))));
        assertTrue(TheShapeFixer.isValid(square));
    }

    @Test
    public void testIsValid_ValidTriangle() {
        Shape2D triangle = new Shape2D(new ArrayList<>(List.of(
                new Point(0, 0), new Point(2, 0),
                new Point(1, 2), new Point(0, 0))));
        assertTrue(TheShapeFixer.isValid(triangle));
    }

    @Test
    public void testIsValid_ValidNonConvex() {
        Shape2D nonConvex = new Shape2D(new ArrayList<>(List.of(
                new Point(0, 0), new Point(3, 0),
                new Point(2, 1), new Point(1, 1),
                new Point(0, 3), new Point(0, 0))));
        assertTrue(TheShapeFixer.isValid(nonConvex));
    }

    @Test
    public void testIsValid_InvalidShapeWithSubContour() {
        Shape2D invalidShape = new Shape2D(new ArrayList<>(List.of(
                new Point(0, 0), new Point(3, 0), new Point(3, 3),
                new Point(0, 3), new Point(1, 1), new Point(2, 1), new Point(0, 0))));
        assertFalse(TheShapeFixer.isValid(invalidShape));
    }

    @Test
    public void testIsValid_SelfIntersectingShape() {
        Shape2D selfIntersecting = new Shape2D(new ArrayList<>(List.of(
                new Point(0, 0), new Point(3, 0),
                new Point(3, 3), new Point(1, 1), new Point(0, 3), new Point(0, 0))));
        assertFalse(TheShapeFixer.isValid(selfIntersecting));
    }

    @Test
    public void testRepair_InvalidShapeWithMultipleContours() {
        Shape2D invalidShapeWithMultipleContours = new Shape2D(new ArrayList<>(List.of(
                new Point(0, 0), new Point(2, 0), new Point(2, 2),
                new Point(0, 2), new Point(1, 1), new Point(3, 1), new Point(0, 0))));

        assertFalse(TheShapeFixer.isValid(invalidShapeWithMultipleContours));
    }

    @Test
    public void testRepair_InvalidShapeWithDiagonalOverlap() {
        Shape2D invalidShapeWithDiagonalOverlap = new Shape2D(new ArrayList<>(List.of(
                new Point(0, 0), new Point(4, 0), new Point(2, 2),
                new Point(4, 4), new Point(0, 4), new Point(2, 2), new Point(0, 0))));

        assertFalse(TheShapeFixer.isValid(invalidShapeWithDiagonalOverlap));
    }

    @Test
    public void testRepair_InvalidShapeWithSubContour() {
        Shape2D invalidShape = new Shape2D(new ArrayList<>(List.of(
                new Point(0, 0), new Point(3, 0), new Point(3, 3),
                new Point(0, 3), new Point(1, 1), new Point(2, 1), new Point(0, 0))));

        assertFalse(TheShapeFixer.isValid(invalidShape));

        Shape2D repairedShape = TheShapeFixer.repair(invalidShape);

        assertTrue(TheShapeFixer.isValid(repairedShape));
    }

    @Test
    public void testRepair_SelfIntersectingShape() {
        Shape2D selfIntersecting = new Shape2D(new ArrayList<>(List.of(
                new Point(0, 0), new Point(3, 0),
                new Point(3, 3), new Point(1, 1), new Point(0, 3), new Point(0, 0))));

        assertFalse(TheShapeFixer.isValid(selfIntersecting));

        Shape2D repairedShape = TheShapeFixer.repair(selfIntersecting);
        assertTrue(TheShapeFixer.isValid(repairedShape));
    }

    @Test
    public void testRepair_InvalidShapeWithHole() {
        Shape2D invalidShapeWithHole = new Shape2D(new ArrayList<>(List.of(
                new Point(0, 0), new Point(4, 0), new Point(4, 4),
                new Point(0, 4), new Point(2, 2), new Point(0, 0))));

        assertFalse(TheShapeFixer.isValid(invalidShapeWithHole));

        Shape2D repairedShape = TheShapeFixer.repair(invalidShapeWithHole);
        assertTrue(TheShapeFixer.isValid(repairedShape));
    }

    @Test
    public void testRepair_InvalidShapeWithMultipleContours1() {
        Shape2D invalidShapeWithMultipleContours = new Shape2D(new ArrayList<>(List.of(
                new Point(0, 0), new Point(2, 0), new Point(2, 2),
                new Point(0, 2), new Point(1, 1), new Point(3, 1), new Point(0, 0))));

        assertFalse(TheShapeFixer.isValid(invalidShapeWithMultipleContours));

        Shape2D repairedShape = TheShapeFixer.repair(invalidShapeWithMultipleContours);
        assertTrue(TheShapeFixer.isValid(repairedShape));
    }

//    @Test
//    public void testRepair_InvalidShapeWithDiagonalOverlap11() {
//        Shape2D invalidShapeWithDiagonalOverlap = new Shape2D(new ArrayList<>(List.of(
//                new Point(0, 0), new Point(4, 0), new Point(2, 2),
//                new Point(4, 4), new Point(0, 4), new Point(2, 2), new Point(0, 0))));
//
//        assertFalse(TheShapeFixer.isValid(invalidShapeWithDiagonalOverlap));
//
//        Shape2D repairedShape = TheShapeFixer.repair(invalidShapeWithDiagonalOverlap);
//        System.out.println("repairedShape: " + repairedShape);
//        assertTrue(TheShapeFixer.isValid(repairedShape));
//    }

    @Test
    public void testRepair_InvalidShapeWithHole3() {
        Shape2D invalidShapeWithHole = new Shape2D(new ArrayList<>(List.of(
                new Point(0, 0), new Point(4, 0), new Point(4, 4),
                new Point(0, 4), new Point(2, 2), new Point(0, 0))));

        assertFalse(TheShapeFixer.isValid(invalidShapeWithHole));

        Shape2D repairedShape = TheShapeFixer.repair(invalidShapeWithHole);
        assertTrue(TheShapeFixer.isValid(repairedShape));
    }

//    @Test
//    public void testRepair_InvalidSelfIntersectingShape() {
//        Shape2D selfIntersectingShape = new Shape2D(new ArrayList<>(List.of(
//                new Point(0, 0), new Point(4, 0), new Point(2, 2),
//                new Point(4, 4), new Point(0, 4), new Point(2, 2), new Point(0, 0))));
//
//        assertFalse(TheShapeFixer.isValid(selfIntersectingShape));
//
//        Shape2D repairedShape = TheShapeFixer.repair(selfIntersectingShape);
//        System.out.println("repairedShape: " + repairedShape);
//        assertTrue(TheShapeFixer.isValid(repairedShape));
//    }

    @Test
    public void testRepair_InvalidShapeWithExtraVertex() {
        Shape2D shapeWithExtraVertex = new Shape2D(new ArrayList<>(List.of(
                new Point(0, 0), new Point(3, 0), new Point(3, 3),
                new Point(0, 3), new Point(1, 1), new Point(0, 0))));

        assertFalse(TheShapeFixer.isValid(shapeWithExtraVertex));

        Shape2D repairedShape = TheShapeFixer.repair(shapeWithExtraVertex);
        assertTrue(TheShapeFixer.isValid(repairedShape));
    }

    @Test
    public void testRepair_InvalidShapeWithMultipleContours2() {
        Shape2D invalidShapeWithMultipleContours = new Shape2D(new ArrayList<>(List.of(
                new Point(0, 0), new Point(2, 0), new Point(2, 2),
                new Point(0, 2), new Point(1, 1), new Point(3, 1), new Point(0, 0))));

        assertFalse(TheShapeFixer.isValid(invalidShapeWithMultipleContours));

        Shape2D repairedShape = TheShapeFixer.repair(invalidShapeWithMultipleContours);
        assertTrue(TheShapeFixer.isValid(repairedShape));
    }

    @Test
    public void testRepair_InvalidShapeWithSmallSelfIntersection() {
        Shape2D shapeWithSmallSelfIntersection = new Shape2D(new ArrayList<>(List.of(
                new Point(0, 0), new Point(2, 0), new Point(2, 2),
                new Point(1, 1), new Point(0, 2), new Point(0, 0))));

        assertFalse(TheShapeFixer.isValid(shapeWithSmallSelfIntersection));

        Shape2D repairedShape = TheShapeFixer.repair(shapeWithSmallSelfIntersection);
        assertTrue(TheShapeFixer.isValid(repairedShape));
    }

    @Test
    public void testRepair_InvalidShapeWithOverlappingEdges() {
        Shape2D shapeWithOverlappingEdges = new Shape2D(new ArrayList<>(List.of(
                new Point(0, 0), new Point(4, 0), new Point(4, 4),
                new Point(0, 4), new Point(2, 2), new Point(2, 2), new Point(0, 0))));

        assertFalse(TheShapeFixer.isValid(shapeWithOverlappingEdges));

        Shape2D repairedShape = TheShapeFixer.repair(shapeWithOverlappingEdges);
        assertTrue(TheShapeFixer.isValid(repairedShape));
    }

    @Test
    public void testRepair_InvalidShapeWithNonConvexOverlap() {
        Shape2D shapeWithNonConvexOverlap = new Shape2D(new ArrayList<>(List.of(
                new Point(0, 0), new Point(3, 0), new Point(3, 3),
                new Point(0, 3), new Point(1, 1), new Point(0, 0))));

        assertFalse(TheShapeFixer.isValid(shapeWithNonConvexOverlap));

        Shape2D repairedShape = TheShapeFixer.repair(shapeWithNonConvexOverlap);
        assertTrue(TheShapeFixer.isValid(repairedShape));
    }

//    @Test
//    public void testRepair_InvalidShapeWithDiagonalOverlap1() {
//        Shape2D shapeWithDiagonalOverlap = new Shape2D(new ArrayList<>(List.of(
//                new Point(0, 0), new Point(4, 0), new Point(2, 2),
//                new Point(4, 4), new Point(0, 4), new Point(2, 2),
//                new Point(0, 0))));
//
//        assertFalse(TheShapeFixer.isValid(shapeWithDiagonalOverlap));
//
//        Shape2D repairedShape = TheShapeFixer.repair(shapeWithDiagonalOverlap);
//
//        System.out.println("repairedShape: " + repairedShape);
//
//        assertTrue(TheShapeFixer.isValid(repairedShape));
//    }

    @Test
    public void testRepair_InvalidShapeWithSelfIntersectionAndHole() {
        Shape2D shapeWithSelfIntersectionAndHole = new Shape2D(new ArrayList<>(List.of(
                new Point(0, 0), new Point(4, 0), new Point(4, 4),
                new Point(2, 2), new Point(0, 4), new Point(0, 0))));

        assertTrue(TheShapeFixer.isValid(shapeWithSelfIntersectionAndHole));
    }

    @Test
    public void testIsValid_ValidRectangle_1() {
        Shape2D rectangle = new Shape2D(new ArrayList<>(List.of(
                new Point(0, 0), new Point(4, 0),
                new Point(4, 2), new Point(0, 2), new Point(0, 0))));
        assertTrue(TheShapeFixer.isValid(rectangle));
    }

    @Test
    public void testRepair_InvalidRectangleWithExtraPoint_2() {
        Shape2D invalidRectangle = new Shape2D(new ArrayList<>(List.of(
                new Point(0, 0), new Point(4, 0),
                new Point(4, 2), new Point(2, 1), new Point(0, 2), new Point(0, 0))));

        assertFalse(TheShapeFixer.isValid(invalidRectangle));

        Shape2D repairedRectangle = TheShapeFixer.repair(invalidRectangle);
        assertTrue(TheShapeFixer.isValid(repairedRectangle));
    }

    @Test
    public void testIsValid_ValidTriangle_3() {
        Shape2D triangle = new Shape2D(new ArrayList<>(List.of(
                new Point(0, 0), new Point(3, 0),
                new Point(1, 4), new Point(0, 0))));
        assertTrue(TheShapeFixer.isValid(triangle));
    }

    @Test
    public void testRepair_SelfIntersectingTriangle_4() {
        Shape2D invalidTriangle = new Shape2D(new ArrayList<>(List.of(
                new Point(0, 0), new Point(3, 0),
                new Point(1, 2), new Point(2, 1), new Point(0, 0))));

        assertFalse(TheShapeFixer.isValid(invalidTriangle));

        Shape2D repairedTriangle = TheShapeFixer.repair(invalidTriangle);
        assertTrue(TheShapeFixer.isValid(repairedTriangle));
    }

    @Test
    public void testIsValid_ValidPentagon_5() {
        Shape2D pentagon = new Shape2D(new ArrayList<>(List.of(
                new Point(0, 0), new Point(2, 0),
                new Point(3, 2), new Point(1, 4),
                new Point(0, 2), new Point(0, 0))));
        assertTrue(TheShapeFixer.isValid(pentagon));
    }

    @Test
    public void testRepair_InvalidPentagonWithSelfIntersection_6() {
        Shape2D invalidPentagon = new Shape2D(new ArrayList<>(List.of(
                new Point(0, 0), new Point(3, 0),
                new Point(3, 3), new Point(1, 1),
                new Point(0, 3), new Point(0, 0))));

        assertFalse(TheShapeFixer.isValid(invalidPentagon));

        Shape2D repairedPentagon = TheShapeFixer.repair(invalidPentagon);
        assertTrue(TheShapeFixer.isValid(repairedPentagon));
    }

    @Test
    public void testIsValid_ValidNonConvexShape_7() {
        Shape2D nonConvexShape = new Shape2D(new ArrayList<>(List.of(
                new Point(0, 0), new Point(4, 0),
                new Point(4, 2), new Point(2, 3),
                new Point(0, 2), new Point(0, 0))));
        assertTrue(TheShapeFixer.isValid(nonConvexShape));
    }
}
