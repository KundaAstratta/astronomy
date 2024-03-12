package com.outer.astronomy.application.alcubierre;

import com.outer.astronomy.domain.entity.alcubierre.Alcubierre;
import com.outer.astronomy.domain.entity.alcubierre.DataContainer;
import com.outer.astronomy.domain.entity.alcubierre.Gridsize;
import com.outer.astronomy.domain.service.alcubierre.AlcubierreService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.IntStream;

@RestController
@RequestMapping("/alcubierre")
public class AlcubierreController {

    Logger logger = LoggerFactory.getLogger(AlcubierreController.class);

    @Autowired
    AlcubierreService alcubierreService;

    @PostMapping("/simulate")
    public DataContainer simulate(@RequestBody Gridsize gridsize)  {
        logger.info("{}", gridsize.gridSize());
        Alcubierre alcubierre = new Alcubierre(gridsize.gridSize());

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

    @PostMapping("/simulate/save/{filename}")
    public void save(@PathVariable String filename , @RequestBody DataContainer data) throws IOException {
        alcubierreService.save(data, filename);
    }

}
