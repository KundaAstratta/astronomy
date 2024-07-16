package com.outer.astronomy.domain.service.quantum.dirac;

import static com.outer.astronomy.domain.utils.Constants.electronCharge;
import static com.outer.astronomy.domain.utils.Constants.electronMass;
import static com.outer.astronomy.domain.utils.Constants.hbar;
import static com.outer.astronomy.domain.utils.Constants.magneticField;

public class LandauEnergyCalculator implements EnergyCalculator {

    @Override
    public double calculateLandauLevelEnergy(int n) {
        
        double energy = (n + 0.5) * (electronCharge * magneticField * hbar / electronMass);
        return energy / electronCharge;
    }

}