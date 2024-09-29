package com.outer.astronomy.domain.service.entanglement;

import java.util.List;

import com.outer.astronomy.domain.model.entanglement.PhotonPair;

public class SimulationResult {
    private final List<PhotonPair> photonPairs;

    private final double correlation;

    public SimulationResult(List<PhotonPair> photonPairs, double correlation) {
        this.photonPairs = photonPairs;
        this.correlation = correlation;
    }

    // Getters
    public double getCorrelation() {
        return correlation;
    }

    public List<PhotonPair> getPhotonPairs() {
        return photonPairs;
    }
}