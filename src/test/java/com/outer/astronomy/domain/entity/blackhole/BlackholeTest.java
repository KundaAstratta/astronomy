package com.outer.astronomy.domain.entity.blackhole;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class BlackholeTest {

    @Test
    void getSchwarzschildRadius_OK() {
        Features features = new Features(9e29,0,false,0,0,0);
        Blackhole blackhole = new Blackhole(features);
        assertEquals(1336, blackhole.getSchwarzschildRadius(),1);
    }

    @Test
    void getSchwarzschildRadius_Mass_equal_to_zero() {
        Features features = new Features(0,0,false,0,0,0);
        Blackhole blackhole = new Blackhole(features);
        try {
            blackhole.getSchwarzschildRadius();
        } catch(Exception e) {
            assertEquals(e.getMessage(),"Mass must be positive.");
        }
    }

    @Test
    void isTraversable_OK() {
        Features features = new Features(9e29,1336,false,0,0,0);
        Blackhole blackhole = new Blackhole(features);
        assertEquals(blackhole.isTraversable(),true);
    }

    @Test
    void isTraversable_Mass_equals_to_zero () {
        Features features = new Features(0,1336,false,0,0,0);
        Blackhole blackhole = new Blackhole(features);
        try {
            blackhole.isTraversable();
        } catch (Exception e) {
            assertEquals(e.getMessage(), "Mass must be positive.");
        }
    }

    @Test
    void isTravesable_Radius_equals_to_zero () {
        Features features = new Features(9e29,0,false,0,0,0);
        Blackhole blackhole = new Blackhole(features);
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
            assertEquals(e.getMessage(),"Distance must be positive");
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
        assertEquals(7.935182117278767e20,blackhole.timeToCrossApproachCalculus(10e20,20e20,1.989e30,1.989e30),0.1);
    }

    @Test
    void timeToCross_radius_error() {
        Blackhole blackhole = new Blackhole(null);
        try {
            blackhole.timeToCrossApproachCalculus(0,20e20,1.989e30,1.989e30);
        } catch (Exception e) {
            assertEquals(e.getMessage(),"Radial distance must be positive");
        }
    }

    @Test
    void timeToCross_distance_error() {
        Blackhole blackhole = new Blackhole(null);
        try {
            blackhole.timeToCrossApproachCalculus(10,10,1.989e30,1.989e30);
        } catch (Exception e) {
            assertEquals(e.getMessage(),"Distance must be positive");
        }
    }

    @Test
    void timeToCross_mass_error() {
        Blackhole blackhole = new Blackhole(null);
        try {
            blackhole.timeToCrossApproachCalculus(10,20,0,1.989e30);
        } catch (Exception e) {
            assertEquals(e.getMessage(),"Mass must be positive.");
        }
    }

    @Test
    void eventHorizonArea_Ok() {
        Features features = new Features(9e29,1336,true,0,0,0);
        Blackhole blackhole = new Blackhole(features);
        assertEquals(blackhole.eventHorizonArea(),5613368,1);
    }

    @Test
    void eventHorizonArea_mass_error() {
        Features features = new Features(0,1336,true,0,0,0);
        Blackhole blackhole = new Blackhole(features);
        try {
            blackhole.eventHorizonArea();
        } catch (Exception e) {
            assertEquals(e.getMessage(),"Mass must be positive.");
        }
    }

    @Test
    void entopy_Ok() {
        Features features = new Features(9e29,1336,true,5613368,0,0);
        Blackhole blackhole = new Blackhole(features);
        assertEquals(blackhole.entropy(features.eventHorizonArea()),1.1804488176510482E52,1);
    }

    @Test
    void entropy_error_eventHorizonArea() {
        Features features = new Features(9e29,1336,true,0,0,0);
        Blackhole blackhole = new Blackhole(features);
        try {
            blackhole.entropy(features.eventHorizonArea());
        } catch (Exception e) {
            assertEquals(e.getMessage(),"Event Horizon must be present");
        }
    }

    @Test
    void temperature_Ok() {
        Features features = new Features(9e29,1336,true,5613368,0,0);
        Blackhole blackhole = new Blackhole(features);
        assertEquals(blackhole.temperature(),8.565382520009469E-7,1);
    }

    @Test
    void temperature_error_mass () {
        Features features = new Features(0,1336,true,5613368,0,0);
        Blackhole blackhole = new Blackhole(features);
        try {
            blackhole.temperature();
        } catch (Exception e) {
            assertEquals(e.getMessage(), "Mass must be positive.");
        }
    }

}