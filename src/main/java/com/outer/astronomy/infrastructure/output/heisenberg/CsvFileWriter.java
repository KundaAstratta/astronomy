package com.outer.astronomy.infrastructure.output.heisenberg;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;

import org.springframework.stereotype.Component;

@Component
public class CsvFileWriter {

    public void writeToFile(String fileName, String data) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(fileName))) {
            writer.write(data);
        } catch (IOException e) {
            throw new RuntimeException("Erreur lors de l'écriture dans le fichier : " + e.getMessage(), e);
        }
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
│   │   └── CsvFileWriter.java <-- here
│   └── config
│       └── AppConfig.java
│
└── exposition
    └── controller
        └── NeutronStarQuantumEffectController.java
 */