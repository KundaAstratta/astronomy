package com.outer.astronomy.application.quantum;

import com.outer.astronomy.domain.model.quantum.probability.ProbabiltyDensity;
import com.outer.astronomy.domain.model.quantum.schrodinger.SchrodingerEquation;
import com.outer.astronomy.domain.service.quantum.ProbabilityDensityService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
public class ProbabilityDensityController {

    @Autowired
    ProbabilityDensityService probabilityDensityService;

    static Logger logger = LoggerFactory.getLogger(SchrodingerEquation.class);

    @Operation(summary = "Three-dimensional electron probability density in the ground state of hydrogen.", operationId = "hydrogenElectronProbabilityDensity.",
            description = "3D probability density of an electron in the ground state of a hydrogen atom." +
                    "where " +
                    "gridSize (= 10O); Number of spatial grid points (dimensionless)\n" +
                    "dx (= 0.2); Spatial grid spacing (m)\n" +
                    "zSlice, position of layer\n" +
                    "finame, name of the save files",
            tags = {"quantum-controller"}
    )
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Success"),
            @ApiResponse(responseCode = "400", description = "Functional Error", content = @Content(mediaType ="application/json")),
            @ApiResponse(responseCode = "500", description = "Technical Error", content = @Content(mediaType ="application/json"))
    })
    @PostMapping("v1/hydrogenElectronProbabilityDensity/{gridSize}/{dx}/{zSlice}/{filename}")
    public void hydrogenElectronProbabilityDensity(@Parameter(description = "Number of spatial grid points (dimensionless)") @PathVariable int gridSize,
                                                   @Parameter(description = "Spatial grid spacing (m)") @PathVariable double dx,
                                                   @Parameter(description = "zSlice , position of layer (dimensionless") @PathVariable int zSlice,
                                                   @Parameter(description ="filename, name of the files") @PathVariable String filename) {


        ProbabiltyDensity waveFunctionService = new ProbabiltyDensity(gridSize, dx);

        var waveFunction = waveFunctionService.initializeWaveFunction();
        var probabilityDensity = waveFunctionService.calculateProbabilityDensity(waveFunction);

        probabilityDensityService.saveProbabilityDensity(probabilityDensity, gridSize, filename+".csv");
        probabilityDensityService.saveProbabilityDensityZSlice(probabilityDensity,gridSize,zSlice,filename+zSlice+".csv");

    }

}


