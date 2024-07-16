package com.outer.astronomy.application.heisenberg;

import com.outer.astronomy.domain.service.quantum.heisenberg.NeutronStarQuantumEffectService;
import com.outer.astronomy.infrastructure.output.heisenberg.CsvFileWriter;

public class NeutronStarQuantumEffectApplication {

    private final NeutronStarQuantumEffectService service;
    private final CsvFileWriter fileWriter;

    public NeutronStarQuantumEffectApplication(NeutronStarQuantumEffectService service, CsvFileWriter fileWriter) {
        this.service = service;
        this.fileWriter = fileWriter;
    }

    public String execute(double minDensity, double maxDensity, int numValues, String outputFileName) {
        String data = service.generateQuantumEffectsData(minDensity, maxDensity, numValues);
        fileWriter.writeToFile(outputFileName, data);
        return data;
    }
}

/*
 ├── domain
│   ├── model
│   │   └── NeutronDensityCalculation.java
│   └── service
│       └── NeutronStarQuantumEffectService.java 
│
├── application
│   └── NeutronStarQuantumEffectApplication.java <-- here
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