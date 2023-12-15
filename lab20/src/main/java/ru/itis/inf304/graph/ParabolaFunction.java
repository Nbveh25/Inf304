package ru.itis.inf304.graph;

public class ParabolaFunction implements IFunction{

    @Override
    public double calculate(double x) {
        return -0.01*(x-400)*(x-400) + 300; // y = x^2 (0,0) == (400, 300)
    }
}
