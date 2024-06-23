package com.outer.astronomy.infrastructure.config.heisenberg;

import com.outer.astronomy.application.heisenberg.NeutronStarQuantumEffectApplication;
import com.outer.astronomy.domain.service.heisenberg.NeutronStarQuantumEffectService;
import com.outer.astronomy.infrastructure.output.heisenberg.CsvFileWriter;

public class AppConfig {

    public NeutronStarQuantumEffectApplication neutronStarQuantumEffectApplication() {
        NeutronStarQuantumEffectService service = new NeutronStarQuantumEffectService();
        CsvFileWriter fileWriter = new CsvFileWriter();
        return new NeutronStarQuantumEffectApplication(service, fileWriter);
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
│   └── NeutronStarQuantumEffectApplication.java 
│
├── infrastructure
│   ├── output
│   │   └── CsvFileWriter.java
│   └── config
│       └── AppConfig.java <-- here
│
└── exposition
    └── controller
        └── NeutronStarQuantumEffectController.java
 */
