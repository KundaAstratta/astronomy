package com.outer.astronomy.domain.entity.relativity;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class RelativityTest {

    @Test
    void shouldGetEinsteinTensor_WithFlatSpacetime () {

        double[][][][] riemann;
        double[][] metric = new double[][]{
                {-1, 0, 0, 0},
                {0, 1, 0, 0},
                {0, 0, 1, 0},
                {0, 0, 0, 1}
        };

        RiemannTensor riemannTensorRelativitySimulation = new RiemannTensor();
        riemann = riemannTensorRelativitySimulation.calculateRiemannTensor(metric);

        EinsteinTensor einsteinTensorRelativitySimulation = new EinsteinTensor();
        double[][] einsteinTensor = einsteinTensorRelativitySimulation.calculateEinsteinTensor(metric, riemann);

        assertEquals(-750000,einsteinTensor[0][0],1);
        assertEquals(999999,einsteinTensor[1][1],1);
        assertEquals(4499999,einsteinTensor[2][2],1);
        assertEquals( -1.6774345478283484E-9,einsteinTensor[3][3],0.1);

    }

    @Test
    void shouldGetEinsteinTensor_WithSchwarzschildMetric () {

        double[][][][] riemann;

        double r = 2000; // Rayon de Schwarzschild
        double G = 6.67430*Math.pow(10,-11);
        double M = 6370000;
        double theta = 2.3;
        double[][] metric = new double[][]{
                {-1 + 2 * G * M / r, 0, 0, 0},
                {0, 1 / (1 - 2 * G * M / r), 0, 0},
                {0, 0, (r * r), 0},
                {0, 0, 0, r * r * Math.sin(theta) * Math.sin(theta)}
        };

        RiemannTensor riemannTensorRelativitySimulation = new RiemannTensor();
        riemann = riemannTensorRelativitySimulation.calculateRiemannTensor(metric);

        EinsteinTensor einsteinTensorRelativitySimulation = new EinsteinTensor();
        double[][] einsteinTensor = einsteinTensorRelativitySimulation.calculateEinsteinTensor(metric, riemann);

        assertEquals(-3.1121496306547427E12,einsteinTensor[0][0],0.1);
        assertEquals(2.8878504286189614E12,einsteinTensor[1][1],0.1);
        assertEquals(7.032166384567688E25,einsteinTensor[2][2],0.1);
        assertEquals( -8299.162734880087,einsteinTensor[3][3],1);

    }


    @Test
    void shouldGetEinsteinTensor_WithExpandingUnivese () {

        double[][][][] riemann;

        double a0 = 1; //a0 valeur du facteur d'échelle à l'époque actuelle (temps présent)
        double t = 1.1984048E10;//t le temps cosmique
        double t0 = 1;//t0 âge de l'univers (temps présent).
        double a = a0 * Math.pow((t / t0) , 2.0 / 3.0);//a facteur d'echelle de lunivers
        double r = 3.0857E24; //r coordonnée radiale. r représente la distance radiale entre un observateur (qui peut être placé à n'importe quel point dans l'univers) et un point quelconque dans l'univers.
        double theta = 2.3; //l'angle polaire, qui est défini dans le cadre des coordonnées sphériques.
        double[][] metric = new double[][]{
                {-1 , 0, 0, 0},
                {0, a * a, 0, 0},
                {0, 0, (a * a) * (r * r), 0},
                {0, 0, 0, (a * a) * (r * r) * Math.sin(theta) * Math.sin(theta)}
        };

        RiemannTensor riemannTensorRelativitySimulation = new RiemannTensor();
        riemann = riemannTensorRelativitySimulation.calculateRiemannTensor(metric);

        EinsteinTensor einsteinTensorRelativitySimulation = new EinsteinTensor();
        double[][] einsteinTensor = einsteinTensorRelativitySimulation.calculateEinsteinTensor(metric, riemann);

        assertEquals(-2.0316380428220322E68,einsteinTensor[0][0],0.1);
        assertEquals(1.4178680638165566E95,einsteinTensor[1][1],0.1);
        assertEquals(-4.649238404692601E255,einsteinTensor[2][2],0.1);
        assertEquals( -3.536756873408203E115,einsteinTensor[3][3],0.1);

    }


    @Test
    void shouldGetEinsteinTensor_WithFLRW () {

        double[][][][] riemann;

        double a0 = 1; //a0 valeur du facteur d'échelle à l'époque actuelle (temps présent)
        double t = 1.1984048E10;//t le temps cosmique
        double t0 = 1;//t0 âge de l'univers (temps présent).
        double a = a0 * Math.pow((t / t0) , 2.0 / 3.0);//a facteur d'echelle de lunivers
        double r = 3.0857E24; //r coordonnée radiale. r représente la distance radiale entre un observateur (qui peut être placé à n'importe quel point dans l'univers) et un point quelconque dans l'univers.
        double theta = 2.3; //l'angle polaire, qui est défini dans le cadre des coordonnées sphériques.
        double k = 1;//k est la courbure spatiale de l'univers
        double[][] metric = new double[][]{
                {-1 , 0, 0, 0},
                {0, a * a  / (1- k * r *r ), 0, 0},
                {0, 0, (a * a) * (r * r), 0},
                {0, 0, 0, (a * a) * (r * r) * Math.sin(theta) * Math.sin(theta)}
        };

        RiemannTensor riemannTensorRelativitySimulation = new RiemannTensor();
        riemann = riemannTensorRelativitySimulation.calculateRiemannTensor(metric);

        EinsteinTensor einsteinTensorRelativitySimulation = new EinsteinTensor();
        double[][] einsteinTensor = einsteinTensorRelativitySimulation.calculateEinsteinTensor(metric, riemann);

        assertEquals(-2.0316380428220322E68,einsteinTensor[0][0],0.1);
        assertEquals(0.0015639433526570229,einsteinTensor[1][1],0.001);
        assertEquals(-1.6141770357780917E291,einsteinTensor[2][2],0.1);
        assertEquals( -3.536756873408203E115,einsteinTensor[3][3],0.1);

    }
}