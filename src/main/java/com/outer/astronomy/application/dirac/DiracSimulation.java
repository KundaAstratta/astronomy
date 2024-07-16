package com.outer.astronomy.application.dirac;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import com.outer.astronomy.domain.model.quantum.dirac.Electron;
import com.outer.astronomy.domain.service.quantum.dirac.EnergyCalculator;

public class DiracSimulation {

    private final EnergyCalculator energyCalculator;
    private final List<Electron> electrons;
    private List<Double> energies;

    public DiracSimulation(EnergyCalculator energyCalculator, int numElectrons) {

        this.energyCalculator = energyCalculator;
        this.energies = new ArrayList<>();
        this.electrons = IntStream.range(0, numElectrons)
                                 .mapToObj(Electron::new)
                                 .collect(Collectors.toList());
        
    }

    public void run() {

        energies = electrons.stream()
        .map(electron -> energyCalculator.calculateLandauLevelEnergy(electron.getLevel()))
        .collect(Collectors.toList());

    }

    public List<Double> getEnergies() {

        return energies;
        
    }
    
}