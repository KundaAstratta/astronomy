package com.outer.astronomy.application.quantum;

import com.outer.astronomy.domain.entity.quantum.ResultOfSchrodinger;
import com.outer.astronomy.domain.entity.quantum.SchrodingerEquation;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
public class SchrodingerEquationController {


    @Operation(summary = "Calculate simulation of a quantum particle moving in a one-dimensional potential.", operationId = "calculateQuantumParticleMovingInOneDimensionalPotential.",
            description = "Simulation of a quantum particle moving in a one-dimensional potential." +
                    "where " +
                    "numSteps (= 1000); Number of time steps (dimensionless)\n" +
                    "totalTime (= 10.0); Total simulation time (s)\n" +
                    "gridSize (= 10); Number of spatial grid points (dimensionless)\n" +
                    "dx (= 0.1); Spatial grid spacing (m)\n" +
                    "x0 (= 0.0); Initial position of the particle (m)\n" +
                    "sigma (= 1.0); Width of the Gaussian wave packet (m)",
            tags = {"quantum-controller"}
    )
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Success"),
            @ApiResponse(responseCode = "400", description = "Functional Error", content = @Content(mediaType ="application/json")),
            @ApiResponse(responseCode = "500", description = "Technical Error", content = @Content(mediaType ="application/json"))
    })
    @PostMapping("v1/calculateQuantumParticleMovingInOneDimensionalPotential/{numSteps}/{totalTime}/{gridSize}/{dx}/{x0}/{sigma}/{typeOfV}/{paramOfV}")
    public ResultOfSchrodinger calculateQuantumParticleMovingInOneDimensionalPotential(@Parameter(description = "Number of time steps (dimensionless)") @PathVariable int numSteps,
                                                                                       @Parameter(description = "Total simulation time (s)") @PathVariable double totalTime,
                                                                                       @Parameter(description = "Number of spatial grid points (dimensionless)") @PathVariable int gridSize,
                                                                                       @Parameter(description = "Spatial grid spacing (m)") @PathVariable double dx,
                                                                                       @Parameter(description = "Initial position of the particle (m)") @PathVariable double x0,
                                                                                       @Parameter(description = "Width of the Gaussian wave packet (m)") @PathVariable double sigma,
                                                                                       @Parameter(description = "1: Infinite Potential / 2 black hole\n" +
                                                                                      " using the Schwarzschild potential.", example = "1") @PathVariable int typeOfV,
                                                                                       @Parameter(description = "Parameters of Potential if necessary (separate by commas) / if 2 : blackhole mass",example = "Infinite") @PathVariable String paramOfV) {

        SchrodingerEquation schrodingerSimulation = new SchrodingerEquation(numSteps,totalTime,gridSize,dx,x0,sigma,typeOfV,paramOfV);
        return schrodingerSimulation.simulate();
    }

}
