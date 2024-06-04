package com.outer.astronomy.infrastructure.repository.quantum;

import com.outer.astronomy.domain.repository.quantum.ProbabilityDensityRepository;
import com.outer.astronomy.domain.utils.Errors;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

@Component
public class ProbabilityDensityRepositoryImpl implements ProbabilityDensityRepository {

    static Logger logger = LoggerFactory.getLogger(ProbabilityDensityRepositoryImpl.class);

    @Override
    public  void saveProbabilityDensity(double[][][] probabilityDensity, int gridSize, String filename) {

        logProbabilityDensity(probabilityDensity, gridSize);

        try (BufferedWriter writer = new BufferedWriter(new FileWriter(filename))) {
            IntStream.range(0, gridSize).forEach(i -> {
                try {
                    writer.write("[z: " + i + "]\n");
                    IntStream.range(0, gridSize).forEach(j -> {
                        try {
                            writer.write("[");
                        } catch (IOException e) {
                            throw new RuntimeException(e);
                        }
                        IntStream.range(0, gridSize).forEach(k -> {
                            try {
                                if (k != gridSize - 1) {
                                    writer.write(Double.toString(probabilityDensity[i][j][k]) + ",");
                                }
                                if (k == gridSize - 1) {
                                    writer.write(Double.toString(probabilityDensity[i][j][k]));
                                }
                            } catch (IOException e) {
                                e.printStackTrace();
                            }
                        });
                        try {
                            if (j != gridSize - 1) {
                                writer.write("],\n");
                            }
                            if (j == gridSize - 1) {
                                writer.write("]\n");
                            }
                        } catch (IOException e) {
                            throw new RuntimeException(e);
                        }
                    });
                } catch (IOException e) {
                    e.printStackTrace();
                }
            });
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void saveProbabilityDensityZSlice(double[][][] probabilityDensity, int gridSize, int zSlice, String filename) {
        if (zSlice < 0 || zSlice >= gridSize) {
            throw new IllegalArgumentException(Errors.ZSliceOutOfBounds);
        }

        try (BufferedWriter writer = new BufferedWriter(new FileWriter(filename))) {
            IntStream.range(0, gridSize).forEach(j -> {
                try {
                    writer.write("[");
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
                IntStream.range(0, gridSize).forEach(k -> {
                    try {
                        if (k != gridSize - 1) {
                            writer.write(Double.toString(probabilityDensity[zSlice][j][k]) + ",");
                        }
                        if (k == gridSize - 1) {
                            writer.write(Double.toString(probabilityDensity[zSlice][j][k]));
                        }
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                });
                try {
                    if (j != gridSize - 1) {
                        writer.write("],\n");
                    }
                    if (j == gridSize - 1) {
                        writer.write("]\n");
                    }
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            });
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static void logProbabilityDensity(double[][][] probabilityDensity, int  gridSize) {
        IntStream.range(0, gridSize).forEach(i -> {
            logger.info("z: " + i);
            IntStream.range(0, gridSize).forEach(j -> {
                String line = IntStream.range(0, gridSize)
                        .mapToObj(k -> String.format("%.3f ", probabilityDensity[i][j][k]))
                        .collect(Collectors.joining());
                logger.info("{}", line);
            });
        });
    }
}
