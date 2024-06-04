package com.outer.astronomy.domain.entity.blackhole;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class BlackholeTest {

    @Test
    void shouldGetSchwarzschildRadius_WithSommGoodValues() {
        FeaturesBlackhole featuresBlackhole = new FeaturesBlackhole(9*Math.pow(10,29),0,false,0,0,0);
        Blackhole blackhole = new Blackhole(featuresBlackhole);
        assertEquals(1336, blackhole.getSchwarzschildRadius(),1);
    }

    @Test
    void shouldGetError_WithMassEqualTo0() {
        FeaturesBlackhole featuresBlackhole = new FeaturesBlackhole(0,0,false,0,0,0);
        Blackhole blackhole = new Blackhole(featuresBlackhole);
        try {
            blackhole.getSchwarzschildRadius();
        } catch(Exception e) {
            assertEquals(e.getMessage(),"Mass must be positive.");
        }
    }

    @Test
    void shouldIsTraversable_WithGoodValues() {
        FeaturesBlackhole featuresBlackhole = new FeaturesBlackhole(9*Math.pow(10,29),1336,false,0,0,0);
        Blackhole blackhole = new Blackhole(featuresBlackhole);
        assertEquals(blackhole.isTraversable(),true);
    }

    @Test
    void shouldGetErrorForTraversableBlackHole_WithMassEqualTo0 () {
        FeaturesBlackhole featuresBlackhole = new FeaturesBlackhole(0,1336,false,0,0,0);
        Blackhole blackhole = new Blackhole(featuresBlackhole);
        try {
            blackhole.isTraversable();
        } catch (Exception e) {
            assertEquals(e.getMessage(), "Mass must be positive.");
        }
    }

    @Test
    void shouldGetErrorForTraversableBlackHole_WithRadiusEqualTo0 () {
        FeaturesBlackhole featuresBlackhole = new FeaturesBlackhole(9*Math.pow(10,29),0,false,0,0,0);
        Blackhole blackhole = new Blackhole(featuresBlackhole);
        try {
            blackhole.isTraversable();
        } catch (Exception e) {
            assertEquals(e.getMessage(),"Radius must be positive.");
        }
    }

    @Test
    void shouldIsTooCloseReturnFalse () {
        Blackhole blackhole = new Blackhole(null);
        assertEquals(blackhole.isTooClose(100,10,10),false);
    }


    @Test
    void shouldIsToCloseReturnTrue () {
        Blackhole blackhole = new Blackhole(null);
        assertEquals(blackhole.isTooClose(1,10,10),true);
    }

    @Test
    void shouldIsToCloseReturnError_WithWrongDistance() {
        Blackhole blackhole = new Blackhole(null);
        try {
            blackhole.isTooClose(0,10,10);
        } catch (Exception e) {
            assertEquals(e.getMessage(),"Distance must be positive.");
        }
    }

    @Test
    void shouldIsToCloseeturnError_WithWrongRadius() {
        Blackhole blackhole = new Blackhole(null);
        try {
            blackhole.isTooClose(100,0,0);
        } catch (Exception e) {
            assertEquals(e.getMessage(),"Radius must be positive.");
        }
    }

    @Test
    void shouldTimeToCross() {
        Blackhole blackhole = new Blackhole(null);
        assertEquals(7.935182117278767*Math.pow(10,20),blackhole.timeToCrossApproachCalculus(10*Math.pow(10,20),20*Math.pow(10,20),1.989*Math.pow(10,30),1.989*Math.pow(10,30)),0.1);
    }

    @Test
    void shouldTimeToCrossReturnError_WithWrongRadialDistance() {
        Blackhole blackhole = new Blackhole(null);
        try {
            blackhole.timeToCrossApproachCalculus(0,20*Math.pow(10,20),1.989*Math.pow(10,30),1.989*Math.pow(10,30));
        } catch (Exception e) {
            assertEquals(e.getMessage(),"Radial distance must be positive.");
        }
    }

    @Test
    void shouldTimeToCrossReturnError_WithWrongDistance() {
        Blackhole blackhole = new Blackhole(null);
        try {
            blackhole.timeToCrossApproachCalculus(10,10,1.989*Math.pow(10,30),1.989*Math.pow(10,30));
        } catch (Exception e) {
            assertEquals(e.getMessage(),"Distance must be positive.");
        }
    }

    @Test
    void shouldTimeToCrossReturnError_WithWrongMass() {
        Blackhole blackhole = new Blackhole(null);
        try {
            blackhole.timeToCrossApproachCalculus(10,20,0,1.989*Math.pow(10,30));
        } catch (Exception e) {
            assertEquals(e.getMessage(),"Mass must be positive.");
        }
    }

    @Test
    void shouldGetEventHorizonArea() {
        FeaturesBlackhole featuresBlackhole = new FeaturesBlackhole(9*Math.pow(10,29),1336,true,0,0,0);
        Blackhole blackhole = new Blackhole(featuresBlackhole);
        assertEquals(blackhole.eventHorizonArea(),5613368,1);
    }

    @Test
    void shouldEventHorizonAreaReturnError_WithWrongMass() {
        FeaturesBlackhole featuresBlackhole = new FeaturesBlackhole(0,1336,true,0,0,0);
        Blackhole blackhole = new Blackhole(featuresBlackhole);
        try {
            blackhole.eventHorizonArea();
        } catch (Exception e) {
            assertEquals(e.getMessage(),"Mass must be positive.");
        }
    }

    @Test
    void shouldGetEntopy() {
        FeaturesBlackhole featuresBlackhole = new FeaturesBlackhole(9*Math.pow(10,29),1336,true,5613368,0,0);
        Blackhole blackhole = new Blackhole(featuresBlackhole);
        assertEquals(blackhole.entropy(featuresBlackhole.eventHorizonArea()),1.1804488176510484*Math.pow(10,52),0.1);
    }

    @Test
    void shouldEntropyReturnError_WithoutEventHorizonArea() {
        FeaturesBlackhole featuresBlackhole = new FeaturesBlackhole(9*Math.pow(10,29),1336,true,0,0,0);
        Blackhole blackhole = new Blackhole(featuresBlackhole);
        try {
            blackhole.entropy(featuresBlackhole.eventHorizonArea());
        } catch (Exception e) {
            assertEquals(e.getMessage(),"Event Horizon must be present.");
        }
    }

    @Test
    void shouldGetTemperature() {
        FeaturesBlackhole featuresBlackhole = new FeaturesBlackhole(9*Math.pow(10,29),1336,true,5613368,0,0);
        Blackhole blackhole = new Blackhole(featuresBlackhole);
        assertEquals(blackhole.temperature(),8.565382520009469*Math.pow(10,-7),1);
    }

    @Test
    void shouldTemperatureError_WithWrongMass () {
        FeaturesBlackhole featuresBlackhole = new FeaturesBlackhole(0,1336,true,5613368,0,0);
        Blackhole blackhole = new Blackhole(featuresBlackhole);
        try {
            blackhole.temperature();
        } catch (Exception e) {
            assertEquals(e.getMessage(), "Mass must be positive.");
        }
    }

}