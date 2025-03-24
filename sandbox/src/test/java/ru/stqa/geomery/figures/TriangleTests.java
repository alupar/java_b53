package ru.stqa.geomery.figures;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class TriangleTests {

    @Test
    void calcPerimeter() {
        var t = new Triangle(1.35, 2.92, 5.1);
        Assertions.assertEquals(9.37, t.perimeter());
    }

    @Test
    void calcArea() {
        var t = new Triangle(20, 19, 30);
        Assertions.assertEquals("186,795", String.format("%.3f", t.area()));
    }
}
