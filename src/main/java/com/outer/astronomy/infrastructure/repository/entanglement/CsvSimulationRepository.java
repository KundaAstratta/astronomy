package com.outer.astronomy.infrastructure.repository.entanglement;

import java.io.FileWriter;
import java.io.IOException;

import org.springframework.stereotype.Repository;

import com.outer.astronomy.domain.model.entanglement.PhotonPair;
import com.outer.astronomy.domain.repository.entanglement.SimulationRepository;
import com.outer.astronomy.domain.service.entanglement.SimulationResult;

@Repository
public class CsvSimulationRepository implements SimulationRepository {
    @Override
    public void saveResult(SimulationResult result) {
        try (FileWriter writer = new FileWriter("simulation_results.csv")) {
            writer.write("Angle A,Angle B,Particle A,Particle B\n");
            for (PhotonPair pair : result.getPhotonPairs()) {
                writer.write(String.format("%.2f,%.2f,%d,%d\n",
                        pair.getAngleA(), pair.getAngleB(),
                        pair.getResultA(), pair.getResultB()));
            }
            writer.write(String.format("\nCorrelation,%.4f\n", result.getCorrelation()));
        } catch (IOException e) {
            throw new RuntimeException("Failed to save results to CSV", e);
        }
    }
}