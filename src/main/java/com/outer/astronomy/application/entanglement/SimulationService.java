package com.outer.astronomy.application.entanglement;

import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import org.springframework.stereotype.Service;

import com.outer.astronomy.domain.model.entanglement.PhotonPair;
import com.outer.astronomy.domain.repository.entanglement.SimulationRepository;
import com.outer.astronomy.domain.service.entanglement.SimulationResult;

@Service
public class SimulationService {
    private final SimulationRepository repository;

    public SimulationService(SimulationRepository repository) {
        this.repository = repository;
    }

    public SimulationResult runSimulation(int numPairs) {
        Random rand = new Random();

        List<PhotonPair> photonPairs = IntStream.range(0, numPairs)
        .mapToObj(i -> {
            double angleA = rand.nextDouble() * Math.PI;
            double angleB = rand.nextDouble() * Math.PI;
            int resultA = measurePhoton(angleA);
            int resultB = measurePhoton(angleB);
            return new PhotonPair(angleA, angleB, resultA, resultB);
        })
        .collect(Collectors.toList());

        double correlation = calculateCorrelation(photonPairs);
        SimulationResult result = new SimulationResult(photonPairs, correlation);

        repository.saveResult(result);

        return result;
    }

    private int measurePhoton(double angle) {
        Random rand = new Random();
        return (rand.nextDouble() < Math.cos(angle) * Math.cos(angle)) ? 1 : 0;
    }

    private double calculateCorrelation(List<PhotonPair> pairs) {
        int sum = 0;

        pairs.stream()
            .filter(pair -> pair.getResultA() == pair.getResultB())
            .count();

        return (double) sum / pairs.size();
    }
}
