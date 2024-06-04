package com.outer.astronomy.domain.repository.quantum;


public interface ProbabilityDensityRepository {

      void saveProbabilityDensity(double[][][] probabilityDensity, int gridSize, String filename);

      void saveProbabilityDensityZSlice(double[][][] probabilityDensity, int gridSize, int zSlice, String filename);

}
