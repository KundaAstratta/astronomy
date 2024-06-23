package com.outer.astronomy.infrastructure.repository.alcubierre;

import com.outer.astronomy.domain.model.alcubierre.DataContainer;
import com.outer.astronomy.domain.repository.alcubierre.AlcubierreRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.io.FileWriter;
import java.io.IOException;
import java.io.BufferedWriter;
import java.util.Arrays;
import java.util.stream.Collectors;

@Component
public class AlcubierreRepositoryImpl implements AlcubierreRepository {

    Logger logger = LoggerFactory.getLogger(AlcubierreRepositoryImpl.class);


    @Override
    public void save(DataContainer data, String filename)  {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(filename))) {

            writer.write("x,y,z,valeur");
            writer.newLine();

            for (double[] dataPoint : data.data()) {
                String formattedData = Arrays.stream(dataPoint)
                        .mapToObj(String::valueOf)
                        .collect(Collectors.joining(","));

                writer.write(formattedData);
                writer.newLine();
            }
        } catch (IOException e) {
            logger.error("Erreur lors de l'écriture du fichier CSV : {} " , e.getMessage());
        }
    }

}
