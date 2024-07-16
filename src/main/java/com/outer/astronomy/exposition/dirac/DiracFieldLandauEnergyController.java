package com.outer.astronomy.exposition.dirac;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;

import org.springframework.web.bind.annotation.RestController;

import com.outer.astronomy.application.dirac.DiracSimulation;
import com.outer.astronomy.domain.service.quantum.dirac.LandauEnergyCalculator;
import com.outer.astronomy.infrastructure.repository.quantum.dirac.CSVResultSaver;


@RestController
public class DiracFieldLandauEnergyController {

    static Logger logger = LoggerFactory.getLogger(DiracFieldLandauEnergyController.class);

    @Operation(summary = "Dirac field and Landau energy Controller.", operationId = "DiracFieldAndLandauEnergy",
            description = "Dirac field and Landau energy, by examining the quantized energy \n" +
                    "levels of electrons in a strong magnetic field\n" +
                    "where \n"  +
                    "numElectrons nombre d'électron\n",
            tags = {"quantum-controller"}
    )
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Success", content = @Content(mediaType ="applcation/json")),
            @ApiResponse(responseCode = "400", description = "Functional Error", content = @Content(mediaType ="application/json")),
            @ApiResponse(responseCode = "500", description = "Technical Error", content = @Content(mediaType ="application/json"))
    })   
    @GetMapping("v1/DiracFieldAndLandauEnergy/{numElectrons}")
    public void generateDiracFieldAndLandauEnergyData(
        @Parameter(description = "number of electrons (e.g., 10)", required = true) @PathVariable int numElectrons) {

        DiracSimulation simulation = new DiracSimulation(new LandauEnergyCalculator(), numElectrons); 
        simulation.run();

        logger.info("Results of simulation :");
        List<Double> energies = simulation.getEnergies();
        CSVResultSaver saver = new CSVResultSaver();
        saver.saveResults(energies, "results_dirac_landau.csv"); 
        saver.displayResults(energies);

    }

}


