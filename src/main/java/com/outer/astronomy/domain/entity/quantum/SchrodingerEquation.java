package com.outer.astronomy.domain.entity.quantum;


import java.util.Arrays;
import java.util.stream.IntStream;

import org.apache.commons.math3.complex.Complex;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import static com.outer.astronomy.domain.utils.Constants.hbar;

//numSteps = 1000; Number of time steps (sans dimension)
//totalTime = 10.0; Total simulation time (s)
//gridSize = 100; Number of spatial grid points (sans dimension)
//dx = 0.1; Spatial grid spacing (m)
//x0 = 0.0; Initial position of the particle (m)
//sigma = 1.0; Width of the Gaussian wave packet (m)

public record SchrodingerEquation(int numSteps, double totalTime, int gridSize, double dx, double x0,
                                       double sigma, int typeOfV, String paramOfV) {
   static Logger logger = LoggerFactory.getLogger(SchrodingerEquation.class);

    public ResultOfSchrodinger simulate() {

        // Calculate the time step
        double timeStep = totalTime / numSteps;

        // Initialize the potential (e.g., finite square well)
        double[] V = getInfinityV();

        // Simulate a quantum particle moving in the gravitational field of a black hole
        // using the Schwarzschild potential.
        // Initialize the potential (Schwarzschild potential for a black hole)
        //blackHoleMass ;  BlackHole Mass (kg)
        if (typeOfV == 2) {
            extractedBlackHole(V);
        }

        // Wave function initialization (e.g., a Gaussian wave packet) (stream method)
        Complex[] waveFunction = IntStream.range(0, gridSize)
                .mapToObj(i -> {
                    double x = i * dx - x0;
                    return Math.abs(x) < 2.0 ? new Complex(Math.exp(-Math.pow(x, 2) / (2 * sigma * sigma)), 0) : new Complex(0, 0);
                })
                .toArray(Complex[]::new);

        // Temporal evolution simulation (stream method)
        IntStream.range(0, numSteps)
                .forEach(step -> {
                    Complex[] newWaveFunction = new Complex[gridSize];
                    IntStream.range(1, gridSize - 1)
                            .forEach(i -> {
                                double laplacian = (waveFunction[i + 1].getReal() - 2 * waveFunction[i].getReal() + waveFunction[i - 1].getReal()) / (dx * dx);
                                Complex newValue = new Complex(0, -1.0 * timeStep / hbar * (laplacian + V[i] * waveFunction[i].getReal()));
                                newWaveFunction[i] = waveFunction[i].add(newValue);
                            });

                    System.arraycopy(newWaveFunction, 1, waveFunction, 1, gridSize - 2); // Copy only the updated part
                });

        double[] realPart = Arrays.stream(waveFunction).mapToDouble(Complex::getReal).toArray();
        double[] imagPart = Arrays.stream(waveFunction).mapToDouble(Complex::getImaginary).toArray();

        // Display the results
        logger.info("Final wave function (real = {}, imaginary = {})" , Arrays.toString(realPart) , Arrays.toString(imagPart));

        return new ResultOfSchrodinger(realPart, imagPart);
    }

    private double[] getInfinityV() {
        double V0 = Double.POSITIVE_INFINITY;
        return IntStream.range(0, gridSize)
                .mapToDouble(i -> Math.abs(i * dx - x0) < 2.0 ? -V0 : 0.0)
                .toArray();
    }

    private void extractedBlackHole(double[] V) {
        double blackHoleMass = Double.parseDouble(paramOfV);
        IntStream.range(0, gridSize)
                .forEach(i -> V[i] = -blackHoleMass / (i * dx + x0));
    }

}
