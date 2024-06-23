package com.outer.astronomy.exposition.heisenberg;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;

import org.springframework.web.bind.annotation.RestController;

import com.outer.astronomy.application.heisenberg.NeutronStarQuantumEffectApplication;
import com.outer.astronomy.infrastructure.config.heisenberg.AppConfig;

@RestController
public class NeutronStarQuantumEffectController {

    private final NeutronStarQuantumEffectApplication application;


    public NeutronStarQuantumEffectController() {
        AppConfig config = new AppConfig();
        this.application = config.neutronStarQuantumEffectApplication();
    } 


    @Operation(summary = "Neutron Star Quantum Effect Controller.", operationId = "NeutronStarQuantumEffectGenerateData",
            description = "Neutron Star Quantum Effect : compute the minimum uncertainty in momentum as \n" + 
                          "a function of neutron density within a neutron star,\n" +
                          " outputting the results to both the screen and a file.\n" +
                    "where " +
                    "minDensity; Minimum neutron density\n" +
                    "maxDensity; Maximum neutron density\n" +
                    "numValues, Number of values to generate\n" +
                    "outputFileName, name of the save file",
            tags = {"quantum-controller"}
    )
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Success", content = @Content(mediaType ="applcation/json")),
            @ApiResponse(responseCode = "400", description = "Functional Error", content = @Content(mediaType ="application/json")),
            @ApiResponse(responseCode = "500", description = "Technical Error", content = @Content(mediaType ="application/json"))
    })   
    @GetMapping("v1/NeutronStarQuantumEffectGenerateData/{minDensity}/{maxDensity}/{numValues}/{outputFileName}")
    public void generateNeutronStarQuantumEffectGenerateData(
            @Parameter(description = "Minimum neutron density (e.g., 1e30)", required = true) @PathVariable String minDensity,
            @Parameter(description = "Maximum neutron density (e.g., 1e35)", required = true) @PathVariable String maxDensity,
            @Parameter(description = "Number of values to generate", required = true, schema = @Schema(type = "integer", example = "1000")) @RequestParam int numValues,
            @Parameter(description = "Output file name", required = true, schema = @Schema(type = "string", example = "quantum_effects_in_neutron_star.csv")) @RequestParam String outputFileName) {

        application.execute(Double.parseDouble(minDensity), Double.parseDouble(maxDensity), numValues, outputFileName);

    }
}


/*
 ├── domain
│   ├── model
│   │   └── NeutronDensityCalculation.java
│   └── service
│       └── NeutronStarQuantumEffectService.java 
│
├── application
│   └── NeutronStarQuantumEffectApplication.java
│
├── infrastructure
│   ├── output
│   │   └── CsvFileWriter.java
│   └── config
│       └── AppConfig.java
│
└── exposition
    └── controller
        └── NeutronStarQuantumEffectController.java <-- here
 */

