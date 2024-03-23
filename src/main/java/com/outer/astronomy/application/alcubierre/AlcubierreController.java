package com.outer.astronomy.application.alcubierre;

import com.outer.astronomy.domain.entity.alcubierre.Alcubierre;
import com.outer.astronomy.domain.entity.alcubierre.DataContainer;
import com.outer.astronomy.domain.entity.alcubierre.Gridsize;
import com.outer.astronomy.domain.service.alcubierre.AlcubierreService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.IntStream;

@RestController
public class AlcubierreController {

    Logger logger = LoggerFactory.getLogger(AlcubierreController.class);

    @Autowired
    AlcubierreService alcubierreService;

    @Operation(summary = "Alcubierre metrics simulation", operationId = "simulate",
            description = "The Alcubierre metric is a theoretical solution " +
                    "to the Einstein field equations that allows for faster-than-light " +
                    "travel by creating a warp bubble around a spacecraft.",
            tags = {"alcubierre-controller"}
    )
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Success", content = @Content(mediaType ="application/json", schema = @Schema(implementation = DataContainer.class))),
            @ApiResponse(responseCode = "400", description = "Functional Error", content = @Content(mediaType ="application/json")),
            @ApiResponse(responseCode = "500", description = "Technical Error", content = @Content(mediaType ="application/json"))
    })
    @PostMapping("v1/simulate")
    public DataContainer simulate(@RequestBody Gridsize gridsize)  {
        logger.info("{}", gridsize.gridSize());
        Alcubierre alcubierre = new Alcubierre(gridsize);

        double[][][] result ;

        List<double[]> data = new ArrayList<>();

        result = alcubierre.simulate();

        IntStream.range(0, gridsize.gridSize())
                .boxed()
                .flatMap(i -> IntStream.range(0, gridsize.gridSize())
                        .boxed()
                        .flatMap(j -> IntStream.range(0, gridsize.gridSize())
                                .filter(k -> !Double.isNaN(result[i][j][k]))
                                .mapToObj(k -> {
                                    double[] entry = new double[]{i, j, k, result[i][j][k]};
                                    logger.info("({},{},{},{})", i, j, k, result[i][j][k]);
                                    return entry;
                                })
                        )
                )
                .forEach(data::add);


        return new DataContainer(data);

    }
    @Operation(summary = "Save Alcubierre metrics simulation", operationId = "save",
            description = "Save Alcubierre metrics simulation in a file (filename).",
            tags = {"alcubierre-controller"}
    )
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Success", content = @Content(mediaType ="application/json")),
            @ApiResponse(responseCode = "400", description = "Functional Error", content = @Content(mediaType ="application/json")),
            @ApiResponse(responseCode = "500", description = "Technical Error", content = @Content(mediaType ="application/json"))
    })
    @PostMapping("v1/simulate/save/{filename}")
    public void save(@PathVariable String filename , @RequestBody DataContainer data) throws IOException {
        alcubierreService.save(data, filename);
    }

}
