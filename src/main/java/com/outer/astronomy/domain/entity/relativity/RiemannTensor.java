package com.outer.astronomy.domain.entity.relativity;

public record RiemannTensor() {

    public double[][][][] calculateRiemannTensor(double[][] metric) {
        int dim = metric.length;
        double[][][][] riemann = new double[dim][dim][dim][dim];

        for (int i = 0; i < dim; i++) {
            for (int j = 0; j < dim; j++) {
                for (int k = 0; k < dim; k++) {
                    for (int l = 0; l < dim; l++) {
                        riemann[i][j][k][l] = 0;
                        for (int m = 0; m < dim; m++) {
                            riemann[i][j][k][l] +=
                                    metric[i][m] * (
                                            metric[j][k] * ChristoffelSymbol(metric,m, k, l) -
                                                    metric[k][l] * ChristoffelSymbol(metric,m, j, l) +
                                                    metric[j][l] * ChristoffelSymbol(metric,m, k, i) -
                                                    metric[k][i] * ChristoffelSymbol(metric,m, j, i)
                                    );
                        }
                    }
                }
            }
        }

        return riemann;
    }

    private static double ChristoffelSymbol(double[][] metric, int i, int j, int k) {
        double sum = 0;
        int dim = metric.length;
        for (int l = 0; l < dim; l++) {
            sum += 0.5 * (
                    gInverse(metric, i, l) * (partialDerivative(metric, l, j, k) + partialDerivative(metric, l, k, j) - partialDerivative(metric, j, k, l)) +
                    gInverse(metric, j, l) * (partialDerivative(metric, i, k, l) + partialDerivative(metric, k, i, l) - partialDerivative(metric, k, l, i)) -
                    gInverse(metric, k, l) * (partialDerivative(metric, i, j, l) + partialDerivative(metric, j, i, l) - partialDerivative(metric, i, l, j))
            );
        }
        return sum;
    }

    private static double gInverse(double[][] metric, int i, int j) {
        return inverseMatrix(metric)[i][j];
    }

    private static double[][] inverseMatrix(double[][] matrix) {
        int n = matrix.length;
        double[][] inverse = new double[n][n];
        double determinant = determinant(matrix);
        if (determinant == 0) {
            throw new ArithmeticException("Matrix is not invertible.");
        }
        double[][] adjoint = adjoint(matrix);
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                inverse[i][j] = adjoint[i][j] / determinant;
            }
        }
        return inverse;
    }

    private static double[][] adjoint(double[][] matrix) {
        int n = matrix.length;
        double[][] adjoint = new double[n][n];
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                double[][] minor = minor(matrix, i, j);
                double sign = ((i + j) % 2 == 0) ? 1 : -1;
                adjoint[j][i] = sign * determinant(minor);
            }
        }
        return adjoint;
    }

    private static double determinant(double[][] matrix) {
        int n = matrix.length;
        if (n == 1) {
            return matrix[0][0];
        }
        if (n == 2) {
            return matrix[0][0] * matrix[1][1] - matrix[0][1] * matrix[1][0];
        }
        double det = 0;
        for (int j = 0; j < n; j++) {
            double[][] minor = minor(matrix, 0, j);
            double sign = (j % 2 == 0) ? 1 : -1;
            det += sign * matrix[0][j] * determinant(minor);
        }
        return det;
    }

    private static double[][] minor(double[][] matrix, int row, int col) {
        int n = matrix.length;
        double[][] minor = new double[n - 1][n - 1];
        for (int i = 0, p = 0; i < n; i++) {
            if (i == row) {
                continue;
            }
            for (int j = 0, q = 0; j < n; j++) {
                if (j == col) {
                    continue;
                }
                minor[p][q] = matrix[i][j];
                q++;
            }
            p++;
        }
        return minor;
    }

    private static double partialDerivative(double[][] metric, int i, int j, int k) {
        double dx = 1E-6;
        int dim = metric.length;
        if (j < dim - 1 && k < dim - 1) {
            if (k == 0) {
                //Calculate the partial derivative with respect to the first dimension (index j)
                return (metric[i][j + 1] - metric[i][j]) / dx;
            } else if (k == 1) {
                //Calculate the partial derivative with respect to the second dimension (index i)
                return (metric[j + 1][i] - metric[j][i]) / dx;
            }
        }
        // Handle the case where j or k is at the upper bound of the array
        // You can return 0 or another value depending on the context of your application.
        return 0.0; // For example, return 0 in this case.
    }

}
