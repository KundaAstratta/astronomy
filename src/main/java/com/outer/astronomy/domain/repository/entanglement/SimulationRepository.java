package com.outer.astronomy.domain.repository.entanglement;

import com.outer.astronomy.domain.service.entanglement.SimulationResult;

public interface SimulationRepository {
    void saveResult(SimulationResult result);
}
