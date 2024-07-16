package com.outer.astronomy.infrastructure.repository.quantum.dirac;

import java.io.FileWriter;
import java.io.IOException;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class CSVResultSaver {

    static Logger logger = LoggerFactory.getLogger(CSVResultSaver.class);

    public void displayResults(List<Double> energies) {
        System.out.println("Résultats de la simulation :");
        for (int i = 0; i < energies.size(); i++) {
            logger.info("Électron au niveau de Landau " + i + ": Énergie = " + energies.get(i) + " eV");
        }
    }

    public void saveResults(List<Double> energies, String filename) {
        try (FileWriter writer = new FileWriter(filename)) {
            writer.append("Niveau,Énergie (eV)\n");
            for (int i = 0; i < energies.size(); i++) {
                writer.append(i + "," + energies.get(i) + "\n");
            }
            logger.info("Les résultats ont été sauvegardés dans " + filename);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
