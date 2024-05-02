package com.outer.astronomy.domain.entity.relativity;

import static com.outer.astronomy.domain.utils.Constants.gravitationalConstant;

public record EinsteinTensor() {
    public double[][] calculateEinsteinTensor(double[][] metric, double[][][][] riemann) {
        int dim = metric.length;
        double[][] einsteinTensor = new double[dim][dim];

        // Iterate over indices i and j to compute the Einstein tensor
        for (int i = 0; i < dim; i++) {
            for (int j = 0; j < dim; j++) {
                double riemannScalar = calculateRiemannScalar(riemann[i][j]);
                double ricci = calculateRicciScalar(riemann, i, j);

                // Calculer le tenseur d'Einstein pour le couple d'indices (i, j)
                einsteinTensor[i][j] = (ricci - 0.5 * riemannScalar) * metric[i][j]
                        - 8 * Math.PI * gravitationalConstant  * metric[i][j] * metric[i][j];
            }
        }

        return einsteinTensor;
    }

    // Method for calculating the Riemann scalar for a given pair of indices (i, j)
    private static double calculateRiemannScalar(double[][] riemannPair) {
        double scalar = 0;
        for (double[] tensorComponent : riemannPair) {
            for (double component : tensorComponent) {
                scalar += component;
            }
        }
        return scalar;
    }

    // Method for calculating the Ricci scalar for a given pair of indices (i, j)
    private static double calculateRicciScalar(double[][][][] riemann, int i, int j) {
        double ricci = 0;
        for (int k = 0; k < riemann.length; k++) {
            ricci += riemann[i][k][j][k];
        }
        return ricci;
    }
}
