package ru.stqa.geomery.figures;

public record Triangle(double a, double b, double c) {


    public double area() {
        double sp = perimeter() / 2;
        return Math.sqrt(sp * (sp - this.a) * (sp - this.b) * (sp - this.c));
    }


    public double perimeter() {
        return this.a + this.b + this.c;
    }

}
