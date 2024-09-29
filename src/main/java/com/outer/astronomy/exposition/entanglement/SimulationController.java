package com.outer.astronomy.exposition.entanglement;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import com.outer.astronomy.application.entanglement.SimulationService;
import com.outer.astronomy.domain.service.entanglement.SimulationResult;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;

@RestController
public class SimulationController {

    private final SimulationService simulationService;

    public SimulationController(SimulationService simulationService) {
        this.simulationService = simulationService;
    }

    @Operation(
        summary = "Quantum Entanglement Analyzer.", 
        operationId = "QuantumEntanglementAnalyze",
        description = "Analyzes the entanglement properties of a quantum system,\n" +
                      " outputting entanglement measures and visualizations.\n" +
                      "where " +
                      "systemState; The quantum state of the system\n" +
                      "entanglementMeasure; The desired entanglement measure (e.g., concurrence, negativity)\n" +
                      "outputFileName; (Optional) The name of the file to save the results",
        tags = {"quantum-controller"}
    )
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Success"),
            @ApiResponse(responseCode = "400", description = "Functional Error", content = @Content(mediaType ="application/json")),
            @ApiResponse(responseCode = "500", description = "Technical Error", content = @Content(mediaType ="application/json"))
    }) 
    @PostMapping("v1/SimulateQuantumEntanglement/{numPairs}")
    public SimulationResult runSimulation(@Parameter(description = "number of pairs") @PathVariable int numPairs) {
        return simulationService.runSimulation(numPairs);
    }
}