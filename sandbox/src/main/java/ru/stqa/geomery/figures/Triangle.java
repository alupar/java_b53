package ru.stqa.geomery.figures;

public record Triangle(double a, double b, double c) {

    public Triangle {
        if (a <= 0 || b <= 0 || c <= 0) {
            throw new IllegalArgumentException("Длина стороны треугольника должна быть положительной");
        }
        if (a + b <= c || a + c <= b || b + c <= a) {
            throw new IllegalArgumentException("Нарушено неравенство треугольника (сумма двух любых сторон должна быть не меньше третьей стороны)");
        }
    }

    public double area() {
        double sp = perimeter() / 2;
        return Math.sqrt(sp * (sp - this.a) * (sp - this.b) * (sp - this.c));
    }


    public double perimeter() {
        return this.a + this.b + this.c;
    }

}
