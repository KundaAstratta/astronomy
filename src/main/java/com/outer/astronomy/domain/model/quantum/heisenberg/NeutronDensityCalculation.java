package com.outer.astronomy.domain.model.quantum.heisenberg;

import static com.outer.astronomy.domain.utils.Constants.hbar;

public class NeutronDensityCalculation {

    public static double calculatePositionUncertainty(double density) {
        return Math.pow(density, -1.0 / 3.0);
    }

    public static double calculateMomentumUncertainty(double deltaX) {
        return hbar / (2 * deltaX);
    }
}

/* 
├── domain
│   ├── model
│   │   └── NeutronDensityCalculation.java   <-- here
│   └── service
│       └── NeutronStarQuantumEffectService.java
│
├── application
│   └── NeutronStarQuantumEffectApplication.java
│
├── infrastructure
│   ├── output
│   │   └── CsvFileWriter.java
│   └── config
│       └── AppConfig.java
│
└── exposition
    └── controller
        └── NeutronStarQuantumEffectController.java
*/