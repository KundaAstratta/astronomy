package com.outer.astronomy.domain.entity.relativity;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Arrays;

public record Relativity(double[][] metric) {

    static Logger logger = LoggerFactory.getLogger(Relativity.class);

    public double[][] simulate() {

        RiemannTensor riemannTensor = new RiemannTensor();
        double[][][][] riemann = riemannTensor.calculateRiemannTensor(metric);

        EinsteinTensor einsteinTensor = new EinsteinTensor();
        double[][]  einstein = einsteinTensor.calculateEinsteinTensor(metric, riemann);

        Arrays.stream(einstein)
                .forEach(array -> logger.info(Arrays.toString(array)));

        return einstein;
    }


}
