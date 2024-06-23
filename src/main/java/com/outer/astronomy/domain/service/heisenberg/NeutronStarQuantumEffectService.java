package com.outer.astronomy.domain.service.heisenberg;

import java.util.stream.Collectors;
import java.util.stream.IntStream;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.outer.astronomy.domain.model.quantum.heisenberg.NeutronDensityCalculation;

@Service
public class NeutronStarQuantumEffectService {

    Logger logger = LoggerFactory.getLogger(NeutronStarQuantumEffectService.class);

    public String generateQuantumEffectsData(double minDensity, double maxDensity, int numValues) {
        return IntStream.range(0, numValues)
                .mapToObj(i -> {
                    double density = minDensity * Math.pow(maxDensity / minDensity, (double) i / (numValues - 1));
                    double deltaX = NeutronDensityCalculation.calculatePositionUncertainty(density);
                    double deltaP = NeutronDensityCalculation.calculateMomentumUncertainty(deltaX);
                    String formattedMessage = String.format("Density: %.2e neutrons/m^3\tΔx: %.2e m\tΔp: %.2e kg·m/s\n", density, deltaX, deltaP);
                    logger.info(formattedMessage);
                    return String.format("%.2e,%.2e,%.2e\n", density, deltaX, deltaP);
                })
                .collect(Collectors.joining("", "Density (neutrons/m^3),Δx (m),Δp (kg·m/s)\n", ""));
    }

}

/*
 ├── domain
│   ├── model
│   │   └── NeutronDensityCalculation.java
│   └── service
│       └── NeutronStarQuantumEffectService.java <-- here
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