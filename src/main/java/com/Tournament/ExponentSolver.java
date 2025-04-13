package com.Tournament;

public class ExponentSolver {

    public static int solve(int base, int result) {
        return (int) (Math.log(result) / Math.log(base));
    }
}
