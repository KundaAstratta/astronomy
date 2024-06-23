package com.outer.astronomy.domain.entity.quantum.probability;

import org.junit.jupiter.api.Test;

import com.outer.astronomy.domain.model.quantum.probability.ProbabiltyDensity;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

class ProbabiltyDensityTest {

    @Test
    void shoudReturnProbablityDensity_WhenGiveAGridSIzeAndDX() {

        ProbabiltyDensity waveFunctionService = new ProbabiltyDensity(10, 0.2);

        var waveFunction = waveFunctionService.initializeWaveFunction();
        var probabilityDensity = waveFunctionService.calculateProbabilityDensity(waveFunction);
        assertNotNull(probabilityDensity);
        assertEquals(1,probabilityDensity[0][0][0]);
    }

}