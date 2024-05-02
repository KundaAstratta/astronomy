package com.outer.astronomy.domain.entity.blackhole;

import static com.outer.astronomy.domain.utils.Constants.SolarMass;

public record Chandrasekhar(double[] fractions, int[] atomicNumbers, double[] atomicMasses) {


    // Calculating the Average Electron Mass
    public double calculateMuE() {
        double mu_atom = calculateAverageAtomicMass();
        double mu_e = mu_atom / calculateElectronNumber();
        return mu_e;
    }

    // Calculating the Average Atomic Mass
    private double calculateAverageAtomicMass() {
        double numerator = 0;
        double denominator = 0;
        for (int i = 0; i < fractions.length; i++) {
            numerator += fractions[i] * atomicNumbers[i] * atomicMasses[i];
            denominator += fractions[i] * atomicNumbers[i];
        }
        return numerator / denominator;
    }

    // Calculating the Total Number of Electrons
    private double calculateElectronNumber() {
        double electronNumber = 0;
        for (int i = 0; i < fractions.length; i++) {
            electronNumber += fractions[i] * atomicNumbers[i];
        }
        return electronNumber;
    }

    // Calculating the Chandrasekhar Limit
    public double calculateChandrasekharLimit() {
        double mu_e = calculateMuE();
        double M_lim = (1.44 * SolarMass) / Math.pow(mu_e / 2.0, 2);
        return M_lim;
    }
}

