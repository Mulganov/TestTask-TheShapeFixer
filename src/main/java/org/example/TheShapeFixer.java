package org.example;

import org.example.model.Shape2D;
import org.example.util.IsValidUtil;
import org.example.util.RepairUtil;

public class TheShapeFixer {

    public static boolean isValid(Shape2D shape) {
        return IsValidUtil.isValid(shape);
    }

    public static Shape2D repair(Shape2D shape) {
        return RepairUtil.repair(shape);
    }
}
