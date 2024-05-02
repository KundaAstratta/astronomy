package com.outer.astronomy.domain.entity.blackhole;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class BlackholeTest {

    @Test
    void getSchwarzschildRadius_OK() {
        FeaturesBlackhole featuresBlackhole = new FeaturesBlackhole(9*Math.pow(10,29),0,false,0,0,0);
        Blackhole blackhole = new Blackhole(featuresBlackhole);
        assertEquals(1336, blackhole.getSchwarzschildRadius(),1);
    }

    @Test
    void getSchwarzschildRadius_Mass_equal_to_zero() {
        FeaturesBlackhole featuresBlackhole = new FeaturesBlackhole(0,0,false,0,0,0);
        Blackhole blackhole = new Blackhole(featuresBlackhole);
        try {
            blackhole.getSchwarzschildRadius();
        } catch(Exception e) {
            assertEquals(e.getMessage(),"Mass must be positive.");
        }
    }

    @Test
    void isTraversable_OK() {
        FeaturesBlackhole featuresBlackhole = new FeaturesBlackhole(9*Math.pow(10,29),1336,false,0,0,0);
        Blackhole blackhole = new Blackhole(featuresBlackhole);
        assertEquals(blackhole.isTraversable(),true);
    }

    @Test
    void isTraversable_Mass_equals_to_zero () {
        FeaturesBlackhole featuresBlackhole = new FeaturesBlackhole(0,1336,false,0,0,0);
        Blackhole blackhole = new Blackhole(featuresBlackhole);
        try {
            blackhole.isTraversable();
        } catch (Exception e) {
            assertEquals(e.getMessage(), "Mass must be positive.");
        }
    }

    @Test
    void isTravesable_Radius_equals_to_zero () {
        FeaturesBlackhole featuresBlackhole = new FeaturesBlackhole(9*Math.pow(10,29),0,false,0,0,0);
        Blackhole blackhole = new Blackhole(featuresBlackhole);
        try {
            blackhole.isTraversable();
        } catch (Exception e) {
            assertEquals(e.getMessage(),"Radius must be positive.");
        }
    }

    @Test
    void isTooClose_false () {
        Blackhole blackhole = new Blackhole(null);
        assertEquals(blackhole.isTooClose(100,10,10),false);
    }


    @Test
    void isTooClose_true () {
        Blackhole blackhole = new Blackhole(null);
        assertEquals(blackhole.isTooClose(1,10,10),true);
    }

    @Test
    void isTooClose_distance_error() {
        Blackhole blackhole = new Blackhole(null);
        try {
            blackhole.isTooClose(0,10,10);
        } catch (Exception e) {
            assertEquals(e.getMessage(),"Distance must be positive.");
        }
    }

    @Test
    void isTooClose_radius_error() {
        Blackhole blackhole = new Blackhole(null);
        try {
            blackhole.isTooClose(100,0,0);
        } catch (Exception e) {
            assertEquals(e.getMessage(),"Radius must be positive.");
        }
    }

    @Test
    void timeToCross_Ok() {
        Blackhole blackhole = new Blackhole(null);
        assertEquals(7.935182117278767*Math.pow(10,20),blackhole.timeToCrossApproachCalculus(10*Math.pow(10,20),20*Math.pow(10,20),1.989*Math.pow(10,30),1.989*Math.pow(10,30)),0.1);
    }

    @Test
    void timeToCross_radius_error() {
        Blackhole blackhole = new Blackhole(null);
        try {
            blackhole.timeToCrossApproachCalculus(0,20*Math.pow(10,20),1.989*Math.pow(10,30),1.989*Math.pow(10,30));
        } catch (Exception e) {
            assertEquals(e.getMessage(),"Radial distance must be positive.");
        }
    }

    @Test
    void timeToCross_distance_error() {
        Blackhole blackhole = new Blackhole(null);
        try {
            blackhole.timeToCrossApproachCalculus(10,10,1.989*Math.pow(10,30),1.989*Math.pow(10,30));
        } catch (Exception e) {
            assertEquals(e.getMessage(),"Distance must be positive.");
        }
    }

    @Test
    void timeToCross_mass_error() {
        Blackhole blackhole = new Blackhole(null);
        try {
            blackhole.timeToCrossApproachCalculus(10,20,0,1.989*Math.pow(10,30));
        } catch (Exception e) {
            assertEquals(e.getMessage(),"Mass must be positive.");
        }
    }

    @Test
    void eventHorizonArea_Ok() {
        FeaturesBlackhole featuresBlackhole = new FeaturesBlackhole(9*Math.pow(10,29),1336,true,0,0,0);
        Blackhole blackhole = new Blackhole(featuresBlackhole);
        assertEquals(blackhole.eventHorizonArea(),5613368,1);
    }

    @Test
    void eventHorizonArea_mass_error() {
        FeaturesBlackhole featuresBlackhole = new FeaturesBlackhole(0,1336,true,0,0,0);
        Blackhole blackhole = new Blackhole(featuresBlackhole);
        try {
            blackhole.eventHorizonArea();
        } catch (Exception e) {
            assertEquals(e.getMessage(),"Mass must be positive.");
        }
    }

    @Test
    void entopy_Ok() {
        FeaturesBlackhole featuresBlackhole = new FeaturesBlackhole(9*Math.pow(10,29),1336,true,5613368,0,0);
        Blackhole blackhole = new Blackhole(featuresBlackhole);
        assertEquals(blackhole.entropy(featuresBlackhole.eventHorizonArea()),1.1804488176510484*Math.pow(10,52),0.1);
    }

    @Test
    void entropy_error_eventHorizonArea() {
        FeaturesBlackhole featuresBlackhole = new FeaturesBlackhole(9*Math.pow(10,29),1336,true,0,0,0);
        Blackhole blackhole = new Blackhole(featuresBlackhole);
        try {
            blackhole.entropy(featuresBlackhole.eventHorizonArea());
        } catch (Exception e) {
            assertEquals(e.getMessage(),"Event Horizon must be present.");
        }
    }

    @Test
    void temperature_Ok() {
        FeaturesBlackhole featuresBlackhole = new FeaturesBlackhole(9*Math.pow(10,29),1336,true,5613368,0,0);
        Blackhole blackhole = new Blackhole(featuresBlackhole);
        assertEquals(blackhole.temperature(),8.565382520009469*Math.pow(10,-7),1);
    }

    @Test
    void temperature_error_mass () {
        FeaturesBlackhole featuresBlackhole = new FeaturesBlackhole(0,1336,true,5613368,0,0);
        Blackhole blackhole = new Blackhole(featuresBlackhole);
        try {
            blackhole.temperature();
        } catch (Exception e) {
            assertEquals(e.getMessage(), "Mass must be positive.");
        }
    }

}