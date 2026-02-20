package ru.itmo;

public class SeriesExpansion {
    private double factorial(int n){
        if(n<0){
            throw new IllegalArgumentException("n must be non-negative");
        }
        return n == 0 ? 1 : n * factorial(n - 1);
    }

    private boolean checkHit(double x){
        return !Double.isNaN(x) && !Double.isInfinite(x) && (x >= -1.0 && x <= 1.0);
    }

    private double calculate(double x, int n){
        return factorial(2*n) * Math.pow(x, 2*n + 1)/(Math.pow(4, n) * Math.pow(factorial(n), 2) * (2*n + 1));
    }

    public double expand(double x, int n){
        if(!checkHit(x)){
            throw new IllegalArgumentException("x must be in the range [-1, 1]");
        }
        if(n<0){
            throw new IllegalArgumentException("n must be non-negative");
        }
        double sum = Math.PI/2;
        for(int i = 0; i <= n; i++) {
            sum -= calculate(x, i);
        }
        return sum;
    }




}
