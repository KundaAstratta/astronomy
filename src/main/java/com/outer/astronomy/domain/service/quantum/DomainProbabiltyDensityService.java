package com.outer.astronomy.domain.service.quantum;

import com.outer.astronomy.domain.repository.quantum.ProbabilityDensityRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class DomainProbabiltyDensityService implements ProbabilityDensityService {

    @Autowired
    ProbabilityDensityRepository probabilityDensityRepository;

    @Override
    public void saveProbabilityDensity(double[][][] probabilityDensity, int gridSize, String filename) {
        probabilityDensityRepository.saveProbabilityDensity(probabilityDensity, gridSize, filename);
    }

    @Override
    public     void saveProbabilityDensityZSlice(double[][][] probabilityDensity, int gridSize, int zSlice, String filename) {
        probabilityDensityRepository.saveProbabilityDensityZSlice(probabilityDensity,gridSize,zSlice,filename);
    }


}
