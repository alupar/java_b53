package ru.stqa.geomery.figures;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class TriangleTests {

    @Test
    void calcPerimeter() {
        var t = new Triangle(2.35, 2.92, 5.1);
        Assertions.assertEquals(10.37, t.perimeter());
    }

    @Test
    void calcArea() {
        var t = new Triangle(20, 19, 30);
        Assertions.assertEquals("186,795", String.format("%.3f", t.area()));
    }

    @Test
    void cannotCreateTreangleWithNegativeSideA() {
        try {
            new Triangle(-20, 19, 30);
            Assertions.fail();
        } catch (IllegalArgumentException exception) {
        }
    }

    @Test
    void cannotCreateTreangleWithNegativeSideB() {
        try {
            new Triangle(19, -20, 30);
            Assertions.fail();
        } catch (IllegalArgumentException exception) {
        }
    }

    @Test
    void cannotCreateTreangleWithNegativeSideC() {
        try {
            new Triangle(19, 22, -30);
            Assertions.fail();
        } catch (IllegalArgumentException exception) {
        }
    }

    @Test
    void cannotCreateTreangleWithZeroSideA() {
        try {
            new Triangle(0, 21, 30);
            Assertions.fail();
        } catch (IllegalArgumentException exception) {
        }
    }

    @Test
    void cannotCreateTreangleWithZeroSideB() {
        try {
            new Triangle(19, 0, 30);
            Assertions.fail();
        } catch (IllegalArgumentException exception) {
        }
    }

    @Test
    void cannotCreateTreangleWithZeroSideC() {
        try {
            new Triangle(19, 12, 0);
            Assertions.fail();
        } catch (IllegalArgumentException exception) {
        }
    }

    @Test
    void trianglesWithSameSidesAreEqual() {
        var t1 = new Triangle(3, 4, 5);
        var t2 = new Triangle(4, 5, 3);
        Assertions.assertEquals(t1, t2);
    }

    @Test
    void trianglesWithSameSidesAreEqual2() {
        var t1 = new Triangle(3, 4, 5);
        var t2 = new Triangle(4, 5, 3);
        Assertions.assertTrue(t1.equals(t2));
    }

    @Test
    void trianglesWithDifferentSidesAreNotEqual() {
        var t1 = new Triangle(3, 4, 5);
        var t2 = new Triangle(5, 5, 3);
        Assertions.assertNotEquals(t1, t2);
    }
}
