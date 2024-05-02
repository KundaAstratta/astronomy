package com.outer.astronomy.domain.entity.alcubierre;

import java.util.stream.IntStream;

import static com.outer.astronomy.domain.utils.Constants.gravitationalConstant;
import static com.outer.astronomy.domain.utils.Constants.speedOfLight;

public record Alcubierre(Gridsize gridSize) {

    public double[][][] simulate() {

        double[][][] spacetime = new double[100][100][100];

        IntStream.range(0, gridSize.gridSize())
                .boxed()
                .flatMap(i -> IntStream.range(0, gridSize.gridSize())
                        .boxed()
                        .flatMap(j -> IntStream.range(0, gridSize.gridSize())
                                .boxed()
                                .map(k -> {
                                    double x = (i - gridSize.gridSize() / 2) * speedOfLight;
                                    double y = (j - gridSize.gridSize() / 2) * speedOfLight;
                                    double z = (k - gridSize.gridSize() / 2) * speedOfLight;
                                    double t = (i + j + k) * speedOfLight / gridSize.gridSize();
                                    Point point = new Point(x, y, z, t);
                                    return new Quadruplet<>(i, j, k, point);
                                })
                        )
                )
                .forEach(entry -> {
                    int i = entry.first();
                    int j = entry.second();
                    int k = entry.third();
                    Point point = entry.fourth();
                    spacetime[i][j][k] = getMetric(point);
                });

        return spacetime;

    }

    double fu(double r) {
        return Math.sqrt(1 - (2 * gravitationalConstant * r) / (speedOfLight * speedOfLight));
    }
    public double getMetric(Point point) {
        double r = Math.sqrt(point.x() * point.x() + point.y() * point.y() + point.z() * point.z());
        double w = Math.sqrt(1 - (speedOfLight * speedOfLight) / (point.t() * point.t()));
        double phi = Math.atan2(point.y(), point.x());
        double theta = Math.acos(point.z() / r);
        return w * fu(r) * Math.sin(theta) * Math.cos(phi);
    }

}

