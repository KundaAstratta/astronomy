package com.outer.astronomy.domain.entity.quantum.probability;

import org.apache.commons.math3.complex.Complex;

import java.util.stream.IntStream;

public record ProbabiltyDensity(int gridSize, double dx) {

    public Complex[][][] initializeWaveFunction() {
        Complex[][][] waveFunction = new Complex[gridSize][gridSize][gridSize];
        IntStream.range(0, gridSize).forEach(i ->
                IntStream.range(0, gridSize).forEach(j ->
                        IntStream.range(0, gridSize).forEach(k -> {
                            double r = dx * Math.sqrt(i * i + j * j + k * k);
                            waveFunction[i][j][k] = new Complex(Math.exp(-r));
                        })
                )
        );
        return waveFunction;
    }

    public double[][][] calculateProbabilityDensity(Complex[][][] waveFunction) {
        double[][][] probabilityDensity = new double[gridSize][gridSize][gridSize];
        IntStream.range(0, gridSize).forEach(i ->
                IntStream.range(0, gridSize).forEach(j ->
                        IntStream.range(0, gridSize).forEach(k ->
                                probabilityDensity[i][j][k] = waveFunction[i][j][k].abs() * waveFunction[i][j][k].abs()
                        )
                )
        );
        return probabilityDensity;
    }
}

