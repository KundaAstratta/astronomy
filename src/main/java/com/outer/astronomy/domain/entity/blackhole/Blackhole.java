package com.outer.astronomy.domain.entity.blackhole;

import com.outer.astronomy.domain.utils.Errors;

import java.util.stream.IntStream;

import static com.outer.astronomy.domain.utils.Constants.gravitationalConstant;
import static com.outer.astronomy.domain.utils.Constants.speedOfLight;
import static com.outer.astronomy.domain.utils.Constants.BoltzmanConstant;
import static com.outer.astronomy.domain.utils.Constants.PlanckConstant;

public record Blackhole(FeaturesBlackhole featuresBlackhole) {

    //EN
    //The Schwarzschild radius is another important radius that is related to the mass of the wormhole
    //and the strength of its gravity.
    //The Schwarzschild radius is important because it determines the size of the wormhole's event horizon,
    //beyond which nothing can escape.
    //(m)
    public double getSchwarzschildRadius() {
        if (featuresBlackhole.mass() <= 0) {
            throw new IllegalArgumentException(Errors.MassMustBePositive);
        }
        return 2 * featuresBlackhole.mass() * gravitationalConstant / Math.pow(speedOfLight, 2);
    }
    public boolean isTraversable() {
        if (featuresBlackhole.mass() <= 0) {
            throw new IllegalArgumentException(Errors.MassMustBePositive);
        }
        if (featuresBlackhole.radius() <= 0) {
            throw new IllegalArgumentException(Errors.RadiusMustBePositive);
        }
        return featuresBlackhole.mass() < Math.pow(speedOfLight * featuresBlackhole.radius(), 3) / (2 * gravitationalConstant);
    }

    //EN
    //Check if black holes are close enough to have a wormhole.
    //Should not overlap
    public boolean isTooClose (double distance, double radius1, double radius2) {
        if (distance <= 0) {
            throw new IllegalArgumentException(Errors.DistanceMustBePositive);
        }
        if (radius1 <= 0 || radius2 <=0) {
            throw new IllegalArgumentException(Errors.RadiusMustBePositive);
        }

        if (distance < radius1 + radius2) {
            return true;
        }
        return false;
    }


    //EN
    // Delta t = Traversal time (s). The isTooClose flag must be set to true.
    // Delta t = Integral from r1 to r2 of dr / (sqrt(1 - 2 * G * M / r))
    // Delta t is the difference in proper time between two positions
    // r1 and r2 are the initial and final radial distances
    // Radial distance is used to describe the distance between an observer (or an object) and the center of a black hole.
    // G is the gravitational constant
    // M = m1 + m2
    public double timeToCrossApproachCalculus (double r1,double r2, double mass1, double mass2) {
        if (r1 <= 0 || r2<=0) {
            throw new IllegalArgumentException(Errors.RadialDistanceMustBePositive);
        }
        if (Math.abs(r2-r1) == 0) {
            throw new IllegalArgumentException(Errors.DistanceMustBePositive);
        }
        if (mass1 <= 0 || mass2 <=0) {
            throw new IllegalArgumentException(Errors.MassMustBePositive);
        }

        int n = 100; // Number of intervals for integration (to be adjusted according to desired precision)
        double h = (r2 - r1) / n; // Interval size

        // Initialization of the sum for integration
        double sum = 0.0;

        // Calculation of the integral using Simpson's method
        sum = IntStream.rangeClosed(0, n)
                .mapToDouble(i -> {
                    double x = r1 + i * h;
                    double y = Math.sqrt(1 - (2 * gravitationalConstant * (mass1 + mass2)) / x);
                    if (i == 0 || i == n) {
                        return y;
                    } else if (i % 2 == 0) {
                        return 2 * y;
                    } else {
                        return 4 * y;
                    }
                })
                .sum();

        // Final calculation of the time dilation
        return  h / 3 * sum;
    }

    //EN
    //Event horizon area of a black hole (m^2)
    public double eventHorizonArea ( ) {
        if (featuresBlackhole.mass() <= 0) {
            throw new IllegalArgumentException(Errors.MassMustBePositive);
        }
        return 4*Math.PI*(gravitationalConstant*gravitationalConstant* featuresBlackhole.mass()* featuresBlackhole.mass()) / (Math.pow(speedOfLight,4));
    }


    //EN
    //Entropy J/K
    public double entropy (double eventHorizonArea) {
        if (featuresBlackhole.eventHorizonArea() <= 0) {
            throw new IllegalArgumentException(Errors.EventHorizonMustBePresent);
        }
        return eventHorizonArea * BoltzmanConstant * Math.pow(speedOfLight,3) /(4*PlanckConstant*gravitationalConstant);
    }

    public double temperature () {
        if (featuresBlackhole.mass() <= 0) {
            throw new IllegalArgumentException(Errors.MassMustBePositive);
        }
        return PlanckConstant * Math.pow(speedOfLight,3) /(8 * Math.PI * BoltzmanConstant * gravitationalConstant * featuresBlackhole.mass());
    }
}

