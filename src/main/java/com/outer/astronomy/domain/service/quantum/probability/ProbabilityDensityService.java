package com.outer.astronomy.domain.service.quantum.probability;

public interface ProbabilityDensityService {

    void saveProbabilityDensity(double[][][] probabilityDensity, int gridSize, String filename);

    void saveProbabilityDensityZSlice(double[][][] probabilityDensity, int gridSize, int zSlice, String filename);

}
